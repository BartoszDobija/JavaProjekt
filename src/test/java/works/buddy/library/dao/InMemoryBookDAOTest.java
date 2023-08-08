package works.buddy.library.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import works.buddy.library.model.Book;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryBookDAOTest {

    private static final String TITLE = "t";

    private static final String AUTHOR = "a";

    private static final String GENRE = "f";

    private static final Integer YEAR = 2000;

    private BookDAO bookDAO;

    @BeforeEach
    void setUp() {
        bookDAO = new InMemoryBookDAO(new ArrayList<>());
    }

    @Test
    void saving() {
        save(getBookWithSampleValues(null));
        Collection<Book> all = bookDAO.findAll();
        assertNotNull(all);
        assertEquals(1, all.size());
        assertSampleValues(1, all.iterator().next());
    }

    @Test
    void finding() {
        Book bookToSave = getBookWithSampleValues(null);
        save(bookToSave);
        Book retrievedBook = find(bookToSave.getId());
        assertSampleValues(1, retrievedBook);
    }

    @Test
    void deleting() {
        Book book = getBookWithSampleValues(null);
        save(book);
        Integer bookId = book.getId();
        assertEquals(find(bookId), book);
        bookDAO.delete(bookId);
        assertThrowsExactly(NotFoundException.class, () -> find(bookId));
    }

    @Test
    void updating() {
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
        save(book);
        return book;
    }

    private void save(Book book) {
        bookDAO.save(book);
    }

    private void assertSampleValues(Integer bookId, Book book) {
        assertNotNull(book);
        assertEquals(bookId, book.getId());
        assertEquals(TITLE, book.getTitle());
        assertEquals(AUTHOR, book.getAuthor());
        assertEquals(GENRE, book.getGenre());
        assertEquals(YEAR, book.getReleaseYear());
    }

    private Book getBookWithSampleValues(Integer bookId) {
        Book book = new Book();
        book.setId(bookId);
        book.setTitle(TITLE);
        book.setAuthor(AUTHOR);
        book.setGenre(GENRE);
        book.setReleaseYear(YEAR);
        return book;
    }
}