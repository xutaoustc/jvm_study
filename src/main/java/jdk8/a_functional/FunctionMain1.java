package jdk8.a_functional;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionMain1 {
    public static void main(String[] args) {
        FunctionMain1 test = new FunctionMain1();

        System.out.println( test.compute(2, value->value*3, value->value * value) );
        System.out.println( test.compute2(2, value->value*3, value->value * value) );

        System.out.println( test.compute3(1, 2 , (a,b)->a+b));
        System.out.println( test.compute3(1, 2 , (a,b)->a*b));

        System.out.println( test.compute4(2, 3 , (a,b)->a+b, a->a*a));
    }

    // 不管你外面怎么闹腾，到这里函数式编程的本质是匿名内部类就体现出来了
    public int compute(int a, Function<Integer,Integer> fun, Function<Integer,Integer> fun2){
        return fun.compose(fun2).apply(a);
    }

    public int compute2(int a, Function<Integer,Integer> fun, Function<Integer,Integer> fun2){
        return fun.andThen(fun2).apply(a);
    }

    public int compute3(int a, int b, BiFunction<Integer, Integer, Integer> biFunction){
        return biFunction.apply(a, b);
    }

    public int compute4(int a, int b, BiFunction<Integer, Integer, Integer> biFunction, Function<Integer,Integer> function){
        return biFunction.andThen(function).apply(a,b);
    }


}


