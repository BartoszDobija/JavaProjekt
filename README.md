# Library

To successfully launch the application you need to have:
- Java 17
- Maven
- MariaDB 10.6.12 

## MariaDB on Docker

If you have Docker it can be faster to run the container with MariaDB:
- Run instance named mariadb with `test` as root password 
`docker run --name mariadb -e MYSQL_ROOT_PASSWORD=test -e MYSQL_TCP_PORT=3303 --network="host" -d mariadb:10.6.12`
- To get inside container use `docker exec -it mariadb bash` 

Inside you can now access MariaDB:
- `mysql -p` or `mariadb -p` and enter `test` as password

```SQL
CREATE DATABASE IF NOT EXISTS library;

USE library

CREATE USER 'library'@'%' IDENTIFIED BY 'library';
GRANT ALL PRIVILEGES ON library.* TO 'library'@'%';
FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title NVARCHAR(255),
    author NVARCHAR(255),
    genre NVARCHAR(50),
    releaseYear INT
);
```

Now you have database library with user `library` with password `library` and table books with some data.
You should make sure that in `works.buddy.library.config.HibernateConfig` you have selected `hibernate.properties` as PropertySource.

## Building and running

To build application execute command `mvn clean install` in root folder of this project. To run the application in the same place execute `java -jar 
target/library-1.0-SNAPSHOT.jar`