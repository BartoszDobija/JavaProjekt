package works.buddy.library.dao;

import works.buddy.library.model.Book;

import java.util.Collection;

public class InMemoryBookDAO implements BookDAO {

    private final Collection<Book> books;

    public InMemoryBookDAO(Collection<Book> books) {
        this.books = books;
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
    public boolean exists(Integer id) {
        return books.stream().anyMatch(b -> b.getId().equals(id));
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
    }

    @Override
    public void update(Book book) {
        books.stream().filter(b -> b.getId().equals(book.getId())).findAny().ifPresent(b -> {
            b.setAuthor(book.getAuthor());
            b.setTitle(book.getTitle());
            b.setGenre(book.getGenre());
            b.setReleaseYear(book.getReleaseYear());
        });
    }
}
