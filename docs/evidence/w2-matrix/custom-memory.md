
Código	Detalles
200	
Cuerpo de respuesta

Descargar
{
  "id": 1,
  "author": null,
  "content": "Este es el post raíz",
  "createdAt": null,
  "children": [
    {
      "id": 2,
      "author": null,
      "content": "Este es el post raíz",
      "createdAt": null,
      "children": []
    }
  ]
}
Encabezados de respuesta
 conexión : mantener-activo 
 tipo de contenido : aplicación/json 
 Fecha : viernes, 22 de mayo de 2026, 06:53:01 GMT 
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
  "createdAt": "2026-05-22T06:53:01.345Z",
  "children": [
    "string"
  ]
}