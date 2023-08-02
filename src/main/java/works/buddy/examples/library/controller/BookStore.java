package works.buddy.examples.library.controller;

import works.buddy.examples.library.model.Book;

import java.util.List;

public class BookStore {

    public BookStore(List<Book> books) {
        this.books = books;
    }

    public List<Book> books;

    public void addBook(Book book) {
        book.setId(getNextId());
        books.add(book);
    }

    private int getNextId() {
        return books.get(books.size() - 1).getId() + 1;
    }

    public Book showBook(int id) {
        return books.get(id);
    }

    Book deleteBook(int id) {
        return books.remove(id);
    }
}
