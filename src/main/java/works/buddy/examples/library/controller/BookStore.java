package works.buddy.examples.library.controller;

import works.buddy.examples.library.model.Book;

import java.util.Collection;

public class BookStore {

    private Collection<Book> books;

    public BookStore(Collection<Book> books) {
        this.books = books;
    }

    public void add(Book book) {
        book.setId(getNextId());
        books.add(book);
    }

    public Integer getBookStoreSize() {
        return books.size();
    }

    private Integer getNextId() {
        return books.stream().mapToInt(Book::getId).max().orElse(0) + 1;
    }

    public Book findById(Integer id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst().orElseThrow();
    }

    public void deleteBook(Integer id) {
        books.remove(findById(id));
    }
}
