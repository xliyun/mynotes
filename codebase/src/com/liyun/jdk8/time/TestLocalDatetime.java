package com.liyun.jdk8.time;

import org.junit.jupiter.api.Test;

import java.time.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-01-30 9:59
 */
public class TestLocalDatetime {
    //1.LocalDate LocalTime LocalDateTime
    @Test
    public void test1(){
        LocalDateTime ldt= LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2020, 1, 30, 9, 12);
        System.out.println(ldt2);

        //加时间
        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);

        //减时间
        LocalDateTime ldt4 = ldt3.minusMonths(2);
        System.out.println(ldt4);

        //获取年，Int类型
        System.out.println(ldt.getYear());
        Month month = ldt.getMonth();
        System.out.println(month.getValue());

        //一个月的第几天
        System.out.println(ldt.getDayOfMonth());

        System.out.println(ldt.getHour()+":"+ldt.getMinute()+":"+ldt.getSecond());

    }

    //2. Instant:时间戳（以 Unix 元年：1970年1月1日 00:00:00到某个时间之间的毫秒值）
    @Test
    public void test2(){
        //默认获取UTC（世界协调时间）时区
        Instant ins1 = Instant.now();
        System.out.println(ins1);

        //对时区做一个偏移量计算
        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
        //输出带偏移量的时间
        System.out.println(odt);

        //转换成毫秒
        System.out.println(ins1.toEpochMilli());

        //从1970年1月1日0时0分0秒加上1000秒
        Instant ins2 = Instant.ofEpochSecond(1000);
        System.out.println(ins2);

    }

    //3.
    //Duration:计算两个“时间”之间的间隔
    //Period:计算两个“日期”之间的间隔
    @Test
    public void test3() throws InterruptedException {
        Instant ins1=Instant.now();
        Thread.sleep(1000);
        Instant ins2=Instant.now();

        Duration duration = Duration.between(ins1, ins2);
        //获取秒 getSecod 纳秒 getNano
        //而获取 毫秒 toMillis
        System.out.println(duration.getNano());

        System.out.println("-----------------------");

        LocalTime lt1 = LocalTime.now();
        Thread.sleep(1000);
        LocalTime lt2 = LocalTime.now();
        System.out.println(Duration.between(lt1,lt2).toMillis());

        System.out.println("===========日期=================");
        LocalDate ld1=LocalDate.of(2015,1,1);
        LocalDate ld2=LocalDate.now();

        Period period = Period.between(ld1, ld2);
        System.out.println(period.getYears());
        System.out.println(period.getDays());
    }
}
