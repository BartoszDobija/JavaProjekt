package works.buddy.library.dao;

import works.buddy.library.model.Book;

import java.util.Collection;

public class InMemoryBookDAO implements BookDAO {

    private final Collection<Book> books;

    public InMemoryBookDAO(Collection<Book> books) {
        this.books = books;
    }

    @Override
    public void add(Book book) {
        book.setId(getNextId());
        books.add(book);
    }

    private int getNextId() {
        return books.stream().mapToInt(Book::getId).max().orElse(0) + 1;
    }

    @Override
    public Book findById(Integer id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst().orElseThrow();
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
    }

    @Override
    public void edit(Book book) {
        books.stream().filter(b -> b.getId().equals(book.getId())).findAny().ifPresent(b -> {
            b.setAuthor(book.getAuthor());
            b.setTitle(book.getTitle());
            b.setGenre(book.getGenre());
            b.setReleaseYear(book.getReleaseYear());
        });
    }

    @Override
    public Integer getBooksCount() {
        return books.size();
    }
}
