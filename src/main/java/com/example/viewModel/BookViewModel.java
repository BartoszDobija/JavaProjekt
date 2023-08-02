package com.example.viewModel;

import com.example.model.Book;

public class BookViewModel {

    private String title;

    private String author;

    private int releaseDate;

    public BookViewModel(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.releaseDate = book.getReleaseDate();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }
}