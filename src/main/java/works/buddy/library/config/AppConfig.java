package works.buddy.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Scanner;

@Configuration
@ComponentScan("works.buddy.library")
@Import({HibernateConfig.class, DefaultDataSource.class, LiquibaseConfig.class})
public class AppConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
