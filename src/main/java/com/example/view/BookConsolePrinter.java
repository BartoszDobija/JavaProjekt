package com.example.view;

import com.example.viewModel.BookViewModel;
import com.example.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookConsolePrinter {

    private BookViewModel book;

    public BookConsolePrinter(Book book) {
        this.book = new BookViewModel(book);
    }

    public void printBook() {
        System.out.println( book.getTitle() + "\" by " + book.getAuthor() + " [" + book.getReleaseDate() + "]");
    }
}
