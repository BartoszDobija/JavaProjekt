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
        userDialog.acceptDialog("appIntro");
        while (running) {
            running = menu();
        }
    }

    public boolean menu() {
        boolean running = true;
        boolean success = true;
        switch (userDialog.menuDialog()) {
            case 1 -> listBooks();
            case 2 -> success = findBook();
            case 3 -> addBook();
            case 4 -> success = editBook();
            case 5 -> success = deleteBook();
            case 6 -> {running = false; success=false;}
            default -> userDialog.acceptDialog("notANumberError");
        }
        if (success) {
            userDialog.acceptDialog("operationSuccess");
        }
        return running;
    }

    private boolean editBook() {
        Book book = userDialog.editBookDialog();
        if (!bookDao.checkIfIdExists(book.getId())) {
            userDialog.acceptDialog("bookNotFoundError");
            return false;
        }
        bookDao.edit(book);
        return true;

    }

    private boolean deleteBook() {
        int id = userDialog.removeBookDialog();
        if (!bookDao.checkIfIdExists(id)) {
            userDialog.acceptDialog("bookNotFoundError");
            return false;
        }
        bookDao.delete(bookDao.findById(id));
        return true;

    }

    private boolean findBook() {
        int id = userDialog.findBookDialog();
        if (!bookDao.checkIfIdExists(id)) {
            userDialog.acceptDialog("bookNotFoundError");
            return false;
        }
        displayBook.details(bookDao.findById(id));
        return true;

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
