package com.liyun.learn.reflection.java2;

import com.liyun.learn.reflection.java1.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 调用运行时类中指定的结构：属性、方法、构造器
 */
public class ReflectionTest {

    /*
    不理想，了解
     */
    @Test
    public void testField() throws Exception {
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person)clazz.newInstance();

        //获取指定的属性:要求运行时类中属性声明为public
        //通常不采用此方法
        Field id = clazz.getField("id");

        /*
        设置当前属性的值
        set(): 参数1：指明设置哪个对象的属性， 参数2：将此属性的值设置为多少
        */
        id.set(p,1001);
        /*
        获取当前属性的值
        get():参数1：获取哪个对象的当前属性值
         */
        int pId = (int)id.get(p);
        System.out.println(pId);

        //=================================
    }

    /*
    如何操作运行时类中指定的属性 -- 重点
     */
    @Test
    public void testField2() throws Exception{
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person)clazz.newInstance();

        //1.getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");

        //2.保证当前属性是可访问的
        name.setAccessible(true);
        name.set(p,"Tom");
        //如果是静态属性，可以不写类.class name.set("Tom")
        Field staticDesc = clazz.getDeclaredField("staticDesc");
        staticDesc.setAccessible(true);
        staticDesc.set(null,"哈哈");

        //3.获取、设置指定对象的此属性值
        System.out.println(name.get(p));
        System.out.println(staticDesc.get(null));
    }

    /*
    如何操作指定运行时类中的指定的方法 -- 重点
     */
    @Test
    public void testMethod() throws Exception{
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person)clazz.newInstance();

        /*
          1.获取指定的某个方法
          getDeclaredMethod(): 参数1：指明获取的方法的名称
                               参数2：指明获取的方法的形参列表
         */
        Method show = clazz.getDeclaredMethod("show", String.class);

        show.setAccessible(true);
        /*
         invoke(): 参数1：方法的调用者
                   参数2：给方法形参赋值的实参
                   返回值：对应类中调用方法的返回值
         */
        String result = (String)show.invoke(p, "中国");
        System.out.println(result);

        System.out.println("***************如何调用静态方法*****************");

        // private static void showDesc()
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        //如果调用的运行时类中的方法没有返回值，则invoke()返回null
        //静态方法传递的参数可以输入null
        Object invoke2 = showDesc.invoke(null);
        Object invoke = showDesc.invoke(Person.class);
        System.out.println(invoke);
    }

    /*
     如何调用运行时类中指定的构造器
     */
    @Test
    public void testConstructor() throws Exception{
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        //private Person(String name)
        /*
         1.获取指定的构造器
         getDeclaredConstructor():参数：指定构造器的参数列表
         */
        Constructor constructor = clazz.getDeclaredConstructor();

        //2.保证次构造器是可访问的
        constructor.setAccessible(true);
        //3.调用此构造器创建运行时类的对象，需要传递参数
        Person person = (Person) constructor.newInstance();
        System.out.println(person);

    }
}
