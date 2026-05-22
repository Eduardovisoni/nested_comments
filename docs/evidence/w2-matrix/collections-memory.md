Responses
Curl

curl -X 'POST' \
  'http://localhost:8080/comments/root' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "author": "Eduardo",
  "content": "Este es el post raíz"
}'
Request URL
http://localhost:8080/comments/root
Server response
Code	Details

201	
Response body
Download
{
  "id": 1,
  "author": "Eduardo",
  "content": "Este es el post raíz",
  "createdAt": "2026-05-21T23:24:22.2265851",
  "parentId": null
}
Response headers
 connection: keep-alive 
 content-type: application/json 
 date: Fri,22 May 2026 05:24:22 GMT 
 keep-alive: timeout=60 
 transfer-encoding: chunked 


Responses
Code	Description	Links

201	
Comentario raíz creado

Media type

application/json
Controls Accept header.
Example Value
Schema
{
  "id": 0,
  "author": "string",
  "content": "string",
  "createdAt": "2026-05-22T05:27:14.916Z",
  "parentId": 0
}