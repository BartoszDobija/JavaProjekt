package works.buddy.library.view;

import works.buddy.library.model.Book;

import java.util.Scanner;

import static works.buddy.library.service.Messages.getMessage;

public class ConsoleUserDialog implements UserDialog {

    private final Scanner scanner;

    public ConsoleUserDialog() {
        scanner = new Scanner(System.in);
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
        book.setReleaseYear(scanner.nextInt());
        scanner.nextLine();
        return book;
    }

    @Override
    public int findBookDialog() {
        System.out.println(getMessage("findBookIntro"));
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    @Override
    public int removeBookDialog() {
        System.out.println(getMessage("removeBookIntro"));
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    @Override
    public Book editBookDialog() {
        System.out.println(getMessage("editBookIntro"));
        Book book = new Book();
        System.out.println(getMessage("bookDataId"));
        book.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.println(getMessage("bookDataAuthor"));
        book.setAuthor(scanner.nextLine());
        System.out.println(getMessage("bookDataTitle"));
        book.setTitle(scanner.nextLine());
        System.out.println(getMessage("bookDataGenre"));
        book.setGenre(scanner.nextLine());
        System.out.println(getMessage("bookDataRelease"));
        book.setReleaseYear(scanner.nextInt());
        scanner.nextLine();
        return book;
    }

    @Override
    public int menuDialog() {
        System.out.println(getMessage("menuIntro"));
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }
}
