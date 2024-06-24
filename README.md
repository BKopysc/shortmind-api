
# Backend for Shortmind 
A simple text blog for short posts

## Features
- User authentication and authorization (JWT and Role-based)
- Posts ("shortminds") creation
- Comments on posts
- Likes on posts

## Tech Stack
- Java 17
- Spring Boot
- PostgreSQL

## How to run
1. Clone the repository
2. Run the following command to start the server
```
./mvnw spring-boot:run
```
3. The server will start at `http://localhost:8080`

## API Documentation
Swagger is avaiable at `http://localhost:8080/swagger-ui/index.html`

## Tests
Check the `src/test` folder for tests