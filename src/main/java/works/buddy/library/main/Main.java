package works.buddy.library.main;

import works.buddy.library.data.CsvInitialData;
import works.buddy.library.data.InitialData;
import works.buddy.library.services.LibraryApp;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.dao.InMemoryBookDAO;
import works.buddy.library.services.Messages;
import works.buddy.library.view.ConsoleDisplayBook;
import works.buddy.library.view.ConsoleUserDialog;
import works.buddy.library.view.DisplayBook;
import works.buddy.library.view.UserDialog;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Messages.init();
        InitialData initialData = new CsvInitialData(new File("initialData.csv"));
        DisplayBook displayBook = new ConsoleDisplayBook();
        UserDialog userDialog = new ConsoleUserDialog();
        BookDAO bookDAO = new InMemoryBookDAO(initialData.getBooks());
        LibraryApp libraryApp = new LibraryApp(bookDAO, displayBook, userDialog);
        libraryApp.run();
    }

}
