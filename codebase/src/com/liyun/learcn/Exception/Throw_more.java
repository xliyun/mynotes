package com.liyun.learcn.Exception;

public class Throw_more {
    public static void main(String[] args) {
        /*
            多个异常使用捕获又该如何处理呢?
            1.多个异常分别处理。
            2.多个异常一次捕获，多次处理
            3.多个异常一次捕获一次处理。
         */
        /*
        2.多个异常一次捕获，多次处理
        一个try多个catch注意事项:
            catch里边定义的异常变量，如果有子父类关系，那么子类的异常变量必须写在上边，否则就会报错
            因为try中如果出现了异常对象，会把异常对象抛给catch处理
            抛出的异常对象，会从上到下一次赋值给catch中定义的异常变量
         */
        try {
            int[] arr={1,2,3};
            System.out.println(arr[3]);
            Integer integer=Integer.valueOf("a");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }catch (NumberFormatException e) {
            System.out.println(e.toString());
        }
        /*
        3.多个异常一次捕获一次处理。
         */
        try {
            int[] arr2={1,2,3};
            System.out.println(arr2[3]);
            Integer integer2=Integer.valueOf("a");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
