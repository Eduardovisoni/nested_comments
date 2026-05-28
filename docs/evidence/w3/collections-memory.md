# Evidencia — collections + memory

## Árbol creado

| POST | Body | id obtenido | parentId |
|------|------|-------------|----------|
| `/comments/root` | `{"author":"Mariana","content":"Raíz"}` | 1 | null |
| `/comments/{parentId}/replies` | `{"author":"Mariana","content":"Hijo 1"}` | 2 | 1 |
| `/comments/{parentId}/replies` | `{"author":"Mariana","content":"Hijo 2"}` | 3 | 1 |
| `/comments/{parentId}/replies` | `{"author":"Mariana","content":"Nieto"}` | 4 | 2 |

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