# Runtime Selectors

El backend cambia motor y persistencia por configuración, sin recompilar.

## Combinación 1 — custom + memory (demo principal)


mvn spring-boot:run -pl app
Defaults activos: app.tree-strategy=custom, app.storage=memory.

## Combinación 2 — collections + postgres
docker compose up -d postgres
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=collections,postgres
Activa: app.tree-strategy=collections, app.storage=postgres.

Regla
No editar application.properties durante la demo. Cambiar combinación con perfiles.

