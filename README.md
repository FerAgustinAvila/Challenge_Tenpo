## Overview

This is the challenge by Fernando Agustin Avila.

This is a Spring Boot application written in Java. Maven is used as the build tool.
The java JDK used is 11.

## Endpoints documentation
See postman collection.

## Docker
To run application with docker we have to run:
- ./mvnw clean package -DskipTests
- cp target/docker-spring-boot-postgres-0.0.1-SNAPSHOT.jar src/main/docker
- docker-compose up --build
