## Mariana — Motor Collections y Postgres

Usé colecciones estándar del JDK para implementar las 11 operaciones:
`HashMap` para indexar nodos, `ArrayDeque` como cola/pila en BFS y DFS,
`LinkedList` para construir rutas con `addFirst`, `HashSet` para detectar ciclos.
Postgres guarda nodos planos en `comments(id, content, parent_id)`.
La jerarquía no se arma con SQL — el motor la reconstruye desde los datos planos.
`CollectionsTreeAlgorithm` no lleva `@Component`: el wiring lo hace Abner en `StrategyConfig` (SRP).