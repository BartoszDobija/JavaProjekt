package works.buddy.library.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Configuration
@ComponentScan("works.buddy.library")
public class HibernateConfig {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

}
