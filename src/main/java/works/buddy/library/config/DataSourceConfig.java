package works.buddy.library.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("hibernate.properties")
public class DataSourceConfig {

    @Value("${hibernate.connection.driver_class}")
    private String driver;

    @Value("${hibernate.connection.username}")
    private String username;

    @Value("${hibernate.connection.password}")
    private String password;

    @Value("${hibernate_connection_url}")
    private String url;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new DefaultDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.addConnectionProperty("MariaDB", "test");
        return dataSource;
    }
}
