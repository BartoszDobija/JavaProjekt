package com.example;

public class BookViewModel{
    String Title;
    String Author;
    String Genre;
    int ReleaseDate;

    public BookViewModel(Book book){
        this.Title = book.Title;
        this.Author = book.Author;
        this.Genre = book.Genre;
        this.ReleaseDate = book.ReleaseDate;
    }
}