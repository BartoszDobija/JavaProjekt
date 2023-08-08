package works.buddy.library.dao;

import org.junit.jupiter.api.Test;
import works.buddy.library.model.Book;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryBookDAOTest {

    private static final String AUTHOR = "a";
    private static final String GENRE = "f";
    private static final Integer YEAR = 2000;
    @Test
    void update() {
        BookDAO bookDAO = new InMemoryBookDAO(new ArrayList<>());
        Book book = new Book();
        bookDAO.save(book);
        Integer bookId = book.getId();
        assertEquals(1, bookId);

        Book bookUpdate = new Book();
        bookUpdate.setId(bookId);
        bookUpdate.setAuthor(AUTHOR);
        bookUpdate.setGenre(GENRE);
        bookUpdate.setReleaseYear(YEAR);
        assertNull(bookDAO.find(bookId).getAuthor());

        bookDAO.update(bookUpdate);
        Book bookFromDAO = bookDAO.find(bookId);
        assertNotNull(bookFromDAO);
        assertEquals(bookId, bookFromDAO.getId());
        assertEquals(AUTHOR, bookFromDAO.getAuthor());
        assertEquals(GENRE, bookFromDAO.getGenre());
        assertEquals(YEAR, bookFromDAO.getReleaseYear());
    }
}