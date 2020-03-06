package com.liyun.learcn.reflection;

import org.junit.jupiter.api.Test;

import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-02-23 17:29
 */
public class ReflectionTest {

    //反射之前，对于Person的操作
    @Test
    public void test1(){
        //1.创建Person类的对象
        Person p1 = new Person("Tom",12);

        //2.通过对象，调用其内部的属性、方法
        p1.age = 10;
        p1.show();

        //在Person类外部，不可以通过Person类的对象调用其内部的私有结构
        //比如: name、showNation()以及私有的构造器


    }

    //反射之后，对于Person的操作
    @Test
    public void test2() throws Exception {
        //1.通过反射，创建Person类的对象
        Class clazz = Person.class;
        Constructor cons  = clazz.getConstructor(String.class, int.class);

        Object obj = cons.newInstance("Tom", 22);
        Person p = (Person)obj;
        System.out.println(p.toString());

        //2.通过反射，调用对象指定的属性、方法
        //调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(p,10);

        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

         //通过反射，可以调用Person类的私有结构的。比如:私有的构造器、方法、属性
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p1= (Person)cons1.newInstance("Jerry");
        System.out.println(p1);

        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1,"HanMeimei");
        System.out.println(p1);

        //调用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String)showNation.invoke(p1, "中国");
        System.out.println(nation);

        //疑问1：通过直接new的方式或反射的方法都可以调用公共的结构，开发中到底用哪个？
        //建议: 直接new的方式
        //什么时候会使用：反射的方式。反射的特征：动态性
        //疑问2：反射机制与面向对象中的封装性是不是矛盾？如何看待两个技术？
        //不矛盾。

        /*
         关于java.lang.Class类的理解
         1.类的加载过程
         编译：程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾),
         加载：接着我们使用java.exe命令对某个字节码文件进行解析使用。相当于将某个字节码文件加载到内存中，此过程
         称为类的加载。
         加载到内存中的类，我们称为运行时类，就作为Class的一个实例。

         2、换句话说，Class的示例就是对应着一个运行时类
         3.加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式来获取运行时类
         */

    }
    //获取Class的实例的方式
    //3.加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式来获取运行时类
    @Test
    public void test3() throws Exception{
        //方式一：调用运行时类的属性：.class
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1);

        //方式二：通过运行时类的对象
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);

        //方式三：调用Class的静态方法：forName(String classPath) 类的全类名
        Class clazz3 = Class.forName("com.liyun.learcn.reflection.Person");
        System.out.println(clazz3);
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);

        //方式四：使用类的加载器：ClassLoader(了解)
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class  clazz4 = classLoader.loadClass("com.liyun.learcn.reflection.Person");
        System.out.println(clazz1==clazz4);
    }

    @Test
    public void test4(){
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11= b.getClass();
        //只要元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }

    @Test
    public void test5(){
        int num = new Random().nextInt(3);//0,1,2
        String classPath = "";
        switch (num){
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.lang.Object";
                break;
            case 2:
                classPath = "com.liyun.learcn.reflection.Person";
                break;
            default:
                break;
        }

        Object obj = null;
        try {
            obj = getInstance(classPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(obj);
    }
    /*
    创建一个指定类的对象
    classPath:指定类的全类名
     */
    public Object getInstance(String classPath) throws Exception{
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }
}
