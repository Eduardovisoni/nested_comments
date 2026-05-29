# Validación cruzada — custom vs collections

## Árbol de prueba usado (mismo en ambas estrategias)

```
Raíz (id=1)
├── Respuesta 1 (id=2)
│   └── Sub-respuesta (id=4)
└── Respuesta 2 (id=3)
```

## Comparación de resultados

| Operación | custom + memory | collections + memory | ¿Coincide? |
|---|---|---|---|
| `GET /tree` | Raíz(1) → hijos(2,3), nieto(4) bajo el 2 | Raíz(1) → hijos(2,3), nieto(4) bajo el 2 | ✅ |
| `GET /tree/traversal DFS` | `[1, 2, 4, 3]` | `[1, 2, 4, 3]` | ✅ |
| `GET /tree/traversal BFS` | `[1, 2, 3, 4]` | `[1, 2, 3, 4]` | ✅ |
| `GET /tree/height` | `3` | `3` | ✅ |
| `GET /tree/{id}` commentId=2 | Subárbol 2 → 4 | Subárbol 2 → 4 | ✅ |
| `GET /comments/{id}/path` commentId=4 | `[1, 2, 4]` | `[1, 2, 4]` | ✅ |
| `GET /comments/{id}/depth` commentId=4 | `2` | `2` | ✅ |
| `GET /comments/{id}/ancestors` commentId=4 | `[2, 1]` | `[2, 1]` | ✅ |
| `GET /tree/validate` | `true` | `true` | ✅ |

## Conclusión

Las dos implementaciones producen resultados equivalentes sobre el mismo conjunto de datos.
`custom` usa clases propias (`TreeNode`, `NodeChildren`, `ArrayList` como cola para BFS).
`collections` usa `HashMap`, `ArrayDeque`, `LinkedList`, `HashSet` del JDK.
Ambas implementan la misma interfaz `TreeAlgorithmStrategy` y son intercambiables por configuración.
