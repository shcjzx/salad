package homework4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
* @Description: StudentOperatable 的实现类 对student表的操作的具体实现
* @Date: 2019/8/6
*/
public class StudentOperator implements StudentOperatable {
    @Override
    public List<Student> findAllStudent() {
        // 获取mysql链接
        Connection connection = JdbcUtil.getInstance().getConnention();
        // 定义Statement ResultSet sql
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM student";
        // 定义studentlist 返回查询结构
        List<Student> studentList = new ArrayList<>(16);

        try {
            //
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            Student student = null;
            while (resultSet.next()) {
                // 创建Student对象
                student = new Student();
                // 设置对象属性
                student.setSno(resultSet.getString(1));
                student.setSname(resultSet.getString(2));
                student.setSsex(resultSet.getString(3));
                student.setSbirthday(resultSet.getString(4));
                student.setClz(resultSet.getString(5));
                // 把Student对象添加到list集合中
                studentList.add(student);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源资源
            JdbcUtil.getInstance().closeResourse(resultSet);
            JdbcUtil.getInstance().closeResourse(statement);
            JdbcUtil.getInstance().closeResourse(connection);
        }

        return studentList;
    }

    @Override
    public int saveStudent(Student student) {
        Connection connection = JdbcUtil.getInstance().getConnention();
        Statement statement = null;
        String sql = "INSERT INTO student VALUES('"
                + student.getSno() + "','"
                + student.getSname() + "','"
                + student.getSsex() + "','"
                + student.getSbirthday() + "','"
                + student.getClz() + "')";
        int affectedRows = 0;
        try {
            statement = connection.createStatement();
            affectedRows = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源链接
            JdbcUtil.getInstance().closeResourse(statement);
            JdbcUtil.getInstance().closeResourse(connection);
        }
        return affectedRows;
    }

    @Override
    public int updateStudent(Student student) {
        Connection connection = JdbcUtil.getInstance().getConnention();
        Statement statement = null;
        String sql = "UPDATE student SET sname = '" + student.getSname()
                + "', ssex = '" + student.getSsex()
                + "', sbirthday = '" + student.getSbirthday()
                + "', class = '" + student.getClz()
                + "' WHERE sno = '" + student.getSno() + "'";
        int affectRows = 0;
        try {
            statement = connection.createStatement();
            // 执行sql
            affectRows = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源链接
            JdbcUtil.getInstance().closeResourse(statement);
            JdbcUtil.getInstance().closeResourse(connection);
        }
        return affectRows;
    }

    @Override
    public int removeStudent(String sno) {
        Connection connection = JdbcUtil.getInstance().getConnention();
        Statement statement = null;
        String sql = "DELETE FROM student WHERE sno = '"+ sno +"'";
        int affectRows = 0;
        try {
            statement = connection.createStatement();
            // 执行sql
            affectRows = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            // 关闭资源链接
            JdbcUtil.getInstance().closeResourse(statement);
            JdbcUtil.getInstance().closeResourse(connection);
        }
        return affectRows;
    }

    @Override
    public List<Student> findBySnameLike(String sname) {
        // 获取mysql链接
        Connection connection = JdbcUtil.getInstance().getConnention();
        // 定义PreparedStatement 引用
        PreparedStatement preparedStatement = null;
        // 定义结果集引用
        ResultSet resultSet = null;
        String sql = "select * from student where sname like '%"+ sname +"%'";
        // 定义List 集合 并初始容量16 用于返回查询结果
        List<Student> studentList = new ArrayList<>(16);

        try {
            // 传入sql 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 执行sql语句
            resultSet = preparedStatement.executeQuery(sql);

            Student student = null;
            while (resultSet.next()) {
                // 创建student对象
                student = new Student();
                // 设置属性
                student.setSno(resultSet.getString(1));
                student.setSname(resultSet.getString(2));
                student.setSsex(resultSet.getString(3));
                student.setSbirthday(resultSet.getString(4));
                student.setClz(resultSet.getString(5));
                // 把Student对象添加到list集合中
                studentList.add(student);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源链接
            JdbcUtil.getInstance().closeResourse(resultSet);
            JdbcUtil.getInstance().closeResourse(preparedStatement);
            JdbcUtil.getInstance().closeResourse(connection);
        }

        return studentList;
    }

    @Override
    public List<Student> findOrderBySnameLike() {
        // 获取mysql链接
        Connection connection = JdbcUtil.getInstance().getConnention();
        // 定义Statement ResultSet sql
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from student order by sname desc";
        // 定义studentlist 返回查询结构
        List<Student> studentList = new ArrayList<>(16);

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            Student student = null;
            while (resultSet.next()) {
                student = new Student();
                student.setSno(resultSet.getString(1));
                student.setSname(resultSet.getString(2));
                student.setSsex(resultSet.getString(3));
                student.setSbirthday(resultSet.getString(4));
                student.setClz(resultSet.getString(5));
                // 把Student对象添加到list集合中
                studentList.add(student);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源链接
            JdbcUtil.getInstance().closeResourse(resultSet);
            JdbcUtil.getInstance().closeResourse(preparedStatement);
            JdbcUtil.getInstance().closeResourse(connection);
        }

        return studentList;
    }

    @Override
    public List<Student> findBySnameLikeOrderLimit(String sname, int currPage, int pageSize) {
        // 获取mysql链接
        Connection connection = JdbcUtil.getInstance().getConnention();
        // 定义Statement ResultSet sql
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from student where sname like '%"
                + sname + "%' order by sname limit "
                + currPage * pageSize + "," + pageSize;
        // 定义studentlist 返回查询结构
        List<Student> studentList = new ArrayList<>(16);

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            Student student = null;
            while (resultSet.next()) {
                // 创建Student对象
                student = new Student();
                // 设置Student对象值
                student.setSno(resultSet.getString(1));
                student.setSname(resultSet.getString(2));
                student.setSsex(resultSet.getString(3));
                student.setSbirthday(resultSet.getString(4));
                student.setClz(resultSet.getString(5));
                // 把Student对象添加到list集合中
                studentList.add(student);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源链接
            JdbcUtil.getInstance().closeResourse(resultSet);
            JdbcUtil.getInstance().closeResourse(preparedStatement);
            JdbcUtil.getInstance().closeResourse(connection);
        }

        return studentList;
    }
}
