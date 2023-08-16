# Library

To successfully launch the application you need to have:

- Java 17
- Maven
- MariaDB 10.6.12
- Docker and Docker Compose

## Database setup

Within root folder of this app, run `docker compose up -d` and wait until the database is ready.

## Build and run

To build the app, run `mvn clean install`. You can start it using `java -jar target/library.jar`

## Database container removal

You can remove the container and volumes using `docker compose down -v` (image must be disposed separately).
