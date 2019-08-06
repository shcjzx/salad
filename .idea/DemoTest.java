import java.sql.*;

public class DemoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 加载驱动
        Class.forName("com.mysql.jdbc.Driver");// Class
        // 获取连接
        // url:数据库连接地址  jdbc:mysql://localhost:3306/train
        // 如果想要连接其他人的数据库，只要在同一个局域网内部，将localhost换成其IP地址
        // jdbc:mysql://192.168.1.6:3306/train

        // user: 连接数据库的用户名  root
        // password:连接数据库的密码 123456
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/train", "root", "123456");
        // 输出连接对象，只要不为null，就表示可以正常获取数据库连接
        System.out.println(connection);

        // 根据connection获取用于执行sql语句的语句对象（Statement）
        // 使用完之后，必须要关闭
        Statement statement = connection.createStatement();
        String sql = "select * from hero";
        // 执行查询后，会返回结果集对象，ResultSet,该对象用完后必须要关闭
        ResultSet rs = statement.executeQuery(sql);
        // 遍历resultset，获取数据
        while (rs.next()){// rs.next每执行一次，就会切换到下一行，直到结束
            // 一次获取每一行每一列的数据
            int id = rs.getInt(1); // 列索引，从 1 开始
            String sno = rs.getString(2);
            String sname = rs.getString("sname");
            String ssex = rs.getString("ssex");
            int sage = rs.getInt(5);



            System.out.println(id + ", " + sno + ", " + sname + "," + ssex + "," + sage);
        }

        // 后打开  先关闭
        rs.close();

        statement.close();
        // 连接用完之后必须要关闭，
        connection.close();
    }
}
