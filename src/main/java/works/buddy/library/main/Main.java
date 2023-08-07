package works.buddy.library.main;

import works.buddy.library.data.CsvInitialData;
import works.buddy.library.data.InitialData;
import works.buddy.library.services.ConsoleDisplay;
import works.buddy.library.services.Display;
import works.buddy.library.services.LibraryApp;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.dao.InMemoryBookDAO;
import works.buddy.library.services.Messages;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Messages.init();InitialData initialData = new CsvInitialData(new File("initialData.csv"));
        Display display = new ConsoleDisplay();
        BookDAO bookDAO = new InMemoryBookDAO(initialData.getBooks());
        LibraryApp libraryApp = new LibraryApp(bookDAO, display);
        libraryApp.run();
    }

}
