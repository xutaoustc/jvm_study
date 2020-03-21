package jdk8.a_functional;

import jdk8.z_domain.Student;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferenceDemo {
    public static void main(String[] args) {
        // 可以将方法引用看做是一个"函数指针"

        // 4类方法引用， 使用场景，lambda表达式只有一行代码，且这个代码已经有现成的方法了
        Student student1 = new Student("zhangsan", 10);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 50);
        Student student4 = new Student("zhaoliu", 40);
        List<Student> students = Arrays.asList(student1,student2,student3,student4);

        // students.sort((s1,s2)-> Student.compareStudentByScore(s1,s2));
        //1. 类名::方法名
        // students.sort(Student::compareStudentByScore);

        // StudentComparator sc = new StudentComparator();
        // students.sort((s1,s2)->sc.compareStudentByScore(s1,s2));
        //2. 对象名::方法名
        //students.sort(sc::compareStudentByScore);

        //students.sort((s1,s2)->s1.compareByName(s2));
        //3. 类名::实例方法名
        //这里有点难以理解，因为lambda表达式传入两个参数，而这个函数仅有一个参数
        //但是这里和之前的不同，这里是实例方法（是要依靠对象来调用的），但是又没有明确指定调用者。这里实际是由lambda表达式的第一个参数来调用的，后续所有的参数都作为这个方法的参数
        //students.sort(Student::compareByScore);
        //List<String> sList = null;
        //sList.sort(String::compareToIgnoreCase);


        //4. 构造方法引用：类名::new
        //System.out.println(getString(String::new));
        //System.out.println(getString2("hello",String::new));

        students.forEach(s-> System.out.println(s.getScore()));
    }

    private static String getString(Supplier<String> supplier){
        return supplier.get() + ".test";
    }
    private static String getString2(String str, Function<String,String> fun){
        return fun.apply(str);
    }
}
