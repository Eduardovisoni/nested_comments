# Evidencia — collections + postgres

## Árbol creado

| POST | Body | id obtenido | parentId |
|------|------|-------------|----------|
| `/comments/root` | `{"author":"Mariana","content":"Raíz"}` | 15 | null |
| `/comments/{parentId}/replies` | `{"author":"Mariana","content":"Hijo 1"}` | 16 | 15 |
| `/comments/{parentId}/replies` | `{"author":"Mariana","content":"Hijo 2"}` | 17 | 15 |
| `/comments/{parentId}/replies` | `{"author":"Mariana","content":"Nieto"}` | 18 | 16 |

## Resultados GET

| Endpoint | Parámetro | Resultado |
|----------|-----------|-----------|
| `GET /tree` | — | Raíz(15) → hijos(16,17), nieto(18) bajo el 16 |
| `GET /tree/{commentId}` | commentId=16 | Subárbol 16 → 18 |
| `GET /comments/{commentId}/path` | commentId=18 | `[15, 16, 18]` |
| `GET /tree/traversal` | DFS | `[15, 16, 18, 17]` |
| `GET /tree/traversal` | BFS | `[15, 16, 17, 18]` |
| `GET /tree/height` | — | `3` |
| `GET /comments/{commentId}/depth` | commentId=18 | `2` |
| `GET /comments/{commentId}/ancestors` | commentId=18 | `[16, 15]` |
| `GET /tree/validate` | — | `true` |

## Datos en Postgres

```
 id | content | parent_id
----+---------+-----------
 15 | Raíz    |
 16 | Hijo 1  |        15
 17 | Hijo 2  |        15
 18 | Nieto   |        16
(4 rows)
```