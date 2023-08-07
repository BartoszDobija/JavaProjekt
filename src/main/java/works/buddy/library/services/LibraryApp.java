package works.buddy.library.services;

import works.buddy.library.dao.BookDAO;
import works.buddy.library.dao.NotFoundException;
import works.buddy.library.model.Book;

public class LibraryApp {

    private final BookDAO bookDAO;

    private final UI ui;

    public LibraryApp(BookDAO bookDAO, UI ui) {
        this.bookDAO = bookDAO;
        this.ui = ui;
    }

    public void run() {
        boolean running = true;
        ui.displayAlert("appIntro");
        while (running) {
            running = keepRunning();
        }
    }

    public boolean keepRunning() {
        boolean running = true;
        try {
            running = handleAction();
        } catch (NotFoundException e) {
            displayBookNotFound();
        }
        return running;
    }

    private boolean handleAction() throws NotFoundException {
        boolean running = true;
        switch (ui.getSelectedAction()) {
            case 1 -> listBooks();
            case 2 -> findBook();
            case 3 -> addBook();
            case 4 -> editBook();
            case 5 -> deleteBook();
            case 6 -> running = false;
            default -> ui.displayAlert("notANumberError");
        }
        return running;
    }

    private void editBook() {
        Book book = ui.editBook();
        if (bookExists(book.getId())) {
            bookDAO.update(book);
        } else {
            displayBookNotFound();
        }
    }

    private void deleteBook() throws NotFoundException {
        bookDAO.delete(getBook(ui.getBookId()));
    }

    private void findBook() throws NotFoundException {
        ui.displayBook(getBook(ui.getBookId()));
    }

    private Book getBook(Integer bookId) throws NotFoundException {
        return bookDAO.find(bookId);
    }

    private boolean bookExists(Integer bookId) {
        return bookDAO.exists(bookId);
    }

    private void displayBookNotFound() {
        ui.displayAlert("bookNotFoundError");
    }

    private void addBook() {
        bookDAO.save(ui.addBook());
    }

    public void listBooks() {
        bookDAO.findAll().forEach(ui::displayBookTitle);
    }
}
