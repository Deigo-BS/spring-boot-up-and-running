# Proyectos correspondientes al cápitulo 7

## planefinder

Este es un proyecto inicial que no desarrollamos, que hemos usado igualmente en capítulos anteriores.
Como tal de lo que se encarga este proyecto es de generar objetos "Aircraft" y exponerlos en el endpoint 
GET /aircraft

En 7-3.1 hicimos cambios (siguiendo el libro) to use Spring Cloud Stream to publish messages for consumption 
by the aircraft-position (and any other applicable) application.


## aircraft-positions

Este es el proyecto que desarrollamos a la par del libro.

Notas: 
    - Como nosotros desarrollamos este proyecto desde el inicio, usamos Spring Boot `3.5.7`, a diferencia del 
    libro que usa `2.5.3`, por lo que tuvimos que realizar las siguiente modificaciones:
        - El libro propone usar: 
            `<spring-cloud.version>2020.0.0</spring-cloud.version>` 
         en su lugar usamos:
            `<spring-cloud.version>2024.0.0</spring-cloud.version>`
        
        - En el `application.properties` agregamos la siguiente propiedad que deacuerdo a lo investigado, es obligatoria para usar Sring Cloud Stream para publicar/consumir mensajes en Spring Boot 3.X:
            - En el `application.properties` del Supplier (esta no la usamos pues corresponde a "planefinder"):
                `spring.cloud.function.definition=reportPositions`
            - En el `application.properties` del Consumer:
                `spring.cloud.function.definition=retrieveAircraftPositions`