package works.buddy.library.main;

import works.buddy.library.dao.InMemoryBookDAO;
import works.buddy.library.data.CsvInitialData;
import works.buddy.library.model.Book;
import works.buddy.library.services.ConsoleUI;
import works.buddy.library.services.LibraryApp;
import works.buddy.library.services.Messages;
import works.buddy.library.utils.CsvReader;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Scanner;

public class Main {

    private static final String MESSAGES_PROPERTIES = "messages.properties";

    private static final String BOOKS_CSV = "books.csv";

    public static void main(String[] args) {
        Messages.init(MESSAGES_PROPERTIES);
        new LibraryApp(new InMemoryBookDAO(getBooks()), new ConsoleUI(new Scanner(System.in))).run();
    }

    private static Collection<Book> getBooks() {
        return new CsvInitialData(new CsvReader(Paths.get("src/main/resources/" + BOOKS_CSV))).getBooks();
    }
}
