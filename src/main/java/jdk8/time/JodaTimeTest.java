package jdk8.time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public class JodaTimeTest {
    public static void main(String[] args) {
        // 不可变对象，可确保线程安全
        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);

        System.out.println( today.toString("yyyy-MM-dd") );
        System.out.println( tomorrow.toString("yyyy-MM-dd"));

        DateTime d1 = today.withDayOfMonth(1);
        System.out.println(d1);

        LocalDate localDate = new LocalDate();
        System.out.println(localDate);

        // 三个月后最后一天日期
        localDate = localDate.plusMonths(3).dayOfMonth().withMaximumValue();
        System.out.println(localDate);

        // 计算两年前第三个月的最后一天的日期
        DateTime dateTime = new DateTime();
        DateTime computedDate = dateTime.minusYears(2).monthOfYear().setCopy(3).dayOfMonth().withMaximumValue();
        System.out.println(computedDate);


        System.out.println(convertUTC2Date());
        System.out.println(convertDate2UTC(new Date()));
    }

    // 传入UTC返回Java Date
    private static Date convertUTC2Date(){
        // Z表示没有时区
        DateTime dateTime = DateTime.parse("2019-11-11T09:22:54.876Z", DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        // 本地时区
        return dateTime.toDate();
    }

    // 传输时间最好传输UTC标准时间，不带时区，由客户端根据所在时区转成本地时间
    // 服务器端存储不关心时区，由客户端决定应该使用哪个时区
    private static String convertDate2UTC(Date javaDate){
        DateTime dateTime = new DateTime(javaDate, DateTimeZone.UTC);
        return dateTime.toString();
    }
}



















