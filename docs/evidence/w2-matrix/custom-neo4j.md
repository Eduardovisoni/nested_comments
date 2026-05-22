Respuesta del servidor
Código	Detalles
201	
Cuerpo de respuesta

Descargar
{
  "id": 0,
  "author": "Eduardo",
  "content": "Este es el post raíz",
  "createdAt": "2026-05-22T00:35:30.2231106",
  "parentId": null
}
Encabezados de respuesta
 conexión : mantener-activo 
 tipo de contenido : aplicación/json 
 Fecha : viernes, 22 de mayo de 2026, 06:35:30 GMT 
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
  "createdAt": "2026-05-22T06:40:04.024Z",
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
1
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
  'http://localhost:8080/comments/1/replies' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "author": "Eduardo",
  "content": "Este es el post raíz"
}'
URL de solicitud
http://localhost:8080/comments/1/replies
Respuesta del servidor
Código	Detalles
500
Indocumentados
Error: el estado de respuesta es 500

Cuerpo de respuesta

Descargar
{
  "timestamp": "2026-05-22T06:38:14.855+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "path": "/comments/1/replies"
}
Encabezados de respuesta
 conexión : cerrar 
 tipo de contenido : aplicación/json 
 Fecha : viernes, 22 de mayo de 2026, 06:38:14 GMT 
 codificación de transferencia : fragmentada 