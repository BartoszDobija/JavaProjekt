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

    private Book bookFromDb(ResultSet resultSet) {
        Book book = new Book();
        try {
            book.setId(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setGenre(resultSet.getString("genre"));
            book.setReleaseYear(resultSet.getInt("releaseYear"));
        } catch (SQLException e) {
            throw new WrongSQLQueryException();
        }
        return book;
    }

    private PreparedStatement bookToDb(String query, Book book) throws WrongSQLQueryException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setInt(4, book.getReleaseYear());
            if (query.equals(UPDATE)) {
                statement.setInt(5, book.getId());
            }
        } catch (SQLException e) {
            throw new WrongSQLQueryException();
        }
        return statement;
    }

    private PreparedStatement intToDb(String query, int id) throws WrongSQLQueryException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
        } catch (SQLException e) {
            throw new WrongSQLQueryException();
        }
        return statement;
    }

    private void validateUpdate(int rowsAffected) throws NoUpdateException {
        if (rowsAffected < 1) {
            throw new NotFoundException();
        }
    }

    @Override
    public Collection<Book> findAll() throws WrongSQLQueryException {
        Collection<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(bookFromDb(resultSet));
            }
        } catch (SQLException e) {
            throw new WrongSQLQueryException();
        }
        return books;
    }

    @Override
    public void save(Book book) throws WrongSQLQueryException {
        try (PreparedStatement statement = bookToDb(INSERT, book)) {
            validateUpdate(statement.executeUpdate());
        } catch (SQLException e) {
            throw new WrongSQLQueryException();
        }
    }

    @Override
    public Book find(Integer id) throws NotFoundException, WrongSQLQueryException {
        Book book;
        try (PreparedStatement statement = intToDb(FIND, id)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = bookFromDb(resultSet);
            } else {
                throw new NotFoundException();
            }
        } catch (SQLException e) {
            throw new WrongSQLQueryException();
        }
        return book;
    }

    @Override
    public void delete(Integer id) throws NotFoundException, WrongSQLQueryException {
        try (PreparedStatement statement = intToDb(DELETE, id)) {
            validateUpdate(statement.executeUpdate());
        } catch (SQLException e) {
            throw new WrongSQLQueryException();
        }
    }

    @Override
    public void update(Book book) throws NotFoundException, WrongSQLQueryException {
        try (PreparedStatement statement = bookToDb(UPDATE, book)) {
            validateUpdate(statement.executeUpdate());

        } catch (SQLException e) {
            throw new WrongSQLQueryException();
        }
    }
}
