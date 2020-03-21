package jdk8.c_collector;

import jdk8.z_domain.Student;

import java.util.*;
import java.util.stream.Collectors;




// collect方法极其重要
// Collector接口特别重要，包括supplier, accumulator, combiner, finisher
public class StreamCollector {
    public static void main(String[] args) {
        Student student1 = new Student("zhangsan",100);
        Student student2 = new Student("lisi",90);
        Student student3 = new Student("wangwu",18);
        Student student4 = new Student("zhangsan",80);
        List<Student> students = Arrays.asList(student1, student2, student3, student4);


        students.stream().collect(Collectors.minBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        students.stream().collect(Collectors.averagingDouble(Student::getScore)) ;
        students.stream().collect(Collectors.summingInt(Student::getScore)) ;
        students.stream().collect(Collectors.summarizingInt(Student::getScore)) ;

        // 二级汇总
        Map<Integer,Map<String,List<Student>>> doubleGroup =
                students.stream().collect(Collectors.groupingBy(Student::getScore, Collectors.groupingBy(Student::getName)));

        Map<Boolean,Map<Boolean,List<Student>>> doublePartition =
                students.stream().collect(Collectors.partitioningBy(student->student.getScore()>60, Collectors.partitioningBy(student->student.getScore()>80)));

        Map<Boolean,Long> partitioncount =
                students.stream().collect(Collectors.partitioningBy(student->student.getScore()>60, Collectors.counting()));

        students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingInt(Student::getScore)),Optional::get)));
    }
}
