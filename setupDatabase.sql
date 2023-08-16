CREATE DATABASE IF NOT EXISTS library;

USE library;

CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title NVARCHAR(255),
    author NVARCHAR(255),
    genre NVARCHAR(50),
    releaseYear INT
);

CREATE USER 'library'@'db' IDENTIFIED BY 'library';
GRANT ALL PRIVILEGES ON library.* TO 'library'@'db';
FLUSH PRIVILEGES;