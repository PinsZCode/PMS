# Project Management System

## Introduction

This is a simple Java application using Spring Boot for managing projects. It allows users to perform CRUD (Create, Read, Update, Delete) operations on project information.

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Exception Handling](#exception-handling)
- [Testing](#testing)
- [Documentation](#documentation)
- [Dependencies](#dependencies)
- [Modules and Features](#modules-and-features)
- [Build and Run](#build-and-run)


## Prerequisites

Before running this application, ensure you have the following installed:

- Java 17
- Maven
- Git
- STS(Spring Suit Tool) or intellij IDEA

## Getting Started

To get started with this project, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/PinsZCode/PMS.git
    ```

2. Navigate to the project directory:

    ```bash
    cd PMS
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    java -jar target/PMS-0.0.1-SNAPSHOT.jar
    ```

Once the application is running, you can access the H2 Database Console by navigating to [http://localhost:8080/h2-console](http://localhost:8080/h2-console) in your web browser. Make sure to use the JDBC URL `jdbc:h2:mem:testdb` to connect.

## API Endpoints

The following API endpoints are available:

- **POST /api/pms/add**: Create a new project
- **GET /api/pms/read**: Retrieve all projects
- **GET /api/pms/read/{id}**: Retrieve a project by ID
- **DELETE /api/pms/delete/{id}**: Delete a project by ID
- **PUT /api/pms/update/{id}**: Update a project by ID

## Exception Handling

Global exception handling is implemented to manage exceptions and provide meaningful error messages.

## Testing

Unit tests are written for service layer methods, and integration tests are written for API endpoints.

## Documentation

API documentation is generated using Swagger. Access the Swagger UI by navigating to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) after running the application.

## Dependencies

- Spring Web
- Spring Data JPA
- Spring Validation 
- H2 Database
- Springfox Swagger

## Modules and Features

### 1. Project Management Module

- CRUD operations for managing projects
- Exception handling for error scenarios
- Unit and integration testing
- Swagger documentation for API endpoints

### 2. User Authentication Module (Future Enhancement)

- User authentication and authorization
- Secure endpoints based on user roles
- User registration and login functionality

## Build and Run

To build the project, use Maven:

```bash
mvn clean install
