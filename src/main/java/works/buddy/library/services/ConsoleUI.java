package works.buddy.library.services;

import works.buddy.library.model.Book;

import java.util.Scanner;

import static works.buddy.library.services.Messages.getMessage;

public class ConsoleUI implements UI {

    private final Scanner scanner;

    public ConsoleUI(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Integer getSelectedAction() {
        System.out.println(getMessage("menuList"));
        return getInputNumber();
    }

    @Override
    public Book addBook() {
        System.out.println(getMessage("addBookIntro"));
        Book book = new Book();
        System.out.println(getMessage("bookDataAuthor"));
        book.setAuthor(readLine());
        System.out.println(getMessage("bookDataTitle"));
        book.setTitle(readLine());
        System.out.println(getMessage("bookDataGenre"));
        book.setGenre(readLine());
        System.out.println(getMessage("bookDataRelease"));
        book.setReleaseYear(getInputNumber());
        return book;
    }

    @Override
    public Integer getBookId() {
        System.out.println(getMessage("bookDataId"));
        return getInputNumber();
    }

    @Override
    public Book getBookForUpdate() {
        System.out.println(getMessage("editBookIntro"));
        Book book = new Book();
        System.out.println(getMessage("bookDataId"));
        book.setId(getInputNumber());
        System.out.println(getMessage("bookDataAuthor"));
        book.setAuthor(readLine());
        System.out.println(getMessage("bookDataTitle"));
        book.setTitle(readLine());
        System.out.println(getMessage("bookDataGenre"));
        book.setGenre(readLine());
        System.out.println(getMessage("bookDataRelease"));
        book.setReleaseYear(getInputNumber());
        return book;
    }

    private Integer getInputNumber() {
        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                readLine();
                return number;
            }
            System.out.println(getMessage("notANumberError"));
            readLine();
        }
    }

    @Override
    public void displayAlert(String key) {
        System.out.println("\n" + getMessage(key));
        System.out.println(getMessage("continue"));
        readLine();
    }

    private String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void displayBook(Book book) {
        System.out.println(book.getTitle() + " by " + book.getAuthor() + ", " + book.getGenre() + " originally published in [" + book.getReleaseYear() + "]");
    }

    @Override
    public void displayBookTitle(Book book) {
        System.out.print(book.getId() + ". ");
        System.out.println(book.getTitle());
    }
}
