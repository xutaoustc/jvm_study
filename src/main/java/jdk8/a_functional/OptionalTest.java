package jdk8.a_functional;


import jdk8.z_domain.Company;

import java.util.Collections;
import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
//        Optional<String> optional = Optional.of("hello");
        Optional<String> optional = Optional.empty();

        // 面向对象的方式使用optional和之前的判断Null并没有本质区别，这个不是推荐的使用方法
//        if(optional.isPresent()){
//            System.out.println(optional.get());
//        }

        // 推荐的使用方式
        optional.ifPresent(item -> System.out.println(item));
        System.out.println( optional.orElse("sss") );
        System.out.println( optional.orElseGet(()->"你好") );

        Company company = new Company();
//        Company company = null;
//        company.setEmployees(Arrays.asList(new Employee()));

//  传统的编程方法
//        List<Employee> employees = company.getEmployees();
//        if(employees != null){
//            return employees;
//        }else{
//            return Collections.emptyList();
//        }

// 函数式编程
        Optional<Company> optionalCompany = Optional.ofNullable(company);
        System.out.println(
            optionalCompany.map(company1->company1.getEmployees()).orElse(Collections.emptyList())
        );

        // 不要将Optional作为方法参数或者字段，一般它只作为方法返回值
    }
}


