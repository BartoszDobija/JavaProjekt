package works.buddy.library.dao;

import org.springframework.stereotype.Repository;
import works.buddy.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class JdbcBookDAO implements BookDAO {

    private final Connection connection;

    private static final String DELETE = "DELETE FROM books WHERE id=?";

    private static final String FIND_ALL = "SELECT * FROM books ORDER BY id";

    private static final String FIND = "SELECT * FROM books WHERE id=?";

    private static final String INSERT = "INSERT INTO books (title, author, genre, releaseYear) VALUES(?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE books SET title=?, author=?, genre=?, releaseYear=? WHERE id=?";

    public JdbcBookDAO() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mariadb://localhost/library", "library", "library");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book mapFromDb(ResultSet resultSet) {
        Book book = new Book();
        try {
            book.setId(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setGenre(resultSet.getString("genre"));
            book.setReleaseYear(resultSet.getInt("releaseYear"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public Collection<Book> findAll() {
        Collection<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(mapFromDb(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public void save(Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setInt(4, book.getReleaseYear());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book find(Integer id) throws NotFoundException {
        Book book = new Book();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = mapFromDb(resultSet);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Book book) throws NotFoundException {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setInt(4, book.getReleaseYear());
            statement.setInt(5, book.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
