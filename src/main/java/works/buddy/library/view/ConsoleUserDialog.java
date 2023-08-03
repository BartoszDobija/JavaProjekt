package works.buddy.library.view;

import works.buddy.library.model.Book;

import java.util.Scanner;

import static works.buddy.library.service.Messages.getMessage;

public class ConsoleUserDialog implements UserDialog{
    private Scanner scanner;
    public ConsoleUserDialog(){
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
        book.setGenre(scanner.nextLine());
        return book;
    }

    @Override
    public int findBookDialog() {
        System.out.println(getMessage("findBookIntro"));
        return scanner.nextInt();
    }

    @Override
    public int removeBookDialog() {
        System.out.println(getMessage("removeBookIntro"));
        return scanner.nextInt();
    }

    @Override
    public Book editBookDialog() {
        System.out.println(getMessage("editBookIntro"));
        Book book = new Book();
        System.out.println(getMessage("bookDataAuthor"));
        book.setAuthor(scanner.nextLine());
        System.out.println(getMessage("bookDataTitle"));
        book.setTitle(scanner.nextLine());
        System.out.println(getMessage("bookDataGenre"));
        book.setGenre(scanner.nextLine());
        System.out.println(getMessage("bookDataRelease"));
        book.setGenre(scanner.nextLine());
        return book;
    }

    @Override
    public int menuDialog() {
        System.out.println(getMessage("menuIntro"));
        return scanner.nextInt();
    }
}
