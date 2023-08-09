package works.buddy.library.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class DefaultDataSource extends BasicDataSource {

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return createDataSource().getConnection();
    }
}
