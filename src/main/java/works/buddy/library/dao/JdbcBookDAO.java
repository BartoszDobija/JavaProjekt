package works.buddy.library.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import works.buddy.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class JdbcBookDAO implements BookDAO {

    private final Connection connection;

    private static final String ID = "id";

    private static final String TITLE = "title";

    private static final String AUTHOR = "author";

    private static final String GENRE = "genre";

    private static final String RELEASE_YEAR = "releaseYear";

    private static final String FIND_ALL = "SELECT * FROM books ORDER BY id";

    private static final String FIND = "SELECT * FROM books WHERE id=?";

    private static final String INSERT = "INSERT INTO books (title, author, genre, releaseYear) VALUES(?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE books SET title=?, author=?, genre=?, releaseYear=? WHERE id=?";

    private static final String DELETE = "DELETE FROM books WHERE id=?";

    public JdbcBookDAO(@Value("${db.Url}") String url, @Value("${db.User}") String user, @Value("${db.Password}") String password) {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Book> findAll() {
        try (PreparedStatement statement = prepareStatement(FIND_ALL)) {
            Collection<Book> books = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(getBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Book book) {
        try (PreparedStatement statement = prepareStatement(INSERT, book)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book find(Integer id) throws NotFoundException {
        ResultSet resultSet = executeSelectById(id);
        if (hasNext(resultSet)) {
            return getBook(resultSet);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        try (PreparedStatement statement = prepareStatement(DELETE, id)) {
            validateRowsAffected(statement.executeUpdate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Book book) throws NotFoundException {
        try (PreparedStatement statement = prepareStatement(UPDATE, book)) {
            validateRowsAffected(statement.executeUpdate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Book getBook(ResultSet resultSet) {
        try {
            Book book = new Book();
            book.setId(resultSet.getInt(ID));
            book.setTitle(resultSet.getString(TITLE));
            book.setAuthor(resultSet.getString(AUTHOR));
            book.setGenre(resultSet.getString(GENRE));
            book.setReleaseYear(resultSet.getInt(RELEASE_YEAR));
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement prepareStatement(String query, Book book) {
        try {
            PreparedStatement statement;
            statement = prepareStatement(query);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setInt(4, book.getReleaseYear());
            if (query.equals(UPDATE)) {
                statement.setInt(5, book.getId());
            }
            return statement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private PreparedStatement prepareStatement(String query, int id) {
        try {
            PreparedStatement statement;
            statement = prepareStatement(query);
            statement.setInt(1, id);
            return statement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement prepareStatement(String query) {
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeSelectById(int id) {
        try (PreparedStatement statement = prepareStatement(FIND, id)) {
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean hasNext(ResultSet resultSet) {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateRowsAffected(int rowsAffected) throws NotFoundException {
        if (rowsAffected == 0) {
            throw new NotFoundException();
        } else if (rowsAffected > 1) {
            throw new RuntimeException();
        }
    }
}
