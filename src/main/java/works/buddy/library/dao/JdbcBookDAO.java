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
    private static final String FIND_BY_ID = "SELECT * FROM books WHERE id=?";
    private static final String INSERT = "INSERT INTO books (title, author, genre, releaseYear) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE books SET title=?, author=?, genre=?, releaseYear=? WHERE id=?";
    public JdbcBookDAO() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mariadb://localhost/library", "library", "library");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ResultSet execute(String query) {
        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    @Override
    public Collection<Book> findAll() {
        Collection<Book> books = new ArrayList<>();
        try{
            ResultSet resultSet = execute(FIND_ALL);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setReleaseYear(resultSet.getInt("releaseYear"));

                books.add(book);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public Book find(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public void update(Book book) throws NotFoundException {

    }
}
