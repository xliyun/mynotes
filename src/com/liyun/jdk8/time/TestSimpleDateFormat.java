package com.liyun.jdk8.time;



import sun.util.resources.LocaleData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-01-29 20:50
 */
public class TestSimpleDateFormat {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        Callable<Date> call = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                //return sdf.parse("2020-01-29");
                return DateFormatThreadLocal.convert("2020-01-29");
            }
        };


        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results= new ArrayList<>();

        for(int i= 0;i<10;i++){
            results.add(pool.submit(call));
        }

        for (Future<Date> result : results) {
            System.out.println(result.get());
        }

        pool.shutdown();

        System.out.println("===============新日期===================");
        ExecutorService pool2 = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> results2= new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");

        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("2020-11-23",dtf);
            }
        };

        for (int j=0;j<10;j++){
            results2.add(pool2.submit(task));
        }
    }
}
