# Library

To successfully launch the application you need to have:

- Java 17
- Maven
- MariaDB 10.6.12
- Docker and Docker Compose

## Building and running

1) Enter root folder of this application
2) Execute `mvn clean install`
3) Execute `docker compose up`

To access the app, execute `docker attach javaprojekt-app` in a separate terminal window, then press ENTER.

## Container removal

You can remove the containers and volumes using `docker compose down -v` (Images must be disposed separately).
