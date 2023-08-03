package works.buddy.examples.library.view;

import works.buddy.examples.library.model.Book;
import works.buddy.examples.library.utility.ConsoleInputValidation;

import java.util.Scanner;


public class BookAddDialog {

    private Scanner scanner;

    public BookAddDialog(Scanner scanner) {
        this.scanner = scanner;
    }

    public Book createBook() {
        Book book = new Book();
        System.out.println("Adding new book");
        System.out.println("Author name:");
        book.setAuthor(scanner.nextLine());
        System.out.println("Book title:");
        book.setTitle(scanner.nextLine());
        System.out.println("Book genre:");
        book.setGenre(scanner.nextLine());
        System.out.println("Release date:");
        book.setReleaseDate(ConsoleInputValidation.inputNumber(scanner));
        return book;
    }

}
