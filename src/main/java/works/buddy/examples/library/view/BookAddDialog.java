package works.buddy.examples.library.view;

import works.buddy.examples.library.model.Book;

import java.util.Scanner;

import static works.buddy.examples.library.utilities.ConsoleInputValidation.inputNumber;

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
        book.setReleaseDate(inputNumber(scanner));
        return book;
    }

}
