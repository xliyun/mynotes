package com.liyun.learcn.Static;
/*
如果一个成员变量使用了static关键字，那么这个变量不再属于对象自己，而是属于所在的类。多个对象共享一份数据
静态变量：类名称.静态变量
静态方法：雷明春.静态方法()

注意事项：
1.静态只能直接访问静态，不能直接访问非静态
原因，类初始化的时候，先初始化静态
2.静态方法当中不能用this
原因：this代表当前对象，通过堆调用的方法，谁就是当前对象
 */
public class Student {
    public static void main(String[] args) {
        Student stu1=new Student("小明","22");
        stu1.room="体育馆";
        Student stu2=new Student("小红","23");
        System.out.println(stu2.id+" "+stu2.room);

        stu2.methodStatic();//正确，不推荐，这种写法在编译之后被javac翻译成类名称.静态方法
        Student.methodStatic();//正确，推荐
    }
    private String name;
    private String age;
    static String room;//所在教室

    private int id;//学号
    private static int idCounter=0;//学号计数器，每当new一个新计数器，计数器++

    public static void methodStatic(){
        System.out.println("我是静态方法");
    }

    public Student() {
        idCounter++;
    }

    public Student(String name, String age) {
        this.name = name;
        this.age = age;
        this.id=++idCounter;
    }
    public int getId() {return id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAge() {return age;}
    public void setAge(String age) {this.age = age;}
}
