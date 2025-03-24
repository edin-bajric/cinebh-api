# The backend of the Cinebh app
This backend is a part of the Cinebh application, it contains the logic for all of the core services of the application

## Specifications
The backend is written using Spring Boot and PostgreSQL

Spring Boot version: 3.3.4

Java version: 17

Software architecture pattern: Layered Architecture

Build automation tool: Maven

### Dependencies
Spring Boot DevTools

Spring Web

JDBC API

Spring Data JPA

PostgreSQL Driver

Spring OpenAPI Documentation (springdoc-openapi-starter-webmvc-ui)

Flyway

Lombok

Spring Security

JJWT

Stripe Java

## Setup and running

`git clone https://github.com/edin-bajric/cinebh-api.git`

`cd cinebh-api`

`mvn clean install`

`mvn spring-boot:run`

## Deployment

### prerequisites: Docker and Docker Compose

`create a new folder and cd into it`

`git clone https://github.com/edin-bajric/cinebh-api.git and rename the cloned folder to cinebh`

`in the root folder create a new file called docker-compose.yml and paste the following code`

```

services:
postgres:
image: postgres:16.4
environment:
POSTGRES_DB: 
POSTGRES_USER: 
POSTGRES_PASSWORD:
volumes:
- postgres_data:/var/lib/postgresql/data
ports:
- "5432:5432"
networks:
- default

backend:
build:
context: ./cinebh
environment:
DB_URL: 
DB_USERNAME: 
DB_PASSWORD: 
MG_DOMAIN: 
MG_FROM_EMAIL: 
MG_PASSWORD: 
JWT_SECRET:
depends_on:
- postgres
ports:
- "8080:8080"

frontend:
build:
context: ./cinebh-ui
container_name: cinebh-frontend-1
depends_on:
- backend
ports:
- "80:80"

volumes:
postgres_data:

```

`fill in the environment variables in the docker-compose.yml file`

`docker-compose up --build`

`the backend will be running on http://localhost:8080`
