# Library

To successfully launch the application you need to have:
- Java 17
- Maven
- MariaDB 10.6.12 
- Docker and Docker Compose

## Database setup

In the root folder of project run `docker compose up -d` and wait for tasks to finish.

## Building and running

To build application execute command `mvn clean install` in root folder of this project. To run the application in the same place execute `java -jar 
target/library.jar`

## Database container removal

You can remove the container and volumes using `docker compose down -v` (image must be disposed separately).