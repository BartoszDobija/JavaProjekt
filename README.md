# Library

## Requirements:

- Java 17
- Maven
- MariaDB 10.6.12
- Docker and Docker Compose

## Development Environment (database etc.)

Starting: `docker compose -f app/library-dev.yaml up -d`

Stopping: `docker compose -f app/library-dev.yaml down -v`

## Building

`mvn clean install`

## Running

`java -jar target/library.jar`


## dbmDiff

 `mvn clean install && java -jar target/dbmDiff.jar diff.xml`