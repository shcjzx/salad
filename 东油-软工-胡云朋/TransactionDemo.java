package homework5;

import javax.smartcardio.CommandAPDU;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDemo {
    public static void main(String[] args){
        // 获取链接
        Connection connection = JdbcUtil.getInstance().getConnention();
        PreparedStatement preparedStatement = null;
        try {
            // 关闭自动自动提交事务
            connection.setAutoCommit(false);
            // 执行第一条SQL语句
            // sql语句 插入一个账单从1号账户中取出20块
            String sql = "insert into bill values(null, 1, null, 20)";
            // 传入sql语句 获取PreparedStatement对象，然后执行
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            connection.commit();
            // 第二条SQL语句
            // sql 修改账户的money 减去20
            sql = "update account set totalmoney = totalmoney - 20 where aid = 1";
            // 从新获取preparedStatement对象  并执行
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            //提条事务
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭链接释放资源
            JdbcUtil.getInstance().closeResourse(preparedStatement);
            JdbcUtil.getInstance().closeResourse(connection);
        }


    }
}
