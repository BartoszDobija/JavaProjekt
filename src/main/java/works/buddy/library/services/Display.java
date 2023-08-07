package works.buddy.library.services;

import works.buddy.library.model.Book;

public interface Display {

    Book addBook();

    int findBook();

    int removeBook();

    Book editBook();

    int menu();

    void alert(String key);

    void bookDetails(Book book);

    void bookTitle(Book book);
}
