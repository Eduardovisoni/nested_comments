
Parámetros
Cancelar
Sin parámetros

Cuerpo de la solicitud

aplicación/json
{
  "author": "Eduardo",
  "content": "Este es el post raíz"
}
Ejecutar
Claro
Respuestas
Rizo

curl -X 'POST' \
  'http://localhost:8080/comments/root' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "author": "Eduardo",
  "content": "Este es el post raíz"
}'
URL de solicitud
http://localhost:8080/comments/root
Respuesta del servidor
Código	Detalles
201	
Cuerpo de respuesta

Descargar
{
  "id": 2,
  "author": "Eduardo",
  "content": "Este es el post raíz",
  "createdAt": "2026-05-22T00:15:44.7158576",
  "parentId": null
}
Encabezados de respuesta
 conexión : mantener-activo 
 tipo de contenido : aplicación/json 
 Fecha : viernes, 22 de mayo de 2026, 06:15:44 GMT 
 mantener-activo : tiempo de espera=60 
 codificación de transferencia : fragmentada 
Respuestas
Código	Descripción	Campo de golf
201	
Comentario raíz creado

Tipo de medio

aplicación/json
Encabezado de controles Accept.
Valor de ejemplo
Esquema
{
  "id": 0,
  "author": "string",
  "content": "string",
  "createdAt": "2026-05-22T06:22:27.032Z",
  "parentId": 0
}
No hay enlaces

CORREO
/comentarios /{parentId} /respuestas
Responder a un comentario

Parámetros
Cancelar
Nombre	Descripción
ID del padre  *
entero ($ int64 )
( camino )
ID del padre
Cuerpo de la solicitud

aplicación/json
{
  "author": "Eduardo",
  "content": "Este es el post raíz"
}
Corrija los siguientes errores de validación e inténtelo de nuevo.
Para 'parentId': No se ha proporcionado el campo obligatorio.
Ejecutar
Respuestas
Código	Descripción	Campo de golf
201	
Respuesta creada

Tipo de medio

aplicación/json
Encabezado de controles Accept.
Valor de ejemplo
Esquema
{
  "id": 0,
  "author": "string",
  "content": "string",
  "createdAt": "2026-05-22T06:22:27.034Z",
  "parentId": 0
}
No hay enlaces
404	
Comentario padre no encontrado

No hay enlaces

CONSEGUIR
/comentarios /{commentId} /ruta
Ruta desde la raíz hasta el comentario

Parámetros
Cancelar
Nombre	Descripción
ID de comentario  *
entero ($ int64 )
( camino )
ID de comentario
Corrija los siguientes errores de validación e inténtelo de nuevo.
Para 'commentId': No se ha proporcionado el campo obligatorio.
Ejecutar
Respuestas
Código	Descripción	Campo de golf
200	
Lista de ID desde la raíz al nodo (incluye ambos extremos)

Tipo de medio

aplicación/json
Encabezado de controles Accept.
Valor de ejemplo
Esquema
[
  0
]
No hay enlaces
404	
Comentario no encontrado

No hay enlaces

CONSEGUIR
/comentarios /{commentId} /profundidad
Profundidad de un comentario

Parámetros
Cancelar
Nombre	Descripción
ID de comentario  *
entero ($ int64 )
( camino )
ID de comentario
Corrija los siguientes errores de validación e inténtelo de nuevo.
Para 'commentId': No se ha proporcionado el campo obligatorio.
Ejecutar
Respuestas
Código	Descripción	Campo de golf
200	
Número de aristas desde la raíz hasta el nudo

Tipo de medio

aplicación/json
Encabezado de controles Accept.
Valor de ejemplo
Esquema
0
No hay enlaces
404	
Comentario no encontrado

No hay enlaces

CONSEGUIR
/comentarios /{commentId} /antepasados
Ancestros de un comentario

Árbol
Operaciones sobre el árbol como un todo



CONSEGUIR
/árbol
Árbol completo de comentarios

Parámetros
Cancelar
Sin parámetros

Ejecutar
Claro
Respuestas
Rizo

curl -X 'GET' \
  'http://localhost:8080/tree' \
  -H 'accept: application/json'
URL de solicitud
http://localhost:8080/tree
Respuesta del servidor
Código	Detalles
200	
Cuerpo de respuesta

Descargar
{
  "id": 1,
  "author": null,
  "content": "Este es el post raíz",
  "createdAt": null,
  "children": []
}
Encabezados de respuesta
 conexión : mantener-activo 
 tipo de contenido : aplicación/json 
 Fecha : viernes, 22 de mayo de 2026, 06:22:21 GMT 
 mantener-activo : tiempo de espera=60 
 codificación de transferencia : fragmentada 
Respuestas
Código	Descripción	Campo de golf
200	
Árbol jerárquico completo

Tipo de medio

aplicación/json
Encabezado de controles Accept.
Valor de ejemplo
Esquema
{
  "id": 0,
  "author": "string",
  "content": "string",
  "createdAt": "2026-05-22T06:22:27.038Z",
  "children": [
    "string"
  ]
}
No hay enlaces

CONSEGUIR
/árbol /recorrido
Recorrido DFS o BFS

Parámetros
Cancelar
Nombre	Descripción
tipo  *
cadena
( consulta )

DFS
Ejecutar
Claro
Respuestas
Rizo

curl -X 'GET' \
  'http://localhost:8080/tree/traversal?type=DFS' \
  -H 'accept: application/json'
URL de solicitud
http://localhost:8080/tree/traversal?type=DFS
Respuesta del servidor
Código	Detalles
200	
Cuerpo de respuesta

Descargar
[
  1
]
Encabezados de respuesta
 conexión : mantener-activo 
 tipo de contenido : aplicación/json 
 Fecha : viernes, 22 de mayo de 2026, 06:22:27 GMT 
 mantener-activo : tiempo de espera=60 
 codificación de transferencia : fragmentada 
Respuestas
Código	Descripción	Campo de golf
200	
ID de nodos en orden de recorrido

Tipo de medio

aplicación/json
Encabezado de controles Accept.
Valor de ejemplo
Esquema
[









C:\Users\Cooperativa Moyutan>docker exec -it trees-postgres psql -U trees -d trees -c "SELECT id, content, parent_id FROM comments ORDER BY id;"
 id |       content        | parent_id 
----+----------------------+-----------
  1 | Este es el post raíz |
  2 | Este es el post raíz |
(2 rows)


C:\Users\Cooperativa Moyutan>
