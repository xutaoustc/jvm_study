package jdk8.a_functional;

import jdk8.z_domain.F1;
import jdk8.z_domain.F2;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class FunctionMain {
    public static void main(String[] args) {
        JButton button = new JButton();
        button.addActionListener((s)-> System.out.println("s"));

        // 1. 函数式接口是Java函数式编程的根源和载体
        // 2. 在其他许多函数式编程的语言中，Lambda表达式的类型是函数，在Java中lambda表达式的类型是对象，是依附于函数式接口而存在的
        // 3. 函数式接口---如果一个接口有且仅有一个抽象方法（方便生成它的匿名内部类）
        // 4. 函数式编程是针对于函数式接口的匿名内部类来做的
        // 5. lambda表达式，方法引用，构造函数引用都可以创建函数式接口的匿名内部类
        // 6. 在赋值给具体的函数式接口的引用时，如果协议匹配，则可以生成对应类型的匿名内部类
        // 7. 之前的实现是写在方法里的，而现在是在调用端动态提供的
        List<String> list = Arrays.asList("1","2","3");
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        list.forEach(x-> System.out.println(x));

        // lambda表达式的类型是根据上下文推断出来的
        F1 f1 = ()->{};
        F2 f2 = ()->{};
    }
}

