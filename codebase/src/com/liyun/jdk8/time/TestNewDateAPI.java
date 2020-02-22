package com.liyun.jdk8.time;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-02-13 17:58
 */
public class TestNewDateAPI {

    //TemporalAdjuster:时间校正器
    @Test
    public void test5(){
        LocalDateTime ldt=LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        //下个周
        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        //自定义：下个工作日
        //TemporalAdjuster是一个接口
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;
            DayOfWeek dayOfWeek = ldt4.getDayOfWeek();

            //如果是周五，下个工作日就是周一
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

    //DateTimeFormatter:格式化时间/日期
    @Test
    public void test6(){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDateTime ldt = LocalDateTime.now();

        String strDate = ldt.format(dtf);
        System.out.println(strDate);

        System.out.println("---------------------");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

        String strDate2 = dtf2.format(ldt);
        System.out.println(strDate2);

        //strDate2是字符串，掉头发是格式
        LocalDateTime newDate = ldt.parse(strDate2, dtf2);
        System.out.println(newDate);

    }

    @Test
    public void test7(){
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);

        //设置时区
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Warsaw"));
        //输出2020-02-19T15:14:56.505
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.now();
        //带时区的时间日期格式
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Tokyo"));
        //输出2020-02-19T22:14:56.510+09:00[Asia/Tokyo] 比UTC有八个小时时差
        System.out.println(zdt);
    }
}
