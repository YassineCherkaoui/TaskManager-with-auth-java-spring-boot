# TaskManager with Authentication using Java Spring Boot

This project is a backend application built to facilitate task management and authentication. It's implemented using Java Spring Boot framework.

## Usage

Interact with the API through the following endpoints:

- **POST** `/api/auth/register`
- **POST** `/api/auth/login`
- **GET** `/api/users/me`
- **GET** `/api/users` (Only accessible to ADMIN)
- **GET** `/api/tasks`
- **GET** `/api/tasks/{id}`
- **GET** `/api/tasks/title/{title}`
- **GET** `/api/tasks/completed/{completed}`
- **POST** `/api/tasks`
- **PUT** `/api/tasks/{id}`
- **DELETE** `/api/tasks/{id}`

Authentication via a JWT access token is required for certain endpoints.

## Requirements

Ensure you have the following installed:

- Java 17
- Spring Boot 3
- PostgreSQL 15

## Getting Started

1. Clone this repository.
2. Create a PostgreSQL database named `TaskManager-db`.
3. update file application-dev in resounces => add ur credentials
4. Configure your database connection settings in the `application.yml` file.
5. Run the application using `./mvnw spring-boot:run`.