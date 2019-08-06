package homework4;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentOperator implements StudentOperatable {
    @Override
    public List<Student> listStudents() {
        Connection connection = JdbcUtil.getInstance().getConnention();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        String sql = "SELECT * FROM student";
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Student> studentList = new ArrayList<>(16);

        try {
            Student student = null;
            while (resultSet.next()) {
                student = new Student();
                student.setSno(resultSet.getString(1));
                student.setSname(resultSet.getString(2));
                student.setSsex(resultSet.getString(3));
                student.setSbirthday(resultSet.getString(4));
                student.setClz(resultSet.getString(5));
                studentList.add(student);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    @Override
    public int saveStudent(Student student) {
        Connection connection = JdbcUtil.getInstance().getConnention();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO student VALUES('"
                + student.getSno() + "','"
                + student.getSname() + "','"
                + student.getSsex() + "','"
                + student.getSbirthday() + "','"
                + student.getClz() + "')";
        int affectedRows = 0;
        try {
            affectedRows = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    public int updateStudent(Student student) {
        Connection connection = JdbcUtil.getInstance().getConnention();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "UPDATE student SET sname = '" + student.getSname()
                + "', ssex = '" + student.getSsex()
                + "', sbirthday = '" + student.getSbirthday()
                + "', class = '" + student.getClz()
                + "' WHERE sno = '" + student.getSno() + "'";
        int affectRows = 0;
        try {
            affectRows = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public int removeStudent(String sno) {
        Connection connection = JdbcUtil.getInstance().getConnention();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "DELETE FROM student WHERE sno = '"+ sno +"'";
        int affectRows = 0;
        try {
            affectRows = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectRows;
    }
}
