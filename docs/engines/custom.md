# Motor Custom — CustomTreeAlgorithm

## Qué resuelve

Implementa `TreeAlgorithmStrategy` usando **clases propias** del equipo — sin librerías externas
para modelar el árbol. Solo JDK puro + código del grupo.

## Configuración

```properties
app.tree-strategy=custom
app.storage=memory
```

## Comando de arranque

```bash
mvn spring-boot:run -pl app
```

## Estructuras internas

| Clase | Rol |
|---|---|
| `TreeNode` | Nodo del árbol: almacena `id`, `value` y lista de hijos (`List<TreeNode>`) |
| `NodeChildren` | Índice `Map<parentId, List<TreeNode>>` para agrupar hijos por padre al construir el árbol |

`CustomTreeAlgorithm` no lleva `@Component` — el bean lo declara `StrategyConfig` con
`@ConditionalOnProperty(name = "app.tree-strategy", havingValue = "custom")`.

## Las 11 operaciones

| Operación | Estrategia interna |
|---|---|
| `buildTree` | Construye `TreeNode` con `NodeChildren` indexando hijos por padre; retorna `TreeView` |
| `buildSubTree` | Llama a `buildTree` y busca el nodo con `findInTree` (recursión) |
| `pathToRoot` | `HashMap<id, parentId>` (`buildParentMap`); sube desde `nodeId` hacia la raíz con `path.add(0, current)` |
| `dfs` | Recursión sobre `TreeView` (`dfsTraversal`) |
| `bfs` | `ArrayList` como cola con índice de frente `front` (sin `ArrayDeque`); agrega hijos con `addAll` |
| `height` | Recursión sobre `TreeView`: `1 + maxChildHeight` |
| `depth` | `buildParentMap`; cuenta pasos subiendo hasta que no hay padre |
| `ancestors` | `buildParentMap`; colecta ids subiendo desde el **padre** del nodo (no incluye el nodo mismo) |
| `hasNoCycles` | Para cada nodo sube por `parentMap` con un `HashSet`; si visita un id repetido → `false` |

## Dependencias permitidas

- JDK estándar: `HashMap`, `HashSet`, `ArrayList`, `List`, `Map`, `Set`
- Clases internas del módulo: `TreeNode`, `NodeChildren`
- Interfaz del módulo: `TreeAlgorithmStrategy`, `TreeView`, `PlainNode`
- **Sin** Spring, **sin** JPA, **sin** librerías externas de árbol
