# Nested Comments API

API REST para gestión de comentarios jerárquicos con árbol. Permite crear hilos de comentarios anidados, recorrerlos (DFS/BFS), calcular profundidad, altura y ancestros — con motor de árbol y persistencia intercambiables sin recompilar.

---

## Módulos

```
nested_comments/
├── tree-engine/   ← librería pura JDK, sin Spring (algoritmos de árbol)
└── app/           ← Spring Boot 3.3.5, expone los algoritmos como REST API
```

### tree-engine

Librería Java 17 sin dependencias externas. Define la interfaz `TreeAlgorithmStrategy` y dos implementaciones:

| Implementación | Activación | Descripción |
|---|---|---|
| `CustomTreeAlgorithm` | `app.tree-strategy=custom` (default) | Clases propias `TreeNode` + `NodeChildren` |
| `CollectionsTreeAlgorithm` | `app.tree-strategy=collections` | Solo JDK: `HashMap`, `ArrayDeque`, `LinkedList`, `HashSet` |

Modelo interno: `PlainNode(id, value, parentId)` — sin campos de dominio para mantener el motor desacoplado.

### app

Spring Boot que expone el motor vía HTTP. Capas:

- **Controller** → `CommentController`, `TreeController` (implementan interfaces generadas desde `openapi.yaml`)
- **Service** → `TreeServiceImpl` (orquesta repositorio + algoritmo)
- **Persistence** → `TreeRepository` con 3 implementaciones intercambiables
- **Config** → `StrategyConfig`, `StorageConfig` (wiring por perfil)
- **Mapper** → `CommentMapper` (DTOs ↔ dominio)

---

## Requisitos

- Java 17+
- Maven 3.8+
- Docker + Docker Compose (para Postgres / Neo4j)
- Puerto `8081` disponible

---

## Levantar las bases de datos

```bash
# Solo Postgres
docker-compose up -d postgres

# Solo Neo4j
docker-compose up -d neo4j

# Ambas
docker-compose up -d
```

| Servicio | URL | Usuario | Contraseña |
|---|---|---|---|
| Postgres | `localhost:5432` DB: `trees` | `trees` | `trees123` |
| Neo4j browser | `http://localhost:7474` | `neo4j` | `trees123` |

---

## Ejecutar la aplicación

El perfil default es `custom + memory` (sin DB).

```bash
# custom + memory (default)
mvn spring-boot:run -pl app

# collections + memory
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=collections

# custom + postgres
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=postgres

# collections + postgres
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=collections,postgres

# custom + neo4j
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=neo4j

# collections + neo4j
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=collections,neo4j
```

**Combinaciones verificadas:**

| Estrategia | Memory | Postgres | Neo4j |
|---|---|---|---|
| `custom` | ✅ | ✅ | ✅ |
| `collections` | ✅ | ✅ | ✅ |

Una vez corriendo:

- API base: `http://localhost:8081`
- Swagger UI: `http://localhost:8081/swagger-ui.html`
- Spec OpenAPI: `http://localhost:8081/openapi.yaml`

---

## Endpoints

### Comentarios

| Método | Path | Descripción |
|---|---|---|
| `POST` | `/comments/root` | Crea un comentario raíz (sin padre) |
| `POST` | `/comments/{parentId}/replies` | Agrega una respuesta a un comentario existente |
| `GET` | `/comments/{commentId}/path` | IDs desde la raíz hasta el nodo (inclusive) |
| `GET` | `/comments/{commentId}/depth` | Cantidad de aristas desde la raíz al nodo |
| `GET` | `/comments/{commentId}/ancestors` | IDs de todos los ancestros (excluye el nodo) |

### Árbol

| Método | Path | Descripción |
|---|---|---|
| `GET` | `/tree` | Árbol completo como JSON jerárquico |
| `GET` | `/tree/{commentId}` | Sub-árbol con raíz en `commentId` |
| `GET` | `/tree/traversal?type=DFS\|BFS` | IDs en orden de recorrido |
| `GET` | `/tree/height` | Cantidad de niveles del árbol |
| `GET` | `/tree/validate` | `true` si el árbol no tiene ciclos |

### Schemas

```yaml
CommentRequest:  { author: string, content: string }

CommentResponse: { id, author, content, createdAt, parentId }

TreeResponse:    { id, author, content, createdAt, children: [TreeResponse] }
```

---

## Persistencia

### Consultar datos por consola

**Postgres:**
```bash
docker exec -it trees-postgres psql -U trees -d trees
```
```sql
SELECT * FROM comments;
```

**Neo4j (browser):** `http://localhost:7474`
```cypher
MATCH (n) RETURN n
```

**Neo4j (CMD):**
```bash
docker exec -it trees-neo4j cypher-shell -u neo4j -p trees123
```
```cypher
MATCH (n) RETURN n;
```

**Memory:** no hay acceso directo — los datos viven en RAM. Usar los endpoints HTTP.

### Schema SQL (Postgres)

```sql
CREATE TABLE IF NOT EXISTS comments (
    id         BIGSERIAL PRIMARY KEY,
    author     VARCHAR(100),
    content    TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    parent_id  BIGINT REFERENCES comments(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_comments_parent_id ON comments(parent_id);
```

### Nodos Neo4j

Nodo `Comment` con relación `REPLY_TO` hacia el padre.

---

## Diseño

### OpenAPI contract-first

`openapi.yaml` es la fuente de verdad. Maven genera interfaces Java y DTOs a partir del YAML. Los controllers implementan esas interfaces — si el contrato cambia, el compilador obliga a actualizar los controllers.

```
openapi.yaml → openapi-generator-maven-plugin → CommentsApi.java / TreeApi.java
                                                           ↓
                                              CommentController implements CommentsApi
                                              TreeController   implements TreeApi
```

### Patrones aplicados

| Patrón | Dónde |
|---|---|
| **Strategy** | `TreeAlgorithmStrategy` → `CustomTreeAlgorithm` / `CollectionsTreeAlgorithm` |
| **Repository** | `TreeRepository` → `MemoryTreeRepository` / `PostgresTreeRepository` / `Neo4jTreeRepository` |
| **DIP** | `TreeServiceImpl` depende de interfaces, nunca de implementaciones concretas |
| **SRP** | `tree-engine` solo sabe de árboles; no conoce HTTP, Spring ni dominio |
| **Mapper** | `CommentMapper` aísla la conversión DTOs ↔ dominio |

---

## Documentación adicional

| Archivo | Contenido |
|---|---|
| `docs/decisions/` | Decisiones de diseño por integrante |
| `docs/engines/` | Descripción detallada de cada algoritmo |
| `docs/matrix/verification-guide.md` | Guía paso a paso para verificar las 6 combinaciones |
| `docs/evidence/` | Evidencia de pruebas por semana |
| `docs/delivery/` | Checklists de entrega por sprint |
