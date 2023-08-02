package com.example;

public class Book {

    int id;

    String title;

    String author;

    String genre;

    int releaseDate;

    String content = "Lorem Ipsum itd itp tutaj tresc ksiazki";

    Book() {

    }

    Book(int id, String title, String author, String genre, int releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }
}
