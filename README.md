# Library

To successfully launch the application you need to have:

- Java 17
- Maven
- MariaDB 10.6.12
- Docker and Docker Compose

## Database setup

In the root folder of project run `docker compose -f app/library-dev.yaml up -d` and wait for tasks to finish.

## Building and running

To build and run application execute command `mvn clean install && java -jar target/library.jar`

## Database container removal

You can remove the container and volumes using `docker compose -f app/library-dev.yaml down -v` (image must be disposed separately).

## Generating diff changelog

To create changelog containing all differences between model generated by Hibernate and current state of database simply
run `mvn clean install && java -jar target/dbmDiff.jar diff.xml
`. After executing the command diff.xml will be generated in root folder of the project.
If there are any changesets you should copy them to changelog.xml.