package works.buddy.library.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import works.buddy.library.model.Book;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryBookDAOTest {

    private static final String AUTHOR = "a";

    private static final String GENRE = "f";

    private static final Integer YEAR = 2000;

    private BookDAO bookDAO;

    @BeforeEach
    void setUp() {
        bookDAO = new InMemoryBookDAO(new ArrayList<>());
    }

    @Test
    void update() {
        Book book = saveEmptyBook();
        Integer bookId = book.getId();
        assertEquals(1, bookId);
        assertNull(find(bookId).getAuthor());
        bookDAO.update(getBookWithSampleValues(bookId));
        assertSampleValues(bookId, find(bookId));
    }

    private Book find(Integer bookId) {
        return bookDAO.find(bookId);
    }

    private Book saveEmptyBook() {
        Book book = new Book();
        bookDAO.save(book);
        return book;
    }

    private static void assertSampleValues(Integer bookId, Book book) {
        assertNotNull(book);
        assertEquals(bookId, book.getId());
        assertEquals(AUTHOR, book.getAuthor());
        assertEquals(GENRE, book.getGenre());
        assertEquals(YEAR, book.getReleaseYear());
    }

    private static Book getBookWithSampleValues(Integer bookId) {
        Book book = new Book();
        book.setId(bookId);
        book.setAuthor(AUTHOR);
        book.setGenre(GENRE);
        book.setReleaseYear(YEAR);
        return book;
    }
}