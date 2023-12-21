


# Spring Boot MongoDB Demo Application

This is a Spring Boot application that demonstrates basic CRUD operations on a MongoDB database. It uses a MongoDB database to store user information.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java JDK 1.8 or later
- Maven 3.2+
- MongoDB running on localhost (default port 27017)

### Setting Up MongoDB with Docker

If you don't have MongoDB installed on your local machine, you can quickly set it up using Docker. Run the following command to start a MongoDB container:

```bash
docker run --name mongodb -d -p 27017:27017 mongo
```

### Installing

To get the application running, clone the repository and execute the following commands:

```bash
mvn clean install
mvn spring-boot:run
```

This will start the application on `localhost:8080`.

## API Endpoints

The application exposes several RESTful endpoints to interact with the MongoDB database.

### Create a New User

To create a new user, use the following `curl` command:

```bash
curl -X POST http://localhost:8080/users \
  -H 'Content-Type: application/json' \
  -d '{"name": "John Doe", "age": 30, "address": "123 Main St"}'
```

### Find User by Name

To retrieve a user by name:

```bash
curl http://localhost:8080/users/{name}
```

Replace `{name}` with the name of the user.

### Update User Address

To update a user's address:

```bash
curl -X PUT http://localhost:8080/users/{name} \
  -H 'Content-Type: application/json' \
  -d '"New Address St"'
```

Replace `{name}` with the name of the user and `New Address St` with the new address.

### Delete a User

To delete a user by name:

```bash
curl -X DELETE http://localhost:8080/users/{name}
```

Replace `{name}` with the name of the user.

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
- [Maven](https://maven.apache.org/) - Dependency Management
- [MongoDB](https://www.mongodb.com/) - Database

## Author

* **Your Name**

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
```

Replace "Your Name" with your actual name or your GitHub username. You can also add more sections as needed, like `Contributing`, `Versioning`, `Tests`, etc., depending on the complexity and requirements of your project.

Remember, a good `README.md` is invaluable for any project, especially open-source ones, as it's often the first thing users and contributors will look at.