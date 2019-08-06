import java.sql.*;

public class JdbcUtil {


    private Connection conn=null;
    private Statement stmt=null;
    private ResultSet rs=null;
    private static final JdbcUtil JDBC_UTIL = new JdbcUtil();
    private JdbcUtil(){
    }
    public static JdbcUtil getInstance(){
        return JDBC_UTIL;
    }
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/train", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void closeResource(AutoCloseable autoCloseable) {
        if (null != autoCloseable) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public ResultSet executeQuery(String sql){
        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println("执行查询sql出错");
            e.printStackTrace();
        }

        return rs;
    }

    public void releaseSource(){
        try{
            if(rs!=null){
                rs.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }catch(Exception e){
        }
    }
    public int executeUpdate(String sql){
        int count=0;
        try {
            stmt=conn.createStatement();
            count=stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("执行更新sql出错");
            e.printStackTrace();
        }
        return count;
    }


}
