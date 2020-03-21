package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.METHOD,ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String value() default "";
}

public class MyAnnotationTest{
    public static void main(String[] args) {

    }

    @MyAnnotation
    void test(){

    }
}
