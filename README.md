# Library

To successfully launch the application you need to have:
- Java 17
- Maven
- MariaDB 10.6.12 
- Docker and Docker Compose

## Database setup

In the root folder of project run `docker compose up -d` and wait for tasks to finish.

When you are first launching application change `hibernate.hbm2ddl.auto=none` to `hibernate.hbm2ddl.auto=create` so it generates required tables.

## Building and running

To build application execute command `mvn clean install` in root folder of this project. To run the application in the same place execute `java -jar 
target/library.jar`

## Database container removal

You can remove the container and volumes using `docker compose down -v` (image must be disposed separately).

## Generating changelog

Using liquibase commands you can easily generate changelog representing current state of database. In root folder of the project execute `liquibase generate-changelog --defaults-file ./src/main/resources/liquibase.properties
` and generatedChangelog.xml file will appear in resources/dbm folder where you can take relevant parts of changelog.