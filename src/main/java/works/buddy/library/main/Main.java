package works.buddy.library.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import works.buddy.library.config.AppConfig;
import works.buddy.library.dao.JdbcBookDAO;
import works.buddy.library.services.LibraryApp;
import works.buddy.library.services.Messages;

public class Main {

    private static final String MESSAGES_PROPERTIES = "messages.properties";

    public static void main(String[] args) {
        Messages.init(MESSAGES_PROPERTIES);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        LibraryApp libraryApp = context.getBean(LibraryApp.class);
        libraryApp.run();
    }
}
