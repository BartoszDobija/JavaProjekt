package works.buddy.examples.library.controller;

import works.buddy.examples.library.data.InitialData;
import works.buddy.examples.library.service.BookService;
import works.buddy.examples.library.utility.ConsoleInputValidation;
import works.buddy.examples.library.view.BookAddDialog;
import works.buddy.examples.library.view.BookConsolePrinter;
import works.buddy.examples.library.view.BookFindDialog;

import java.util.Scanner;

import static works.buddy.examples.library.view.MessageConsolePrinter.printMessage;

public class LibraryApp {

    private final BookService bookService;

    private final Scanner scanner;

    public LibraryApp() {
        this.bookService = new BookService(new InitialData().getSampleBooks());
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            running = execute();
        }
    }

    public boolean execute() {
        printMessage("welcome");
        boolean running = true;
        int decision = ConsoleInputValidation.inputNumber(scanner);
        switch (decision) {
            case 1 -> listBookStore();
            case 2 -> addBookToStore();
            case 3 -> findBookById();
            case 4 -> running = false;
            default -> printMessage("error");
        }
        return running;
    }

    private void findBookById() {
        printMessage("findById");
        listBookTitles();
        int id = BookFindDialog.getBookId(scanner);
        BookConsolePrinter.printBook(bookService.findById(id - 1));
    }

    private void addBookToStore() {
        printMessage("addBook");
        bookService.add(BookAddDialog.createBookDialog(scanner));
    }

    public void listBookStore() {
        printMessage("showLibrary");
        for (int i = 0; i < bookService.getBookStoreSize(); i++) {
            BookConsolePrinter.printBook(bookService.findById(i));
        }
    }

    private void listBookTitles() {
        for (int i = 0; i < bookService.getBookStoreSize(); i++) {
            BookConsolePrinter.printBookTitle(bookService.findById(i), i + 1);
        }
    }
}
