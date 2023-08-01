package com.example;

public class BookViewModel{
    String Title;
    String Author;
    String Genre;
    int ReleaseDate;

    public BookViewModel(Book book){
        this.Title = book.title;
        this.Author = book.author;
        this.Genre = book.genre;
        this.ReleaseDate = book.releaseDate;
    }
}