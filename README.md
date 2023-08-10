# Library

To successfully launch the application you need to have:
- Java 17
- Maven
- MariaDB 10.6.12 

## MariaDB locally

If you have installed MariaDB locally and using root password as authentication

Open CLI Client:
- `sudo mysql` or `sudo mariadb`

After authenticating:
```SQL
CREATE DATABASE IF NOT EXISTS library;

USE library

CREATE USER 'library'@'localhost' IDENTIFIED BY 'library';
GRANT ALL PRIVILEGES ON library.* TO 'library'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title NVARCHAR(255),
    author NVARCHAR(255),
    genre NVARCHAR(50),
    releaseYear INT
);

INSERT INTO books (title, author, genre, releaseYear)
VALUES
	('Anna Karenina', 'Leo Tolstoy', 'Fiction', 1877),
	('To Kill a Mockingbird', 'Harper Lee', 'Coming-of-age story', 1960),
	('The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 1925);

```

Now you have database library with user `library` with password `library` and table books with some data. 
You should make sure that in `works.buddy.library.config.HibernateConfig` you have selected `hibernate.properties` as PropertySource.

## MariaDB on Docker

If you have Docker it can be faster to run the container with MariaDB:
- Run instance named mariadb with `test` as root password `docker run --name mariadb -e MYSQL_ROOT_PASSWORD=test -d mariadb:10.6.12`
- Get IP address of your container `docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mariadb`
- To get inside container use `docker exec -it mariadb bash` 

Inside you can now access MariaDB:
- `mysql -p` or `mariadb -p` and enter `test` as password

Now execute similar script as with local installation but user is now `'library'@'%'`
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

INSERT INTO books (title, author, genre, releaseYear)
VALUES
	('Anna Karenina', 'Leo Tolstoy', 'Fiction', 1877),
	('To Kill a Mockingbird', 'Harper Lee', 'Coming-of-age story', 1960),
	('The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 1925);

```

Now you have database library with user `library` with password `library` and table books with some data.
You should make sure that in `works.buddy.library.config.HibernateConfig` you have selected `dockerMariaDB.properties` as PropertySource and changed IP 
address to correct one from `docker inspect` command earlier.

## Building and running

To build application execute command `mvn clean install` in root folder of this project. To run the application in the same place execute `java -jar 
target/library-1.0-SNAPSHOT.jar`