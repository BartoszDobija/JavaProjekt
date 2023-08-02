package com.example;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<BookViewModel> books;

    public Library(List<Book> originalBooks) {
        this.books = new ArrayList<>();

        BookViewModel book;
        for (Book originalBook : originalBooks) {
            book = new BookViewModel(originalBook);
            this.books.add(book);
        }
    }

    public void displayLibrary() {
        for (BookViewModel book : books) {
            System.out.println(book.genre + ": \"" + book.title + "\" by " + book.author + " [" + book.ReleaseDate + "]");
        }
    }
}
