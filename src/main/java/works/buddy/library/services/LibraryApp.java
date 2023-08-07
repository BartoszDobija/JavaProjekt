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
        boolean success = true;
        switch (ui.getSelectedAction()) {
            case 1 -> listBooks();
            case 2 -> success = findBook();
            case 3 -> addBook();
            case 4 -> success = editBook();
            case 5 -> success = deleteBook();
            case 6 -> {
                running = false;
                success = false;
            }
            default -> ui.displayAlert("notANumberError");
        }
        if (success) {
            ui.displayAlert("operationSuccess");
        }
        return running;
    }

    private boolean editBook() {
        Book book = ui.editBook();
        if (!bookDAO.exists(book.getId())) {
            ui.displayAlert("bookNotFoundError");
            return false;
        }
        bookDAO.edit(book);
        return true;

    }

    private boolean deleteBook() {
        int id = ui.getBookIdForDeletion();
        if (!bookDAO.exists(id)) {
            ui.displayAlert("bookNotFoundError");
            return false;
        }
        bookDAO.delete(bookDAO.findById(id));
        return true;

    }

    private boolean findBook() {
        int id = ui.getBookId();
        if (!bookDAO.exists(id)) {
            ui.displayAlert("bookNotFoundError");
            return false;
        }
        ui.displayBook(bookDAO.findById(id));
        return true;

    }

    private void addBook() {
        Book book = ui.addBook();
        bookDAO.add(book);
    }

    public void listBooks() {
        for (Book book : bookDAO.getAll()) {
            ui.displayBookTitle(book);
        }
    }

}
