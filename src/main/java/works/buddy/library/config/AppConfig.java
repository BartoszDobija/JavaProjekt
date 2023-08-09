package works.buddy.library.config;

import org.springframework.context.annotation.*;
import works.buddy.library.model.Book;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

import static works.buddy.library.utils.CsvReader.readCSV;

@Configuration
@ComponentScan("works.buddy.library")
@Import({HibernateConfig.class})
@PropertySource("hibernate.properties")
public class AppConfig {

    private static final String BOOKS_CSV = "books.csv";

    @Bean
    public Collection<Book> books() {
        return getBooks();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    private static Collection<Book> getBooks() {
        return getBooks(readCSV(Paths.get("src/main/resources/" + BOOKS_CSV)));
    }

    private static Collection<Book> getBooks(Collection<String[]> lines) {
        return lines.stream().map(object -> new Book(object[0], object[1], object[2], Integer.parseInt(object[3]))).collect(Collectors.toList());
    }
}
