package works.buddy.library.dao;

import works.buddy.library.model.Book;

import java.util.Collection;

public interface BookDAO {

    Collection<Book> findAll();

    void add(Book book);

    Book findById(Integer id) throws NotFoundException;

    boolean exists(Integer id);

    void delete(Book book);

    void update(Book book);
}
