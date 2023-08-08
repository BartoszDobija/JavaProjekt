package works.buddy.library.dao;

import works.buddy.library.model.Book;

import java.sql.*;
import java.util.Collection;

public class JdbcBookDAO implements BookDAO {

    private final Connection connection;

    public JdbcBookDAO() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mariadb://localhost/", "root", null);
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
        return null;
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
