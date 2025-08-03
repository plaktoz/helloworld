cd adminapp;mvn clean package;cd ..;
cd todoistapp;mvn clean package;
docker compose up --build -d

curl http://localhost:8080/api/call-app2 

docker compose down --volumes --remove-orphans