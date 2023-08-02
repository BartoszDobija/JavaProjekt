package works.buddy.examples.library.data;

import works.buddy.examples.library.model.Book;

import java.util.Collection;
import java.util.List;

public class InitialData {

    public Collection<Book> getSampleBooks() {
        return List.of(new Book(1, "Anna Karenina", "Leo Tolstoy", "Fiction", 1877),
                new Book(2, "To Kill a Mockingbird", "Harper Lee,", "Coming-of-age story", 1960),
                new Book(3, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1925));
    }
}
