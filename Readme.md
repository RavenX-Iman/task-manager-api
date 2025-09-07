# Task Manager API

This is a **Java-based RESTful API** for managing tasks, built as a mini-project experiment to explore backend development. The application is developed using **Spring Boot 3.x** and **Java 21**, with an **H2 in-memory database** for data storage. The API supports full CRUD operations, input validation, and global error handling, tested using **Thunder Client** and **Postman**.

---

## Features

- Create, read, update, and delete tasks
- Input validation using Bean Validation (`@NotBlank`, `@Size`)
- Global error handling with `@ControllerAdvice`
- Automatic timestamp generation (`createdAt`, `updatedAt`)
- Lightweight and self-contained for experimentation
- Fully testable via Thunder Client or Postman

---

## Technologies Used

### Programming Languages

- Java 21

### Framework

- Spring Boot 3.x

### Database

- H2 Database (in-memory)

### Testing Tool

- Thunder Client

---

## Folder Structure

```plaintext
task-manager-api
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── main
│   │   ├── java/com/iman/taskmanager
│   │   │   ├── TaskManagerApiApplication.java
│   │   │   ├── controller
│   │   │   │   └── TaskController.java
│   │   │   ├── entity
│   │   │   │   ├── Task.java
│   │   │   │   └── TaskStatus.java
│   │   │   ├── exception
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── repository
│   │   │       └── TaskRepository.java
│   │   └── resources
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java/com/iman/taskmanager
│           └── TaskManagerApiApplicationTests.java
└── target
    └── (compiled classes and build artifacts)
```

### Explanation of Key Directories

- `src/main/java/com/iman/taskmanager`: Core Java package containing application logic
- `controller`: Handles HTTP requests and responses
- `entity`: Defines data models (`Task`, `TaskStatus`)
- `exception`: Implements global error handling
- `repository`: Manages database interactions
- `src/main/resources`: Static assets and configuration files
- `src/test`: Unit and integration tests
- `target`: Compiled class files and build artifacts

---

## Prerequisites

- Java Development Kit (JDK) 21
- Maven (included as wrapper `mvnw`)

---

## How to Run

1. Clone this repository to your local machine.

2. Generate the project zip file from [start.spring.io](https://start.spring.io/)

   - **Project**: Maven Project (zip)
   - **Language**: Java 21
   - **Dependencies**: Spring Web, Spring Data JPA, H2 Database

3. Extract to: '[YOUR_LOCAL_DIRECTORY]/task-manager-api'

4. Run the application:

   ```bash
   ./mvnw spring-boot:run   # Linux/Mac
   .\mvnw spring-boot:run  # Windows
   ```

---

## API Documentation

### Base URL

```
http://localhost:8080/api/tasks
```

### Endpoints

#### 1. Get All Tasks

- **Method**: GET

- **URL**: `/api/tasks`

- **Description**: Fetches all tasks from the database.

- **Success Response (200 OK)**:

  ```json
  [
    {
      "id": 1,
      "title": "Learn Spring Boot",
      "description": "CRUD practice",
      "status": "PENDING",
      "createdAt": "2025-09-07T13:43:20.970725",
      "updatedAt": "2025-09-07T14:00:40.8541378"
    }
  ]
  ```

#### 2. Get Task by ID

- **Method**: GET

- **URL**: `/api/tasks/{id}`

- **Description**: Fetches a single task by its ID.

- **Success Response (200 OK)**:

  ```json
  {
    "id": 1,
    "title": "Learn Spring Boot",
    "description": "CRUD practice",
    "status": "PENDING",
    "createdAt": "2025-09-07T13:43:20.970725",
    "updatedAt": "2025-09-07T14:00:40.8541378"
  }
  ```

- **Error Response (404 Not Found)**:

  ```json
  {
    "timestamp": "2025-09-07T14:20:00",
    "status": 404,
    "error": "Not Found",
    "path": "/api/tasks/99"
  }
  ```

#### 3. Create New Task

- **Method**: POST

- **URL**: `/api/tasks`

- **Description**: Creates a new task.

- **Request Body**:

  ```json
  {
    "title": "Learn Spring Boot",
    "description": "CRUD practice",
    "status": "PENDING"
  }
  ```

- **Success Response (201 Created)**:

  ```json
  {
    "id": 1,
    "title": "Learn Spring Boot",
    "description": "CRUD practice",
    "status": "PENDING",
    "createdAt": "2025-09-07T13:43:20.970725",
    "updatedAt": "2025-09-07T13:43:20.970725"
  }
  ```

- **Error Response (400 Bad Request)**:

  ```json
  {
    "timestamp": "2025-09-07T14:15:00",
    "status": 400,
    "errors": [
      "title: Task title is required"
    ]
  }
  ```

#### 4. Update Existing Task

- **Method**: PUT

- **URL**: `/api/tasks/{id}`

- **Description**: Updates an existing task by its ID.

- **Request Body**:

  ```json
  {
    "title": "Learn Spring Boot",
    "description": "Updated description",
    "status": "IN_PROGRESS"
  }
  ```

- **Success Response (200 OK)**:

  ```json
  {
    "id": 1,
    "title": "Learn Spring Boot",
    "description": "Updated description",
    "status": "IN_PROGRESS",
    "createdAt": "2025-09-07T13:43:20.970725",
    "updatedAt": "2025-09-07T14:00:40.8541378"
  }
  ```

- **Error Responses**:

  - **400 Bad Request** (validation error):

    ```json
    {
      "timestamp": "2025-09-07T14:15:00",
      "status": 400,
      "errors": [
        "title: Task title is required"
      ]
    }
    ```

  - **404 Not Found** (task ID does not exist):

    ```json
    {
      "timestamp": "2025-09-07T14:16:00",
      "status": 404,
      "error": "Not Found",
      "path": "/api/tasks/99"
    }
    ```

#### 5. Delete Task

- **Method**: DELETE

- **URL**: `/api/tasks/{id}`

- **Description**: Deletes a task by its ID.

- **Success Response (204 No Content)**: (Empty body)

- **Error Response (404 Not Found)**:

  ```json
  {
    "timestamp": "2025-09-07T14:20:00",
    "status": 404,
    "error": "Not Found",
    "path": "/api/tasks/99"
  }
  ```

#### 6. Get Task Count

- **Method**: GET

- **URL**: `/api/tasks/count`

- **Description**: Returns the total number of tasks in the database.

- **Success Response (200 OK)**:

  ```
  Total tasks: 5
  ```

### Validation Rules

- **title**: Required, max 100 characters
- **description**: Optional, max 500 characters
- **status**: Must be one of `PENDING`, `IN_PROGRESS`, `COMPLETED`

---

## Screenshots

> **Note**: Screenshots are not available for this API project. You can test the API endpoints using Thunder Client or Postman to visualize the responses.

---

## Authors

**Iman Tahir**