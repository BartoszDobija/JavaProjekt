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
        switch (display.menu()) {
            case 1 -> listBooks();
            case 2 -> findBook();
            case 3 -> addBook();
            case 4 -> editBook();
            case 5 -> deleteBook();
            case 6 -> running = false;
            default -> display.alert("notANumberError");
        }
        return running;
    }

    private void editBook() {
        Book book = display.editBook();
        if (bookDao.checkIfIdDoesntExists(book.getId())) {
            display.alert("bookNotFoundError");
            return;
        }
        bookDao.edit(book);

    }

    private void deleteBook() {
        int id = display.removeBook();
        if (bookDao.checkIfIdDoesntExists(id)) {
            display.alert("bookNotFoundError");
            return;
        }
        bookDao.delete(bookDao.findById(id));

    }

    private void findBook() {
        int id = display.findBook();
        if (bookDao.checkIfIdDoesntExists(id)) {
            display.alert("bookNotFoundError");
            return;
        }
        display.bookDetails(bookDao.findById(id));

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
