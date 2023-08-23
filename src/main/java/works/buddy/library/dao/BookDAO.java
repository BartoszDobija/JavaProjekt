package works.buddy.library.dao;

import works.buddy.library.model.Book;

import java.util.Collection;

public interface BookDAO {

    void save(Book book);

    void update(Book book);

    void delete(Book book);

    Collection<Book> findAll();

    Book find(Integer id);
}
