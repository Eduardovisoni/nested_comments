## Combinaciones verificadas (Semana 2)

| Estrategia   | Memory | Postgres | Neo4j |
|--------------|--------|----------|-------|
| `custom`     | ✅     | ✅       | ✅    |
| `collections`| ✅     | ✅       | ✅    |

Guía de verificación (Swagger UI + receta paso a paso): [`docs/matrix/verification-guide.md`](docs/matrix/verification-guide.md).
Evidencia (responses + snapshots de BD): [`docs/evidence/w2-matrix/`](docs/evidence/w2-matrix/).

# custom + memory (default)
mvn spring-boot:run -pl app

# custom + postgres
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=postgres

# custom + neo4j
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=neo4j

# collections + memory
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=collections

# collections + postgres
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=collections,postgres

# collections + neo4j
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=collections,neo4j