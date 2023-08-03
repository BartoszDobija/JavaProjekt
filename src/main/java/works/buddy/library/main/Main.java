package works.buddy.library.main;

import works.buddy.library.app.LibraryApp;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.dao.InMemoryBookDAO;
import works.buddy.library.data.MockupData;
import works.buddy.library.service.Messages;
import works.buddy.library.view.ConsoleDisplayBook;
import works.buddy.library.view.ConsoleUserDialog;
import works.buddy.library.view.DisplayBook;
import works.buddy.library.view.UserDialog;

public class Main {

    public static void main(String[] args) {
        Messages.init();
        MockupData mockupData = new MockupData();
        DisplayBook displayBook = new ConsoleDisplayBook();
        UserDialog userDialog = new ConsoleUserDialog();
        BookDAO bookDAO = new InMemoryBookDAO(mockupData.getBooks());
        LibraryApp libraryApp = new LibraryApp(bookDAO, displayBook, userDialog);
        libraryApp.run();
    }

}
