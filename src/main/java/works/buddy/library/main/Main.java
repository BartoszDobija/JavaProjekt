package works.buddy.library.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import works.buddy.library.dao.InMemoryBookDAO;
import works.buddy.library.model.Book;
import works.buddy.library.services.ConsoleUI;
import works.buddy.library.services.LibraryApp;
import works.buddy.library.services.Messages;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Scanner;

import static works.buddy.library.utils.CsvReader.readCSV;

public class Main {

    private static final String MESSAGES_PROPERTIES = "messages.properties";

    private static final String BOOKS_CSV = "books.csv";

    public static void main(String[] args) {
        Messages.init(MESSAGES_PROPERTIES);
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");

        new LibraryApp(new InMemoryBookDAO(getBooks()), new ConsoleUI(new Scanner(System.in))).run();
    }

    private static Collection<Book> getBooks() {
        return getBooks(readCSV(Paths.get("src/main/resources/" + BOOKS_CSV)));
    }

    private static Collection<Book> getBooks(Collection<String[]> lines) {
        return lines.stream().map(object -> new Book(object[0], object[1], object[2], Integer.parseInt(object[3]))).toList();
    }
}
