# Task Service
REST microservice for task management in a distributed system.

> **Note:** This service is designed to run within the [Orchestra](https://github.com/a-partala/orchestra) ecosystem.<br><br>
> Please follow the **[Quick Start Guide](https://github.com/a-partala/orchestra#quick-start)** to spin up the system.

## Technologies
Java 21, Spring Boot (Web, Security, Data JPA), PostgreSQL, Docker, JUnit, Mockito, Maven, Lombok

## Functionality
- Task lifecycle (create, start, complete, update, delete)
- JWT authentication
- Role-based authorization
- Task filtering with pagination and multiple parameters
- Clean architecture: controller -> service -> repository

## Package structure
```
net.partala.taskservice
├── auth/
│   └── jwt/
├── config/
├── dto/
│   ├── request/
│   └── response/
├── exception/
├── task/
└── user/
```

## API endpoints
```
GET /tasks?...              - search tasks with filters: creatorId, assignedUserId, status, priority, pageSize, pageNum
GET /tasks/{id}             - get task by id
POST /tasks                 - create task
POST /tasks/{id}/start      - start task or assign user to it
POST /tasks/{id}/complete   - mark task as completed
PUT /tasks/{id}             - change task data
DELETE /tasks/{id}          - delete task
```

## Request Examples
```
POST /tasks
{
  "title": "create front-end",
  "deadlineDate": "2026-10-10T00:00:00",
  "priority": "MEDIUM"
}

POST /tasks/{id}/start
{
  "userId": 1
}

PUT /tasks/{id}
{
  "title": "update front-end",
  "deadlineDate": "2028-10-10T00:00:00",
  "priority": "HIGH"
}
```
