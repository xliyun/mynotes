package com.liyun.learcn.Exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
    java.lang.Throwable:类是 Java 语言中所有错误或异常的超类，有以下两个子类
        Exception:编译期异常，进行编译(写代码)java程序出现的问题
            RuntimeException:运行期异常，java程序运行过程中出现的问题，把异常处理掉，程序可以继续执行
        Error:错误
            必须修改源代码，程序才能执行

 */
public class Exception_01 {
    public static void main(String[] args) {
        //Exception:编译期异常，进行编译(写代码)java程序出现的问题,
        //像下面，不处理异常就没办法继续编写代码
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
        Date date=null;
        try{
            date=sdf.parse("2019-01-27");
        }catch (ParseException e){
            e.printStackTrace();
        }
        //RuntimeException:运行期异常，java程序运行过程中出现的问题，把异常处理掉，程序可以继续执行
        //在运行时可能出的错
        int[] arr={1,2,3};
        try{
            //可能会出现异常的代码
            System.out.println(arr[5]);
        }catch (Exception e){
            System.out.println(e);
        }
        /*
        Error:错误
        OutOfMemorryError: java heap space
        内存溢出的错误，创建的数组太大了，超出了给JVM分配的内存
         */
        int[] arr2s=new int[1024*1024*1024*1024*1024*1024*1024*1024*1024];
        System.out.println("----end-----");

    }
}
