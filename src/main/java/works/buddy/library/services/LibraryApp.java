package works.buddy.library.services;

import works.buddy.library.dao.BookDAO;
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
            running = menu();
        }
    }

    public boolean menu() {
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
        if (bookDAO.exists(book.getId())) {
            bookDAO.update(book);
        } else {
            displayBookNotFound();
        }
    }

    private void deleteBook() {
        if (bookDAO.exists(ui.getBookIdForDeletion())) {
            bookDAO.delete(bookDAO.findById(ui.getBookIdForDeletion()));
        } else {
            displayBookNotFound();
        }
    }

    private void findBook() {
        if (bookDAO.exists(ui.getBookId())) {
            ui.displayBook(bookDAO.findById(ui.getBookId()));
        } else {
            displayBookNotFound();
        }
    }

    private void displayBookNotFound() {
        ui.displayAlert("bookNotFoundError");
    }

    private void addBook() {
        Book book = ui.addBook();
        bookDAO.add(book);
    }

    public void listBooks() {
        bookDAO.getAll().forEach(ui::displayBookTitle);
    }
}
