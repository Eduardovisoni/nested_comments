# Week 3 Delivery Checklist

## Backend

- [x] `custom + memory` probado en Swagger — ver `docs/evidence/w3/custom-memory.md`
- [x] `collections + postgres` probado en Swagger — ver `docs/evidence/w3/collections-postgres.md`
- [x] `collections + memory` probado en Swagger — ver `docs/evidence/w3/collections-memory.md`
- [x] Validación cruzada custom vs collections — ver `docs/evidence/w3/custom-vs-collections.md`
- [x] Matriz completa 6 combinaciones — ver `docs/evidence/w2-matrix/`

## Documentación

- [x] README motor custom — `docs/engines/custom.md`
- [x] README motor collections — `docs/engines/collections.md`
- [x] Documento de decisiones (sección Eduardo) — `docs/decisions/design-decisions.md`

## Frontend

- [x] Frontend conectado — Lovable
- [x] CORS configurado — `CorsConfig.java`

## GitHub

- [x] PRs de Semana 3 mergeados con reviewer
- [x] Mínimo 3 commits por integrante esta semana

## Trello

- [ ] Todas las tarjetas de Semana 3 en Done

## Demo mínima confirmada

- [x] `custom + memory` — arranca con `mvn spring-boot:run -pl app`
- [x] `collections + postgres` — arranca con `docker compose up -d postgres && mvn spring-boot:run -pl app -Dspring-boot.run.profiles=collections,postgres`
- [x] Cambio de combinación sin recompilar usando perfiles Spring
