package com.example.controller;

import com.example.data.InitialData;
import com.example.model.Book;
import com.example.view.BookConsolePrinter;
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

    public void begin() {
        messageConsolePrinter.printMessage("welcome");

        switch (scanner.nextLine()){
            case "1" -> listBookStore();
            default -> messageConsolePrinter.printMessage("error");
        }
    }

    public void listBookStore(){
        messageConsolePrinter.printMessage("showLibrary");
        BookConsolePrinter bookConsolePrinter;
        for (Book book: bookStore.books             ) {
            bookConsolePrinter = new BookConsolePrinter(book);
            bookConsolePrinter.printBook();
        }
    }
}
