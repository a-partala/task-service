# Task Service
REST microservice for task management in a distributed system.

This service is a part of a multi-service architecture (with user-service as an authentication provider).
Implements business logic of creating, assigning, starting, and completing tasks.

## Technologies
Java 21, Spring Boot (Web, Security, Data JPA), PostgreSQL, Docker, JUnit, Mockito, Maven, Lombok

## Functionality
- Task lifecycle (create, start, complete, update, delete)
- JWT authentication
- Role-based authorization
- Task filtering with pagination and multiple parameters
- Clean architecture: controller -> service -> repository

## Package structure
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

## API endpoints
GET /tasks?...              - search tasks with filters: creatorId, assignedUserId, status, priority, pageSize, pageNum
GET /tasks/{id}             - get task by id
POST /tasks                 - create task
POST /tasks/{id}/start      - start task or assign user to it
POST /tasks/{id}/complete   - mark task as completed
PUT /tasks/{id}             - change task data
DELETE /tasks/{id}          - delete task

## Run locally
Prerequisite:
- PostgreSQL running locally
mvn clean install
mvn spring-boot:run

## Request Examples
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
