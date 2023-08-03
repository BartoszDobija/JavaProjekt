package works.buddy.examples.library.view;

import works.buddy.examples.library.model.Book;
public class BookConsolePrinter {

    public static void printBook(Book book) {
        System.out.println("\"" + book.getTitle() + "\" by " + book.getAuthor() + " [" + book.getReleaseDate() + "]");
    }

    public static void printBookTitle(Book book, int i) {
        System.out.println(i + ". " + book.getTitle());
    }
}
