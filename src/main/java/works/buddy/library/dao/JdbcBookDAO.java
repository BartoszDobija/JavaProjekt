package works.buddy.library.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import works.buddy.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Repository
@PropertySource("classpath:db.query.properties")
@PropertySource("classpath:db.connection.properties")
public class JdbcBookDAO implements BookDAO {

    private final Connection connection;

    @Value("${dbFindAll}")
    private String FIND_ALL;

    @Value("${dbFind}")
    private String FIND;

    @Value("${dbInsert}")
    private String INSERT;

    @Value("${dbUpdate}")
    private String UPDATE;

    @Value("${dbDelete}")
    private String DELETE;

    public JdbcBookDAO(@Value("${dbUrl}") String url, @Value("${dbUser}") String user, @Value("${dbPassword}") String password) {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Book mapBookFromDbResult(ResultSet resultSet) {
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

    private PreparedStatement prepareStatement(String query) {
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement statementWithData(String query, Book book) throws RuntimeException {
        PreparedStatement statement;
        try {
            statement = prepareStatement(query);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setInt(4, book.getReleaseYear());
            if (query.equals(UPDATE)) {
                statement.setInt(5, book.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    private PreparedStatement statementWithData(String query, int id) throws RuntimeException {
        PreparedStatement statement;
        try {
            statement = prepareStatement(query);
            statement.setInt(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    private void bookIdWasValid(int rowsAffected) throws RuntimeException {
        if (rowsAffected == 0) {
            throw new NotFoundException();
        } else if (rowsAffected > 1) {
            throw new RuntimeException();
        }
    }

    @Override
    public Collection<Book> findAll() throws RuntimeException {
        Collection<Book> books = new ArrayList<>();
        try (PreparedStatement statement = prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(mapBookFromDbResult(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public void save(Book book) throws RuntimeException {
        try (PreparedStatement statement = statementWithData(INSERT, book)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book find(Integer id) throws RuntimeException {
        Book book;
        try (PreparedStatement statement = statementWithData(FIND, id)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = mapBookFromDbResult(resultSet);
            } else {
                throw new NotFoundException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public void delete(Integer id) throws RuntimeException {
        try (PreparedStatement statement = statementWithData(DELETE, id)) {
            bookIdWasValid(statement.executeUpdate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Book book) throws RuntimeException {
        try (PreparedStatement statement = statementWithData(UPDATE, book)) {
            bookIdWasValid(statement.executeUpdate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
