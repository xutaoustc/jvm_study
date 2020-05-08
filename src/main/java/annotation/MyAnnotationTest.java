package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// @Target, @Retention, @Document, @Inherited 4种元注解
@Target(value={ElementType.METHOD,ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    // 可以使用default给属性设置默认的初始值
    // 如果只有一个属性，而且属性的名字为value，则在使用时可以不使用属性名称
    String value() default "";
}

public class MyAnnotationTest{
    public static void main(String[] args) {

    }

    @MyAnnotation
    void test(){

    }
}
