# TodoistApp - Spring Boot + Docker Hello World

This is a minimal Spring Boot application that returns `Hello, World!` on the root endpoint (`/`). It is containerized using Docker and can be run locally.

---

## 🛠 Prerequisites

- Java 17+
- Maven 3.x
- Docker

---

## 🚀 Build and Run

Use the following single-line terminal command to:

1. Clean and build the Spring Boot JAR
2. Build the Docker image
3. Stop and remove any existing container
4. Run the container
5. Wait 5 seconds
6. Curl the endpoint to verify the app is working

```bash
# build & start
docker compose up --build -d
# when updating only 1 container
docker-compose up -d --no-deps todoistapp

# logs (handy while waiting for health checks)
docker compose logs -f

# stop & remove (keep volumes)
docker compose down

# nuke everything including data volumes
docker compose down --volumes --remove-orphans

# mvn install command
mvn clean install
mvn install -pl admin-app -am

# test
curl http://localhost:8080/api/call-app2
docker exec -it app1 curl http://app2:8080/hello

# Task CURL command
## Create
curl -X POST http://localhost:8080/api/tasks \
  -H 'Content-Type: application/json' \
  -d '{"taskSummary":"demo","startDate":"2025-08-10T10:00:00"}'

## List
curl http://localhost:8080/api/tasks

## Get one
curl http://localhost:8080/api/tasks/1

## Update
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H 'Content-Type: application/json' \
  -d '{"id":1, "taskSummary":"updated"}'

## Delete
curl -X DELETE http://localhost:8080/api/tasks/1

# MYSQL
## login to mssql in docker
docker exec -it mysql mysql -utodo -ptodo_pwd -D todoist
```
# test