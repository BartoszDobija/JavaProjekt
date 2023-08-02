package works.buddy.examples.library.data;

import works.buddy.examples.library.model.Book;

import java.util.ArrayList;
import java.util.List;


public class InitialData {

    public List<Book> getSampleBooks() {
        List<Book> books = new ArrayList() {{
            add(new Book(1, "Anna Karenina", "Leo Tolstoy", "Fiction", 1877));
            add(new Book(2, "To Kill a Mockingbird", "Harper Lee,", "Coming-of-age story", 1960));
            add(new Book(3, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1925));
        }};
        return books;
    }
}
