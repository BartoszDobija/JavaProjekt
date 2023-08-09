package works.buddy.library.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import works.buddy.library.model.Book;

import java.nio.file.Paths;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

import static works.buddy.library.utils.CsvReader.readCSV;

@Configuration
@ComponentScan("works.buddy.library")
@PropertySource("classpath:db.properties")
public class AppConfig {

    @Value("${dbUrl}")
    private String dbUrl;

    @Value("${dbUser}")

    private String dbUser;

    @Value("${dbPassword}")

    private String dbPassword;

    private static final String BOOKS_CSV = "books.csv";

    @Bean
    public Collection<Book> books() {
        return getBooks();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public Connection connection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    private static Collection<Book> getBooks() {
        return getBooks(readCSV(Paths.get("src/main/resources/" + BOOKS_CSV)));
    }

    private static Collection<Book> getBooks(Collection<String[]> lines) {
        return lines.stream().map(object -> new Book(object[0], object[1], object[2], Integer.parseInt(object[3]))).collect(Collectors.toList());
    }
}
