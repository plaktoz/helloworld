# TodoistApp - Spring Boot + Docker Hello World

This is a minimal Spring Boot application that returns `Hello, World!` on the root endpoint (`/`). It is containerized using Docker and can be run locally.

---

## 🛠 Prerequisites

- Java 21+
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

# Freq used commands
```bash
cd backend;\
mvn install -pl todoistapp -am -DskipTests;\
cd ..;\
docker container stop todoistapp;\
docker container rm todoistapp;\
docker image rm todoistapp-image;\
docker-compose up -d --no-deps todoistapp;\
sleep 10;
curl http://localhost:8080/api/tasks/1;\
echo done;
```

# Docker
```bash
# Docker commands
## build & start
docker compose up --build -d

## logs (handy while waiting for health checks)
docker compose logs -f

## stop & remove (keep volumes)
docker compose down

## nuke everything including data volumes
docker compose down --volumes --remove-orphans
```
# Maven build
```bash
# mvn install command
# install all from parent
mvn clean install
# install module from parent
mvn install -pl commonlib -am
mvn install -pl todoistapp -am
mvn install -pl adminapp -am
```

# MYSQL
```bash
## MYSQL
## login to mssql in docker
docker exec -it mysql mysql -utodo -ptodo_pwd -D todoist
use todoist;
show tables;
describe task;
select * from task;

## check keycloak
docker exec -it mysql mysql -uroot -proot_pwd -e "SHOW DATABASES LIKE 'keycloakdb';"
docker exec -it mysql mysql -uroot -proot_pwd -e "SELECT user,host FROM mysql.user WHERE user='keycloakdbuser';"
```

# REDIS
```bash
docker exec -it redis redis-cli
KEYS "task:1"
get "task:1"
```

# MONGODB
```bash
docker exec -it mongo mongosh \
  --username mongoadmin \
  --password mongopwd \
  --authenticationDatabase admin
## common clis
```

# KAFKA produce and consume message
```bash
docker exec kafka kafka-topics --bootstrap-server localhost:9092 --create --topic task_topic --partitions 1 --replication-factor 1
WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
Created topic task_topic.
docker exec kafka kafka-topics --bootstrap-server localhost:9092 --list                                                        
my_new_topic

docker exec -it kafka bash
/usr/bin/kafka-console-producer --bootstrap-server localhost:9092 --topic task_topic
/usr/bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic task_topic

/opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
```

# Curl
```bash
# Task CURL command

# with nginx
curl http://localhost/api/todoistapp/api/tasks
curl http://localhost/api/todoistapp/api/call-app2

curl -X POST http://localhost/api/todoistapp/api/tasks \
  -H 'Content-Type: application/json' \
  -d '{"taskSummary":"demo","startDate":"2025-08-10T10:00:00"}'

# without nginx
# test command
curl http://localhost:8080/api/call-app2
docker exec -it app1 curl http://app2:8080/hello

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
```