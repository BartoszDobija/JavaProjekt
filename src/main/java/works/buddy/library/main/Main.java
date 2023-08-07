package works.buddy.library.main;

import works.buddy.library.services.ConsoleDisplay;
import works.buddy.library.services.Display;
import works.buddy.library.services.LibraryApp;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.dao.InMemoryBookDAO;
import works.buddy.library.data.MockupData;
import works.buddy.library.services.Messages;

public class Main {

    public static void main(String[] args) {
        Messages.init();
        MockupData mockupData = new MockupData();
        Display display = new ConsoleDisplay();
        BookDAO bookDAO = new InMemoryBookDAO(mockupData.getBooks());
        LibraryApp libraryApp = new LibraryApp(bookDAO, display);
        libraryApp.run();
    }

}
