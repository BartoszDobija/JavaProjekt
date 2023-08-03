package works.buddy.library.dao;

import works.buddy.library.model.Book;

public interface BookDAO {

    void add(Book book);

    Book findById(Integer id);

    boolean delete(Integer id);

    void edit(Integer id);

    Integer getBooksCount();
}
