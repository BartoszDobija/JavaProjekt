package works.buddy.library.services;

import works.buddy.library.model.Book;

public interface UI {

    Integer getSelectedAction();

    Book addBook();

    Integer getBookId();

    Book editBook();

    void displayAlert(String key);

    void displayBook(Book book);

    void displayBookTitle(Book book);
}
