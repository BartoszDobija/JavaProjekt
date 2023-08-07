package works.buddy.library.main;

import works.buddy.library.dao.BookDAO;
import works.buddy.library.dao.InMemoryBookDAO;
import works.buddy.library.data.CsvInitialData;
import works.buddy.library.data.InitialData;
import works.buddy.library.services.ConsoleUI;
import works.buddy.library.services.LibraryApp;
import works.buddy.library.services.Messages;
import works.buddy.library.services.UI;
import works.buddy.library.utils.CsvReader;

import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Messages.init();
        InitialData initialData = new CsvInitialData(new CsvReader(Paths.get("src/main/resources/initialData.csv")));
        UI ui = new ConsoleUI(new Scanner(System.in));
        BookDAO bookDAO = new InMemoryBookDAO(initialData.getBooks());
        LibraryApp libraryApp = new LibraryApp(bookDAO, ui);
        libraryApp.run();
    }

}
