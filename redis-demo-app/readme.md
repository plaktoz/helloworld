# Spring Boot Redis Demo Application

This Spring Boot application demonstrates how to integrate with Redis for performing basic operations. It uses Redis as a database to store and retrieve data.

## Getting Started

These instructions will guide you through getting a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java JDK 1.8 or later
- Maven 3.2+
- Docker (for running Redis in a Docker container)

### Setting Up Redis with Docker

If you don't have Redis installed on your local machine, you can easily set it up using Docker. Run the following command to start a Redis container:

```bash
docker network create -d bridge redisnet
docker run -d -p 6379:6379 --name myredis --network redisnet redis
```

Flushing up the database

```bash
flushdb
```


## Testing the app
### 1. CURL to setup new user
```bash
curl -X POST "http://localhost:8080/user?id=1" \
  -H "Content-Type: application/json" \
  -d '{
        "firstName": "Mohammed",
        "lastName": "Ahern",
        "email": "mahern0@amazon.com"
      }'
```

### 2. CURL to get user name
```bash
curl http://localhost:8080/user/1
Mohammed
```

## Configure with password
Configuring Redis to require a password for clients to connect is a security measure that helps protect your data. The password configuration is done in the Redis configuration file (usually named `redis.conf`). Here are the steps to set up a password (also known as "requirepass") for Redis:

### 1. Locate Redis Configuration File

The Redis configuration file is typically named `redis.conf`. Its location may vary based on how Redis was installed and your operating system. Common locations include:

- `/etc/redis/redis.conf` (common in Linux)
- A directory inside your Redis installation folder

### 2. Edit the Redis Configuration File

Open `redis.conf` in a text editor with administrative or root privileges. Find the `requirepass` directive. If it's not present, you can add it; if it's commented out (with a `#` at the beginning), uncomment it.

```conf
requirepass yourpassword
```

Replace `yourpassword` with a strong, secure password. 

### 3. Restart Redis

After saving changes to `redis.conf`, restart the Redis server for the changes to take effect. The command to restart Redis depends on your system; commonly, it's one of the following:

```bash
sudo service redis-server restart
# or
sudo systemctl restart redis
```

### 4. Connect to Redis with Password

Once you have set the password, clients must authenticate to Redis using the `AUTH` command followed by the password. Here’s how you can do this:

- **Redis CLI**:
  
  ```bash
  redis-cli
  AUTH yourpassword
  ```

- **In Application Code**:
  
  You'll need to modify your application's Redis configuration to include the password. For example, in a Spring Boot application, you would add the following to your `application.properties` or `application.yml`:

  ```properties
  # application.properties
  spring.redis.password=yourpassword
  ```

  ```yaml
  # application.yml
  spring:
    redis:
      password: yourpassword
  ```

### Security Considerations

- **Strong Password**: Choose a strong and complex password to secure your Redis instance.
- **Limit Access**: Besides setting a password, consider other security measures like binding Redis to `127.0.0.1` (to prevent external access) and using firewalls to restrict access.
- **Secure Config File**: The `redis.conf` file should be properly secured, as it contains sensitive information.
- **Transport Security**: Redis does not encrypt traffic; everything is sent in plain text. For production environments, consider securing traffic, for example, by using an SSH tunnel or stunnel.

Remember that adding a password is just one aspect of securing a Redis instance. Comprehensive security involves a combination of measures including network configuration, access control, and regular security audits.