package com.example.view;

import com.example.model.Book;
import com.example.viewModel.BookViewModel;

public class BookConsolePrinter {

    private BookViewModel book;

    public BookConsolePrinter(Book book) {
        this.book = new BookViewModel(book);
    }

    public void printBook() {
        System.out.println("\"" + book.getTitle() + "\" by " + book.getAuthor() + " [" + book.getReleaseDate() + "]");
    }

    public void shortPrintBook(int i) {
        System.out.println(i + ". " + book.getTitle());
    }
}
