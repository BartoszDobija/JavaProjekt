package works.buddy.library.services;

import works.buddy.library.model.Book;

import java.util.Scanner;

import static works.buddy.library.services.Messages.getMessage;

public class ConsoleDisplay implements Display {

    private final Scanner scanner;

    public ConsoleDisplay(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Book addBook() {
        System.out.println(getMessage("addBookIntro"));
        Book book = new Book();
        System.out.println(getMessage("bookDataAuthor"));
        book.setAuthor(scanner.nextLine());
        System.out.println(getMessage("bookDataTitle"));
        book.setTitle(scanner.nextLine());
        System.out.println(getMessage("bookDataGenre"));
        book.setGenre(scanner.nextLine());
        System.out.println(getMessage("bookDataRelease"));
        book.setReleaseYear(getInputNumber());
        return book;
    }

    @Override
    public int findBook() {
        System.out.println(getMessage("findBookIntro"));
        return getInputNumber();
    }

    @Override
    public int removeBook() {
        System.out.println(getMessage("removeBookIntro"));
        return getInputNumber();
    }

    @Override
    public Book editBook() {
        System.out.println(getMessage("editBookIntro"));
        Book book = new Book();
        System.out.println(getMessage("bookDataId"));
        book.setId(getInputNumber());
        System.out.println(getMessage("bookDataAuthor"));
        book.setAuthor(scanner.nextLine());
        System.out.println(getMessage("bookDataTitle"));
        book.setTitle(scanner.nextLine());
        System.out.println(getMessage("bookDataGenre"));
        book.setGenre(scanner.nextLine());
        System.out.println(getMessage("bookDataRelease"));
        book.setReleaseYear(getInputNumber());
        return book;
    }

    @Override
    public int menu() {
        System.out.println(getMessage("menuList"));
        return getInputNumber();
    }

    private int getInputNumber() {
        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                scanner.nextLine();
                return number;
            }
            System.out.println(getMessage("notANumberError"));
            scanner.nextLine();
        }
    }

    @Override
    public void alert(String key) {
        System.out.println("\n" + getMessage(key));
        System.out.println(getMessage("continue"));
        scanner.nextLine();
    }

    @Override
    public void bookDetails(Book book) {
        System.out.println(book.getTitle() + " by " + book.getAuthor() + " [" + book.getReleaseYear() + "]");
    }

    @Override
    public void bookTitle(Book book) {
        System.out.println(book.getTitle());
    }
}
