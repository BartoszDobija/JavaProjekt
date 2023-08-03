package works.buddy.library.app;

import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Book;
import works.buddy.library.view.DisplayBook;
import works.buddy.library.view.UserDialog;

import static works.buddy.examples.library.view.MessageConsolePrinter.printMessage;

public class LibraryApp {

    private BookDAO bookDao;

    private DisplayBook displayBook;

    private UserDialog userDialog;

    public LibraryApp(BookDAO bookDao, DisplayBook displayBook, UserDialog userDialog) {
        this.bookDao = bookDao;
        this.displayBook = displayBook;
        this.userDialog = userDialog;
    }

    public void run() {
        boolean running = true;
        while (running) {
            running = menu();
        }
    }

    public boolean menu() {
        boolean running = true;

        switch (userDialog.menuDialog()) {
            case 1 -> listBooks();
            case 2 -> addBook();
            case 3 -> findBook();
            case 4 -> running = false;
            default -> printMessage("error");
        }
        return running;
    }

    private void findBook() {
        int id = userDialog.findBookDialog();
        displayBook.details(bookDao.findById(id));
    }

    private void addBook() {
        Book book = userDialog.addBookDialog();
        bookDao.add(book);
    }

    public void listBooks() {
        for (int i = 0; i < bookDao.getBooksCount(); i++) {
            displayBook.title(bookDao.findById(i));
        }
    }

}
