## Arrancar backend en custom + memory:

mvn spring-boot:run -pl app

https://nested-comment-viewer.lovable.app/

Latest Raw JSON Response
HTTP 200 · 16ms
{
  "id": 1,
  "author": null,
  "content": "comentario Raiz",
  "createdAt": null,
  "children": [
    {
      "id": 4,
      "author": null,
      "content": "hijo 1",
      "createdAt": null,
      "children": []
    },
    {
      "id": 6,
      "author": null,
      "content": "este es una respuesta al nodo raiz",
      "createdAt": null,
      "children": []
    }
  ]
}



##Luego cambiar a collections + postgres:

docker compose up -d postgres
mvn spring-boot:run -pl app -Dspring-boot.run.profiles=collections,postgres

{
  "id": 3,
  "author": null,
  "content": "Raiz demo",
  "createdAt": null,
  "children": [
    {
      "id": 6,
      "author": null,
      "content": "esta el comentario respuesta a postgres",
      "createdAt": null,
      "children": []
    },
    {
      "id": 7,
      "author": null,
      "content": "esta el comentario respuesta a postgres",
      "createdAt": null,
      "children": []
    }
  ]
}
