# The backend of the Cinebh app

This backend is a part of the Cinebh application. It contains the logic for all core services of the application.

## Specifications
- **Framework**: Spring Boot
- **Database**: PostgreSQL
- **Spring Boot Version**: 3.3.4
- **Java Version**: 17
- **Architecture**: Layered Architecture
- **Build Tool**: Maven

### Dependencies

#### Core Dependencies
- `spring-boot-starter-web` - Web framework for building REST APIs
- `spring-boot-starter-data-jpa` - JPA and Hibernate integration
- `spring-boot-starter-jdbc` - JDBC support
- `postgresql` - PostgreSQL database driver

#### Development & Tooling
- `spring-boot-devtools` - Development tools for live reload
- `springdoc-openapi-starter-webmvc-ui` - OpenAPI documentation
- `flyway-core` - Database migrations
- `lombok` - Reduces boilerplate code

#### Security & Authentication
- `spring-boot-starter-security` - Security features
- `jjwt` - JSON Web Token (JWT) support

#### Payment
- `stripe-java` - Stripe API integration

## Setup and running

```sh
git clone https://github.com/edin-bajric/cinebh-api.git
cd cinebh-api
mvn clean install
mvn spring-boot:run
```

The backend will be running on `http://localhost:8080`. 🚀

## Deployment

### Prerequisites: Docker and Docker Compose

```sh
# Create a new folder and navigate into it
git clone https://github.com/edin-bajric/cinebh-api.git cinebh
cd cinebh
```

If the `docker-compose.yml` already exists from the frontend repo, you can skip this part.

Create a new file called `docker-compose.yml` in the root folder and paste the following code:

```yaml
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

Fill in the environment variables in the `docker-compose.yml` file, then run:

```sh
docker-compose up --build
```

The backend will be running on `http://localhost:8080`. 🚀
