package com.example.controller;

import com.example.data.InitialData;
import com.example.model.Book;
import com.example.view.BookAddDialog;
import com.example.view.BookConsolePrinter;
import com.example.view.BookFindDialog;
import com.example.view.MessageConsolePrinter;

import java.util.Scanner;

public class BookController {

    private BookStore bookStore;

    private MessageConsolePrinter messageConsolePrinter;

    private Scanner scanner;

    public BookController() {
        this.bookStore = new BookStore(new InitialData().getSampleBooks());
        this.messageConsolePrinter = new MessageConsolePrinter();
        this.scanner = new Scanner(System.in);
    }

    public boolean begin() {
        messageConsolePrinter.printMessage("welcome");
        boolean running = true;
        switch (scanner.nextLine()) {
            case "1" -> listBookStore();
            case "2" -> addBookToStore();
            case "3" -> findBookById();
            case "4" -> running = false;
            default -> messageConsolePrinter.printMessage("error");
        }
        return running;
    }

    private void findBookById() {
        messageConsolePrinter.printMessage("showById");
        for (int i = 0; i < bookStore.books.size(); i++) {
            BookConsolePrinter bookConsolePrinter = new BookConsolePrinter(bookStore.books.get(i));
            bookConsolePrinter.shortPrintBook(i + 1);
        }
        BookFindDialog bookFindDialog = new BookFindDialog(scanner);
        int id = bookFindDialog.getBookId();
        BookConsolePrinter bookConsolePrinter = new BookConsolePrinter(bookStore.showBook(id - 1));
        bookConsolePrinter.printBook();
    }

    private void addBookToStore() {
        BookAddDialog bookAddDialog = new BookAddDialog(scanner);
        bookStore.addBook(bookAddDialog.createBook());
    }

    public void listBookStore() {
        messageConsolePrinter.printMessage("showLibrary");
        BookConsolePrinter bookConsolePrinter;
        for (Book book : bookStore.books) {
            bookConsolePrinter = new BookConsolePrinter(book);
            bookConsolePrinter.printBook();
        }
    }
}
