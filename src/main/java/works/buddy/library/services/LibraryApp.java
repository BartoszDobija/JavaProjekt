package works.buddy.library.services;

import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Book;

public class LibraryApp {

    private final BookDAO bookDao;

    private final Display display;

    public LibraryApp(BookDAO bookDao, Display display) {
        this.bookDao = bookDao;
        this.display = display;
    }

    public void run() {
        boolean running = true;
        display.alert("appIntro");
        while (running) {
            running = menu();
        }
    }

    public boolean menu() {
        boolean running = true;
        boolean success = true;
        switch (display.menu()) {
            case 1 -> listBooks();
            case 2 -> success = findBook();
            case 3 -> addBook();
            case 4 -> success = editBook();
            case 5 -> success = deleteBook();
            case 6 -> {
                running = false;
                success = false;
            }
            default -> display.alert("notANumberError");
        }
        if (success) {
            display.alert("operationSuccess");
        }
        return running;
    }

    private boolean editBook() {
        Book book = display.editBook();
        if (!bookDao.checkIfIdExists(book.getId())) {
            display.alert("bookNotFoundError");
            return false;
        }
        bookDao.edit(book);
        return true;

    }

    private boolean deleteBook() {
        int id = display.removeBook();
        if (!bookDao.checkIfIdExists(id)) {
            display.alert("bookNotFoundError");
            return false;
        }
        bookDao.delete(bookDao.findById(id));
        return true;

    }

    private boolean findBook() {
        int id = display.findBook();
        if (!bookDao.checkIfIdExists(id)) {
            display.alert("bookNotFoundError");
            return false;
        }
        display.bookDetails(bookDao.findById(id));
        return true;

    }

    private void addBook() {
        Book book = display.addBook();
        bookDao.add(book);
    }

    public void listBooks() {
        for (Book book : bookDao.getAll()) {
            display.bookTitle(book);
        }
    }

}
