# spring-boot-multiple-datasources

This project is a poc to demonstrate how to configure multiple datasources within one application

Any datasources needs below things
1. Data source config
2. Entitty manager to mp tables to entities
3. trnsaction management

In this application used h2 just for demo but you can use in any combination of datasources

JPA dependency be=y default needs atleast one impl of above 3 so for jpa to identify that we annotate one of impl with @Primary(In our case i did for TODO)

GOTCHA/s faced
1. persistence unit: If only building one EntityManagerFactory you can omit this, but if there are more than one in the same application youshould give them distinct names.(this entire statement is definition provided by java specifiaction)
