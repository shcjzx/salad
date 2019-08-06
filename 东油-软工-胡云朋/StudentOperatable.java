package homework4;

import java.util.List;

public interface StudentOperatable {
    /**
    * @Description: 查询所有学生信息并返回
    * @return: List<Student>
    */
    List<Student> findAllStudent();
    /**
    * @Description: 添加一条学生记录
    * @param student
    * @return: int
    */
    int saveStudent(Student student);
    /**
     * @Description: 更新一条学生记录
     * @param student
     * @return: int
     */
    int updateStudent(Student student);
    /**
     * @Description: 根据sno删除一条学生记录
     * @param sno
     * @return: int
     */
    int removeStudent(String sno);
    /**
    * @Description: 根据学生姓名模糊查询并返回
    * @param sname
    * @return: List<Student>
    */
    List<Student> findBySnameLike(String sname);
    /**
    * @Description: 查询所有学生信息 并按照sname降序排序
    * @param
    * @return: List<Student>
    */
    List<Student> findOrderBySnameLike();
    /**
    * @Description: 通过sname模糊查询学生信息 升序 分页
    * @param sname
    * @param currPage
    * @param pageSize
    * @return: List<Student>
    */
    List<Student> findBySnameLikeOrderLimit(String sname, int currPage, int pageSize);

}
