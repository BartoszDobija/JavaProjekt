package works.buddy.library.dao;

import works.buddy.library.model.Book;

public interface BookDAO {

    void add(Book book);

    Book findById(Integer id);

    void delete(Book book);

    void edit(Book book);

    Integer getBooksCount();
}
