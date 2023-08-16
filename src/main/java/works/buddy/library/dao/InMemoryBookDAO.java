package works.buddy.library.dao;

import org.springframework.stereotype.Repository;
import works.buddy.library.model.Book;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class InMemoryBookDAO implements BookDAO {

    private Collection<Book> books;

    public InMemoryBookDAO() {
        this.books = new ArrayList<>();
    }

    private int getNextId() {
        return books.stream().mapToInt(Book::getId).max().orElse(0) + 1;
    }

    @Override
    public Collection<Book> findAll() {
        return books;
    }

    @Override
    public void save(Book book) {
        book.setId(getNextId());
        books.add(book);
    }

    @Override
    public Book find(Integer id) throws NotFoundException {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst().orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        books.remove(find(id));
    }

    @Override
    public void update(Book book) throws NotFoundException {
        delete(book.getId());
        books.add(book);
    }
}
