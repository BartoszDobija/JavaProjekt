package works.buddy.library.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import works.buddy.library.config.AppConfig;
import works.buddy.library.services.LibraryApp;
import works.buddy.library.services.Messages;

import java.sql.*;

public class Main {

    private static final String MESSAGES_PROPERTIES = "messages.properties";

    public static void main(String[] args) {

        //create connection for a server installed in localhost, with a user "root" with no password
        try{
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/", "root", null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Messages.init(MESSAGES_PROPERTIES);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        LibraryApp libraryApp = context.getBean(LibraryApp.class);
        libraryApp.run();
    }
}
