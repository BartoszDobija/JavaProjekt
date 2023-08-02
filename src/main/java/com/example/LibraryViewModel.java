package com.example;

import java.util.ArrayList;
import java.util.List;

public class LibraryViewModel {

    private List<BookViewModel> library;

    public LibraryViewModel(List<Book> originalBooks) {
        this.library = new ArrayList<>();

        BookViewModel book;
        for (Book originalBook : originalBooks) {
            book = new BookViewModel(originalBook);
            this.library.add(book);
        }
    }

    public void displayLibrary() {
        for (BookViewModel book : library) {
            System.out.println(book.Genre + ": \"" + book.Title + "\" by " + book.Author + " [" + book.ReleaseDate + "]");
        }
    }
}
