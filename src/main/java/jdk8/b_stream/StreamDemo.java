package jdk8.b_stream;

import jdk8.z_domain.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
//        streamUsage1();
//        streamCut();
        groupAndPartition();
    }

    private static void createStream(){
        // 创建方法1
        Stream stream = Stream.of("11","22");

        // 创建方法2
        String[] array = new String[]{"hello","world"};
        Stream stream1 = Stream.of(array);
        Stream stream2 = Arrays.stream(array);

        // 创建方法3
        List<String> list = Arrays.asList(array);
        Stream stream4 = list.stream();

        // 创建方法4
        Stream stream5 = Stream.generate(()->"aa");

        // 创建方法5
        Stream<Integer> stream6 = Stream.iterate(1,item->item+2).limit(10);
    }


    private static void streamUsage(){
        // Stream的使用过程：
        //     源
        //     中间操作
        //     终止求值
        IntStream.of(new int[]{1,2,3}).forEach(System.out::println);

        IntStream.range(3,8).forEach(System.out::println);

        Arrays.asList(1,2,3).stream().map(i->2*i).reduce(0,Integer::sum);
    }

    private static void streamUsage1(){
        Stream<String> stream = Stream.of("a","b");

        // Stream转数组
        String[] arr = stream.toArray(length -> new String[length]);
        // String[] arr1 = stream.toArray(String[]::new);

        // collect方法分析
        // stream.collect(Collectors.toList());
        // stream.collect(()->new ArrayList<String>(), (theList,item)->theList.add(item), (theList1,theList2)->theList1.addAll(theList2));
        // stream.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
    }

    private static void streamUsage2(){
        // 将Stream中的内容转成任意Collection
        // stream.collect(Collectors.toCollection(ArrayList::new));
        // Set<String> set = stream.collect(Collectors.toCollection(TreeSet::new));

        // Collectors是一种辅助类，里面有很多实现了Collector接口的静态方法
        Stream<String> stringStream = Stream.of("123","456","789");
        System.out.println(stringStream.collect(Collectors.joining()));

        // flatmap使用
        Stream<List<Integer>> listStream = Stream.of(Arrays.asList(1), Arrays.asList(2,3),Arrays.asList(4,5));
        listStream.flatMap(list->list.stream()).map(x->x*x).forEach(System.out::println);
    }

    private static void streamCut(){
        List<String> list = Arrays.asList("hello","world","helloworld");

        // 实际并不会都打印
        list.stream().mapToInt(item->{
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);
    }

    private static void descartes(){
        List<String> list1 = Arrays.asList("a","b","c");
        List<String> list2 = Arrays.asList("1","2","3");

        list1.stream().flatMap(x-> list2.stream().map(y-> x + " " + y)).collect(Collectors.toList());
    }

    private static void groupAndPartition(){
        Student student1 = new Student("zhangsan",100);
        Student student2 = new Student("lisi",90);
        Student student3 = new Student("wangwu",18);
        Student student4 = new Student("zhangsan",80);
        List<Student> students = Arrays.asList(student1, student2, student3, student4);

        /*  Collectors.groupingBy返回这样一个Collector
               1. supplier是一个HashMap
               2. 现在问题是如何accumulate一个A到这个HashMap中
                     如果根据选择条件，A.prop在HashMap中没有，那么就应该往HashMap中插入一条记录，那么插入的记录应该是子Collector的supplier
                     接着应该使用子Collector的accumulator
                     相当于Map的key是个分类条件，把对应的记录分类后，可以看做是与一般的collect无异
            BiConsumer<Map<K, A>, T> accumulator = (m, t) -> {
                K key = Objects.requireNonNull(classifier.apply(t), "element cannot be mapped to a null key");
                A container = m.computeIfAbsent(key, k -> downstreamSupplier.get());
                downstreamAccumulator.accept(container, t);
            };
        */
        Map<String, List<Student>> map = students.stream().collect(Collectors.groupingBy(Student::getName));
        Map<String,Long> countMap = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
        Map<String,Double> avgMap = students.stream().collect(Collectors.groupingBy(Student::getName,Collectors.averagingDouble(Student::getScore)));
        // Partition是一种特殊的只有2个部分的group
        Map<Boolean,List<Student>> partitionMap = students.stream().collect(Collectors.partitioningBy(student->student.getScore()>=90));
    }
}


























