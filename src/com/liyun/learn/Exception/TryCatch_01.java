package com.liyun.learn.Exception;

import java.io.FileNotFoundException;

/*
    try...catch:异常处理的第二种方式，自己处理异常
    格式:
        try{
            可能产生异常的代码
        }catch(定义一个异常的变量，用来接收try中抛出的异常对象){
            异常的处理逻辑
        }
        ...
        catch(异常类名 变量名){
        }
     注意:
        1.try中可能会抛出多个异常对象，那么就可以使用多个catch来处理这些异常对象
        2.如果try中产生了异常，那么就会执行catch中的异常处理逻辑，执行完毕catch中的处理逻辑，继续执行try...cathch之后的代码
 */
public class TryCatch_01 {
    public static void main(String[] args) {
        try {
            readFile("c:\\b.txt");
        } catch (FileNotFoundException e) {
            /*
                Throwable类中定义了3个异常处理的方法
                String getMessage() 返回此throwable的详细消息字符串。
                String toString() 返回此throwable的简短描述。重写了Object的toString方法
                void printStackTrace() JVM打印异常对象，默认调用此方法，异常信息是最全面的
             */
            System.out.println("------Throwable类中3个异常处理的方法-----");
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            e.printStackTrace();
            System.out.println("catch - 传递的文件路径不是c:\\a.txt ");
        }
        /*
        finally不能单独使用，必须和try一起使用
        finally一般用于资源释放(资源回收),无论程序是否出现异常，最后都要释放资源(IO)
         */
        finally {
            System.out.println("资源释放");
        }
    }
    public static void readFile(String fileName) throws FileNotFoundException {
        if(!fileName.equals("c:\\\\a.txt")){
            throw new FileNotFoundException("传递的文件路径不是c:\\a.txt");
        }
        System.out.println(fileName);
    }
}
