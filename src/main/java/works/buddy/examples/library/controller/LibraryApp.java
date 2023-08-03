package works.buddy.examples.library.controller;

import works.buddy.examples.library.data.InitialData;
import works.buddy.examples.library.utility.ConsoleInputValidation;
import works.buddy.examples.library.view.BookAddDialog;
import works.buddy.examples.library.view.BookConsolePrinter;
import works.buddy.examples.library.view.BookFindDialog;

import java.util.Scanner;

import static works.buddy.examples.library.view.MessageConsolePrinter.printMessage;

public class LibraryApp {

    private BookStore bookStore;

    private Scanner scanner;

    public LibraryApp() {
        this.bookStore = new BookStore(new InitialData().getSampleBooks());
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
        switch (ConsoleInputValidation.inputNumber(scanner)) {
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
        BookConsolePrinter bookConsolePrinter;
        for (int i = 0; i < bookStore.getBookStoreSize(); i++) {
            bookConsolePrinter = new BookConsolePrinter(bookStore.findById(i));
            bookConsolePrinter.shortPrintBook(i + 1);
        }
        BookFindDialog bookFindDialog = new BookFindDialog(scanner);
        int id = bookFindDialog.getBookId();
        bookConsolePrinter = new BookConsolePrinter(bookStore.findById(id - 1));
        bookConsolePrinter.printBook();
    }

    private void addBookToStore() {
        printMessage("addBook");
        BookAddDialog bookAddDialog = new BookAddDialog(scanner);
        bookStore.add(bookAddDialog.createBook());
    }

    public void listBookStore() {
        printMessage("showLibrary");
        BookConsolePrinter bookConsolePrinter;
        for (int i = 0; i < bookStore.getBookStoreSize(); i++) {
            bookConsolePrinter = new BookConsolePrinter(bookStore.findById(i));
            bookConsolePrinter.printBook();
        }
    }
}
