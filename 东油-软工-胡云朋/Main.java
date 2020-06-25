package homework4;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentOperatable studentOperatable = new StudentOperator();
        List<Student> list = studentOperatable.findBySnameLike("");
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
