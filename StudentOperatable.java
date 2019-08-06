package homework4;

import java.util.List;

public interface StudentOperatable {
    List<Student> listStudents();
    int saveStudent(Student student);
    int updateStudent(Student student);
    int removeStudent(String sno);
}
