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
            case 2 -> findBook();
            case 3 -> addBook();
            case 4 -> editBook();
            case 5 -> deleteBook();
            case 6 -> running = false;
            default -> printMessage("error");
        }
        return running;
    }

    private void editBook() {
        bookDao.edit(userDialog.editBookDialog());
    }

    private void deleteBook() {
        int id = userDialog.removeBookDialog();
        bookDao.delete(bookDao.findById(id));
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
