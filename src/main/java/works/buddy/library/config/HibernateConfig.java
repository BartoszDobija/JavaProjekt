package works.buddy.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan("works.buddy.library")
public class HibernateConfig {

    @Bean
    public EntityManager entityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("");
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public Connection connection() {
        try {
            return DriverManager.getConnection("jdbc:mariadb://localhost/library", "library", "library");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
