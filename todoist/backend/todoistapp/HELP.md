# TodoistApp - Spring Boot + Docker Hello World

This is a minimal Spring Boot application that returns `Hello, World!` on the root endpoint (`/`). It is containerized
using Docker and can be run locally.

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
mvn clean package && \
docker build -t todoistapp . && \
docker stop todoistapp || true && \
docker rm todoistapp || true && \
docker run -d -p 8080:8080 --name todoistapp todoistapp && \
sleep 5s && \
curl http://localhost:8080/
