package jdk8.time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;

public class Java8TimeDemo {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.getYear() + "  " + localDate.getMonthValue() + "  " + localDate.getDayOfMonth());

        LocalDate localDate2 = LocalDate.of(2019,11,25);
        System.out.println(localDate2);

        // 不关心年份
        LocalDate localDate3 = LocalDate.of(2010,3,25);
        MonthDay monthDay = MonthDay.of(localDate3.getMonth(), localDate3.getDayOfMonth());
        MonthDay monthDay2 = MonthDay.from(LocalDate.of(2011,3,25));
        if(monthDay.equals(monthDay2)){
            System.out.println("equals");
        }else{
            System.out.println("not equals");
        }

        // 不关心日期
        LocalTime time = LocalTime.now();
        System.out.println(time);
        LocalTime time2 = time.plusHours(3).plusMinutes(20);
        System.out.println(time2);
    }
}





















