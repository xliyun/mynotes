package com.liyun.learcn.Exception;

import java.util.Objects;

/*
    throw关键字
    作用:
        可以使用throw关键字在指定的方法中抛出指定的异常
     使用格式:
        throw new xxxException("异常产生的原因");
     注意:
        1.throw关键字必须写在方法的内部
        2.throw关键字后边的new 对象必须是Exception或者Exception的子类对象
        3.throw关键字抛出指定的异常对象，我们就必须处理这个异常对象
            throw关键字后边创建的是RuntimeException或者是RuntimeException的子类对象，我们可以不处理，默认交给JVM处理(打印异常对象，中断程序)
            throw关键字后面创建的是编译异常(写代码的时候报错)，我们就必须处理这个异常，要么throws，要么try...catch

 */
public class Throw_02 {
    public static void main(String[] args) {
        //工作中我们首先必须对方法传递过来的参数进行合法性校验
        //如果参数不合法，必须使用抛出异常的方法，告知方法的调用者，传递的参数有问题
        int[] arr={1,2,3};
        int value=getElement(arr,-1);
        System.out.println(value);
    }
    public static int getElement(int[] arr,int index){
        /*
            我们对传递过来的数组进行合法性校验
            如果数组arr的值是null
            那么我们就抛出空指针异常，告知方法的调用者 传递的数组的值是null

            注意:
                NullPointerException是一个运行期异常，我们不用处理，默认交给JVM处理
         */
/*        if(arr==null){
            throw new NullPointerException("传递的数组是null");
        }*/
        //Objects判断是否为空的静态类
        Objects.requireNonNull(arr,"传递的数组是null");
        /*
            如果index的范围不再数组的索引范围内
            那么我们就抛出数组越界异常
         */
        if(index<0 ||index>arr.length-1){
            throw new ArrayIndexOutOfBoundsException("数组索引越界");
        }
        int ele=arr[index];
        return ele;
    }
}
