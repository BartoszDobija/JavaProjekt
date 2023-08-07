package works.buddy.library.dao;

import works.buddy.library.model.Book;

import java.util.Collection;

public interface BookDAO {

    Collection<Book> getAll();

    void add(Book book);

    Book findById(Integer id);

    boolean checkIfIdDoesntExists(Integer id);

    void delete(Book book);

    void edit(Book book);
}
