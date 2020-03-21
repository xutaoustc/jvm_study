package jdk8.c_collector;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StreamComparator {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");

        // Sort需要使用一个Comparator
        Collections.sort(list, (s1,s2)->s1.length() - s2.length()); // 升序
        Collections.sort(list, (s1,s2)->s2.length() - s1.length()); // 降序

        // comparingInt静态方法构建了一个Comparator
        Collections.sort(list, Comparator.comparingInt(String::length).reversed());

        // 两层比较
        list.sort(Comparator.comparingInt(String::length).thenComparing(String::compareToIgnoreCase));
        list.sort(Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        // comparing调用的是元素的compareTo方法
        list.sort(Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toLowerCase)));
        // comparing指定比较规则
        list.sort(Comparator.comparingInt(String::length).thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder())));
        list.sort(Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder())));

        // 多级排序
        list.sort(Comparator.comparingInt(String::length).reversed()
                    .thenComparing( Comparator.comparing(String::toLowerCase, Comparator.reverseOrder()))
                    .thenComparing( Comparator.reverseOrder() )
        );
    }
}





















