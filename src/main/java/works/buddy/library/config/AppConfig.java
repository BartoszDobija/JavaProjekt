package works.buddy.library.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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

    @Bean
    public Connection connection(@Value("${db.url}") String url, @Value("${db.user}") String user, @Value("${db.password}") String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(getResources());
        configurer.setNullValue("@null");
        configurer.setFileEncoding("UTF-8");
        configurer.setOrder(Ordered.LOWEST_PRECEDENCE);
        return configurer;
    }

    private static Resource[] getResources() {
        return new ClassPathResource[]{new ClassPathResource("jdbc.properties")};
    }

    private static Collection<Book> getBooks() {
        return getBooks(readCSV(Paths.get("./src/main/resources/" + BOOKS_CSV)));
    }

    private static Collection<Book> getBooks(Collection<String[]> lines) {
        return lines.stream().map(object -> new Book(object[0], object[1], object[2], Integer.parseInt(object[3]))).collect(Collectors.toList());
    }
}
