package works.buddy.library.app;

import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Book;
import works.buddy.library.view.DisplayBook;
import works.buddy.library.view.UserDialog;

public class LibraryApp {

    private final BookDAO bookDao;

    private final DisplayBook displayBook;

    private final UserDialog userDialog;

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
            default -> userDialog.errorDialog("notANumberError");
        }
        return running;
    }

    private void editBook() {
        Book book = userDialog.editBookDialog();
        if (!bookDao.checkIfIdExists(book.getId())) {
            userDialog.errorDialog("bookNotFoundError");
            return;
        }
        bookDao.edit(book);
    }

    private void deleteBook() {
        int id = userDialog.removeBookDialog();
        if (!bookDao.checkIfIdExists(id)) {
            userDialog.errorDialog("bookNotFoundError");
            return;
        }
        bookDao.delete(bookDao.findById(id));
    }

    private void findBook() {
        int id = userDialog.findBookDialog();
        if (!bookDao.checkIfIdExists(id)) {
            userDialog.errorDialog("bookNotFoundError");
            return;
        }
        displayBook.details(bookDao.findById(id));
    }

    private void addBook() {
        Book book = userDialog.addBookDialog();
        bookDao.add(book);
    }

    public void listBooks() {
        for (Book book : bookDao.getAll()) {
            displayBook.title(book);
        }
    }

}
