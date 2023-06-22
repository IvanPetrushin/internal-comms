## Web application written in Java and JavaScript is used for internal communication, assignment and prioritization of tasks, data exchange and reporting on the work among the employees.
### MVC architecture pattern.
## Technology stack
* Spring Framework
* Rest API
* PostgreSQL
* Gradle

### Needed env variables
```
spring.datasource.username
spring.datasource.password
```

### back-end:
1. Connect PostgreSQL DB
2. Build with Gradle
```
 ./gradlew build
```
3. Run locally with configuration:
```
-Djava.rmi.server.hostname=localhost
internalcomms.InternalCommsApplication
```
4. Check locally
```
http://localhost:8080/
```
### Containerization - [DockerHub](https://hub.docker.com/r/nikitamakoveev/internal-comms2)
1. Download [docker-compose.yml](dockerFile/docker-compose.yml)
2. Run in cmd
```
docker compose build
docker compose up
```
3. Check locally
```
http://localhost:8080/
```
