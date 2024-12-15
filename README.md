*Copyright (C) Diana-Iuliana Cismaru*

# Weather Data Application
## Homework #2 - SCD

This project is a simple Spring Boot application designed to manage weather data,
including temperatures for cities and countries. The application is containerized
using Docker and uses PostgreSQL as its database.

## Features
- Manage temperature data for cities and countries.
- Query temperatures based on location and date ranges.
- Containerized application with Docker Compose.

## Requirements
- Docker
- Docker Compose

## How to Run

1. Build and run the containers:
   ```bash
   docker-compose up --build
   ```

2. Access the application:
    - The API will be available at `http://localhost:8080`.

## Project Structure

```
t2scd/
├── Dockerfile
├── docker-compose.yml
├── .dockerignore
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   ├── resources/
│   ├── tests/
└── README.md
```

## Notes
- The application uses environment variables for database configuration:
    - `SPRING_DATASOURCE_URL`
    - `SPRING_DATASOURCE_USERNAME`
    - `SPRING_DATASOURCE_PASSWORD`
- Data persistence is handled via Docker volumes.
- To enable communication between containers using named DNS (e.g., referring
to the database container as `db` instead of its IP address), the application is
configured to use a custom Docker network (`app-network`) defined in 
`docker-compose.yml`. This allows Docker to automatically resolve service names
within the network. For testing DNS resolution, the `ping` utility was added to
the application container by modifying the `Dockerfile`. The `ping` command can
be used to verify connectivity between containers:
   ```bash
  docker exec -it tema2-app-1 ping db
   ```

