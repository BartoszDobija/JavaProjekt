package works.buddy.examples.library.view;

import works.buddy.examples.library.model.Book;
import works.buddy.examples.library.utility.ConsoleInputValidation;

import java.util.Scanner;
public class BookAddDialog {
    public static Book createBookDialog(Scanner scanner) {
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
