## Abner — Wiring por configuración y Neo4j

`StrategyConfig` y `StorageConfig` usan `@ConditionalOnProperty` para registrar
el bean correcto según `app.tree-strategy` y `app.storage` sin que las clases
concretas dependan de Spring (OCP: agregar una estrategia nueva no modifica las existentes).
`@Autowired(required = false)` en `StorageConfig` evita que Spring falle al arrancar
en modo `memory`, donde los repos de JPA y Neo4j no están disponibles.
Neo4j usa la relación `REPLY_TO` de hijo a padre para modelar la jerarquía.
El árbol lo reconstruye el motor desde nodos planos — no se usa Cypher para recorridos.