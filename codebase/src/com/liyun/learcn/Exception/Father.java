package com.liyun.learcn.Exception;


/*
    子父类的异常:
        1.如果父类抛出了多个异常，子类重写父类方法时，抛出和父类相同的异常或者是父类异常的子类或者不抛出异常
        2.父类方法没有抛出异常，子类重写父类该方法时也不可抛出异常。此时子类产生该异常，只能捕获处理，不能声明抛出
     注意:
        父类异常是什么样，子类异常就是什么样
 */
public class Father {
    public void show01() throws NullPointerException,ClassCastException{}
    //索引越界异常
    public void show02() throws  IndexOutOfBoundsException{}
    public void show03() throws  IndexOutOfBoundsException{}
    public void show04(){}
}

class Son extends  Father{
    //1.子类重写父类方法时，抛出和父类相同的异常
    public void show01() throws NullPointerException,ClassCastException{}
    //2.子类重写父类方法时 抛出和父异常类的子类
    //数组索引越界异常是索引越界异常的子类
    public void show02() throws  ArrayIndexOutOfBoundsException{}
    //3.子类重写父类方法时，不抛出异常
    public void show03(){}
    //4.父类方法没有抛出异常，子类重写父类该方法时也不可抛出异常
    //如果有异常了，只能捕获处理，不能抛出
    public void show04(){
        try {
            throw new Exception("编译器异常");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
