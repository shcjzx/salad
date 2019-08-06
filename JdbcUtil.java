package homework4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

    private final static JdbcUtil JDBC_UTIL = new JdbcUtil();
    private final static String URL = "jdbc:mysql://127.0.0.1:3306/hw";
    private final static String USER = "root";
    private final static String PASSWORD = "hei123456";
    private JdbcUtil() {}
    public static JdbcUtil getInstance() {
        return JDBC_UTIL;
    }

    public Connection getConnention() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void closeResourse(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
