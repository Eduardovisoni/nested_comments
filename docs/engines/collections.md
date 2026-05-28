# Motor Collections

## Qué resuelve

`CollectionsTreeAlgorithm` implementa las 11 operaciones del contrato
`TreeAlgorithmStrategy` usando únicamente colecciones estándar del JDK,
sin estructuras de datos propias.

## Configuración

```properties
app.tree-strategy=collections
app.storage=postgres
```

## Comandos de arranque

```bash
docker compose up -d postgres
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=collections,postgres
```

## Colecciones usadas

| Colección | Uso |
|-----------|-----|
| `HashMap` | Indexar nodos por id y agrupar hijos por padre |
| `ArrayDeque` | Cola para BFS, pila para DFS y BFS de subárbol |
| `LinkedList` | Construir ruta raíz → nodo con `addFirst` |
| `HashSet` | Detectar ciclos sin repetir visitas |

## Operaciones

| # | Endpoint | Método |
|---|----------|--------|
| 1 | `POST /comments/root` | `buildTree` |
| 2 | `POST /comments/{parentId}/replies` | `buildTree` |
| 3 | `GET /tree` | `buildTree` |
| 4 | `GET /tree/{commentId}` | `buildSubTree` |
| 5 | `GET /tree/traversal?type=DFS` | `dfs` |
| 6 | `GET /tree/traversal?type=BFS` | `bfs` |
| 7 | `GET /tree/height` | `height` |
| 8 | `GET /comments/{commentId}/path` | `pathToRoot` |
| 9 | `GET /comments/{commentId}/depth` | `depth` |
| 10 | `GET /comments/{commentId}/ancestors` | `ancestors` |
| 11 | `GET /tree/validate` | `hasNoCycles` |