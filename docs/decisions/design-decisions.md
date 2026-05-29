# Design Decisions

## Eduardo — Motor Custom y contrato PlainNode

`PlainNode` solo transporta `(id, value, parentId)` — sin `author` ni `createdAt`.
Motivo: `tree-engine` es un módulo JDK puro, sin dependencias de dominio ni de Spring.
Agregar campos del caso de uso (`author`, `createdAt`) violaría SRP: el motor dejaría
de ser genérico y quedaría acoplado al dominio "comentarios".

`author` y `createdAt` se generan en `TreeServiceImpl` para la respuesta inmediata del POST
y se aceptan como efímeros en los GET — los repositorios no los persisten porque reciben
`PlainNode`, no `Comment`.

Esta decisión permite que el mismo módulo `tree-engine` pueda reutilizarse en cualquier
otro caso de uso jerárquico sin cambiar su contrato.
