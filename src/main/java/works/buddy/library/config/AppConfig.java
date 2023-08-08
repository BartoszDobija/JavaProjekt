package works.buddy.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.dao.InMemoryBookDAO;
import works.buddy.library.model.Book;
import works.buddy.library.services.ConsoleUI;
import works.buddy.library.services.LibraryApp;
import works.buddy.library.services.UI;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Scanner;

import static works.buddy.library.utils.CsvReader.readCSV;

@Configuration
public class AppConfig {

    private static final String BOOKS_CSV = "books.csv";

    @Bean
    public BookDAO bookDAO() {
        return new InMemoryBookDAO(getBooks());
    }

    @Bean
    public UI ui() {
        return new ConsoleUI(new Scanner(System.in));
    }

    @Bean
    public LibraryApp libraryApp(BookDAO bookDAO, UI ui) {
        return new LibraryApp(bookDAO, ui);
    }

    private static Collection<Book> getBooks() {
        return getBooks(readCSV(Paths.get("src/main/resources/" + BOOKS_CSV)));
    }

    private static Collection<Book> getBooks(Collection<String[]> lines) {
        return lines.stream().map(object -> new Book(object[0], object[1], object[2], Integer.parseInt(object[3]))).toList();
    }
}
