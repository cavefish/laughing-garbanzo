# laughing-garbanzo

Este proyecto es de uso educativo, para permitir el
aprendizaje de los siguientes conceptos:

- Arquitectura de aplicaciones.
- Inyección de dependencias.
- Tests unitarios.
- Spring boot y maven.
- Persistencia.
- Streams.

## Tareas pendientes a realizar por el estudiante

- Completar el código de la clase [TagNameNormalizer.java](src/main/java/dev/cavefish/minipost/domain/TagNameNormalizer.java),
 atendiendo a sus tests.
- Completar el código de la clase [EmailValidator.java](src/main/java/dev/cavefish/minipost/domain/users/EmailValidator.java),
 creando los tests necesarios.
- Implementar la persistencia de los objetos de dominio User y Post.
  - Deberás crear una clase de entidad. Fíjate en [TagEntity.java](src/main/java/dev/cavefish/minipost/entities/TagEntity.java).
  - Crea un mapper para mantener la división de los módulos del sistema. Fíjate en [TagEntityMapper.java](src/main/java/dev/cavefish/minipost/entities/mappers/TagEntityMapper.java). 
  - Crea una relación entre Post (N) y User (1).

## 