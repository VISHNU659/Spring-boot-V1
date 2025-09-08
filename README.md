Create a resources/application.properties Folder inside main 

Add this to applicaition.properties

spring.application.name=demo1

spring.datasource.url=jdbc:oracle:thin:@0.0.0.0:1521:FREE
spring.datasource.username=C##spring
spring.datasource.password=spring
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=update

# Only use the Below !!!!  Once when you want to change the DataBase Structure as it drops and creates all the tables from scratch!!
#spring.jpa.hibernate.ddl-auto=create

spring.jpa.database-platform=org.hibernate.dialect.OracleDialect


CREATE USER C##spring IDENTIFIED BY spring;
GRANT CONNECT, RESOURCE TO C##spring;
ALTER USER C##spring QUOTA UNLIMITED ON USERS;


