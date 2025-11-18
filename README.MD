#Proyectos correspondientes al cápitulo 6
Este capítulo demostró cómo optimizar el almacenamiento y la recuperación de datos utilizando diversas opciones de bases de datos y los proyectos y funcionalidades de Spring Data que permiten su uso de la manera más potente posible: a través de Spring Boot.

###planefinder
Este proyecto proviene del código fuente del libro, el cual se encarga de generar "aircrafts" y los expone 
en un endpoint para que el resto de proyectos propuestos por el libro lo consuman.

###sbur-redis
Proyecto siguiendo el ejemplo del libro, usando una base de datos redis (no ha sido probado)

###sbur-jpa
Proyecto siguiendo el ejemplo del libro, el libro usa MySQL pero yo lo adapte para usar Oracle
(no me funciono como esperaba)


##Proyectos personales
Ya que sbur-jpa no funciono como se esperaba cree los siguientes proyectos que basicametne sigune la misma 
funcioanlidad que proponen los ejemplos del libro

###diego-generator
Imita la funcionalidad de "planefinder", en lugar de gerar 'aircrafts' genera 'students' que igualmente los
expone en un endpoint

###diego-jpa-oracle
Imita la funcionalidad de "sbur-jpa" que consume a diego-generator e interactua con una base de datos oracle
ademas en este ejemplo tambien mostramos como usar data.sql y schema.sql

###diego-jpa-database-independent
Este proyecto tiene la misma funcionalidad que diego-jpa-oracle con la diferencia que:
- El código busca ser independiente de la base de datos a diferencia de diego-jpa-oracle que explícitamente requiere trabajar con una base que tenga el mecanismo de secuencias.
- Ya no consume a diego-generator, y simplemente genera a los students el mismo.

Ademas agrega el tema del ch6 - 7.3 Loading Data: 
que nos muestra una alternativa de como cargar/popular una tabla sin la necesidad de usar schema.sql data.sql con lo cual mantenemos la indepencia de la base de datos.