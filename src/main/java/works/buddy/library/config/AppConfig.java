package works.buddy.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import works.buddy.library.model.Book;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

@Configuration
@ComponentScan("works.buddy.library")
@Import({HibernateConfig.class, DefaultDataSource.class})
public class AppConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
