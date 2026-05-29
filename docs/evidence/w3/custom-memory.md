# Evidencia — custom + memory

## Configuración usada

```properties
app.tree-strategy=custom
app.storage=memory
```

```bash
mvn spring-boot:run -pl app
```

## Árbol creado

| POST | Body | id obtenido | parentId |
|------|------|-------------|----------|
| `/comments/root` | `{"author":"Eduardo","content":"Post raíz"}` | 1 | null |
| `/comments/{parentId}/replies` | `{"author":"Eduardo","content":"Respuesta 1"}` | 2 | 1 |
| `/comments/{parentId}/replies` | `{"author":"Eduardo","content":"Respuesta 2"}` | 3 | 1 |
| `/comments/{parentId}/replies` | `{"author":"Eduardo","content":"Sub-respuesta de Respuesta 1"}` | 4 | 2 |

## Resultados GET

| Endpoint | Parámetro | Resultado |
|----------|-----------|-----------|
| `GET /tree` | — | Raíz(1) → hijos(2,3), nieto(4) bajo el 2 |
| `GET /tree/{commentId}` | commentId=2 | Subárbol 2 → 4 |
| `GET /comments/{commentId}/path` | commentId=4 | `[1, 2, 4]` |
| `GET /tree/traversal` | DFS | `[1, 2, 4, 3]` |
| `GET /tree/traversal` | BFS | `[1, 2, 3, 4]` |
| `GET /tree/height` | — | `3` |
| `GET /comments/{commentId}/depth` | commentId=4 | `2` |
| `GET /comments/{commentId}/ancestors` | commentId=4 | `[2, 1]` |
| `GET /tree/validate` | — | `true` |

## Nota sobre author / createdAt

`author` y `createdAt` se generan en `TreeServiceImpl` para la respuesta inmediata del POST
pero no se persisten en memoria — `PlainNode` solo transporta `(id, value, parentId)`.
En los GET del árbol esos campos vienen `null`. Decisión de diseño documentada en
`docs/decisions/design-decisions.md`.
