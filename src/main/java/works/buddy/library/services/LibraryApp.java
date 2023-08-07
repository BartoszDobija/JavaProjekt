package works.buddy.library.services;

import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Book;

public class LibraryApp {

    private final BookDAO bookDao;

    private final UI UI;

    public LibraryApp(BookDAO bookDao, UI UI) {
        this.bookDao = bookDao;
        this.UI = UI;
    }

    public void run() {
        boolean running = true;
        UI.displayAlert("appIntro");
        while (running) {
            running = menu();
        }
    }

    public boolean menu() {
        boolean running = true;
        boolean success = true;
        switch (UI.getSelectedAction()) {
            case 1 -> listBooks();
            case 2 -> success = findBook();
            case 3 -> addBook();
            case 4 -> success = editBook();
            case 5 -> success = deleteBook();
            case 6 -> {
                running = false;
                success = false;
            }
            default -> UI.displayAlert("notANumberError");
        }
        if (success) {
            UI.displayAlert("operationSuccess");
        }
        return running;
    }

    private boolean editBook() {
        Book book = UI.editBook();
        if (!bookDao.checkIfIdExists(book.getId())) {
            UI.displayAlert("bookNotFoundError");
            return false;
        }
        bookDao.edit(book);
        return true;

    }

    private boolean deleteBook() {
        int id = UI.getBookIdForDeletion();
        if (!bookDao.checkIfIdExists(id)) {
            UI.displayAlert("bookNotFoundError");
            return false;
        }
        bookDao.delete(bookDao.findById(id));
        return true;

    }

    private boolean findBook() {
        int id = UI.getBookId();
        if (!bookDao.checkIfIdExists(id)) {
            UI.displayAlert("bookNotFoundError");
            return false;
        }
        UI.displayBook(bookDao.findById(id));
        return true;

    }

    private void addBook() {
        Book book = UI.addBook();
        bookDao.add(book);
    }

    public void listBooks() {
        for (Book book : bookDao.getAll()) {
            UI.displayBookTitle(book);
        }
    }

}
