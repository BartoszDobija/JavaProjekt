package works.buddy.library.data;


import works.buddy.library.model.Book;

import java.util.ArrayList;
import java.util.Collection;

public class MockupData {
    public Collection<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(0, "Anna Karenina", "Leo Tolstoy", "Fiction", 1877));
        books.add(new Book(1, "To Kill a Mockingbird", "Harper Lee,", "Coming-of-age story", 1960));
        books.add(new Book(2, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1925));
        return books;
    }
}
