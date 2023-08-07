package works.buddy.library.view;

import works.buddy.library.model.Book;

import java.util.Scanner;

import static works.buddy.library.services.Messages.getMessage;

public class ConsoleUserDialog implements UserDialog {

    private final Scanner scanner;

    public ConsoleUserDialog() {
        scanner = new Scanner(System.in);
    }

    private int inputNumber() {
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
    public Book addBookDialog() {
        System.out.println(getMessage("addBookIntro"));
        Book book = new Book();
        System.out.println(getMessage("bookDataAuthor"));
        book.setAuthor(scanner.nextLine());
        System.out.println(getMessage("bookDataTitle"));
        book.setTitle(scanner.nextLine());
        System.out.println(getMessage("bookDataGenre"));
        book.setGenre(scanner.nextLine());
        System.out.println(getMessage("bookDataRelease"));
        book.setReleaseYear(inputNumber());
        return book;
    }

    @Override
    public int findBookDialog() {
        System.out.println(getMessage("findBookIntro"));
        return inputNumber();
    }

    @Override
    public int removeBookDialog() {
        System.out.println(getMessage("removeBookIntro"));
        return inputNumber();
    }

    @Override
    public Book editBookDialog() {
        System.out.println(getMessage("editBookIntro"));
        Book book = new Book();
        System.out.println(getMessage("bookDataId"));
        book.setId(inputNumber());
        System.out.println(getMessage("bookDataAuthor"));
        book.setAuthor(scanner.nextLine());
        System.out.println(getMessage("bookDataTitle"));
        book.setTitle(scanner.nextLine());
        System.out.println(getMessage("bookDataGenre"));
        book.setGenre(scanner.nextLine());
        System.out.println(getMessage("bookDataRelease"));
        book.setReleaseYear(inputNumber());
        return book;
    }

    @Override
    public int menuDialog() {
        System.out.println(getMessage("menuList"));
        return inputNumber();
    }

    @Override
    public void acceptDialog(String key) {
        System.out.println(getMessage(key));
        System.out.println(getMessage("continue"));
        scanner.nextLine();
    }
}
