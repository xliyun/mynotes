package com.liyun.learn.reflection.java2;

import com.liyun.learn.reflection.java1.Person;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 获取运行时类的方法结构
 */
public class MethodTest {

    @Test
    public void test1(){
        Class clazz = Person.class;

        //getMethods(); 获取当前运行时类及其所有父类当中声明为public权限的方法
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }

        //getDeclaredMethods(): 获取当前运行时类当中声明的所有方法（不包含父类中声明的方法）
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println(m);
        }


    }

    /*
     @Xxx
     权限修饰符 返回值类型 方法名（参数类型1 形参1,...） throws XxxException{}
     */
    @Test
    public void test2(){
        Class clazz = Person.class;

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            //1.获取方法声明的注解
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a : annotations) {
                System.out.println(a);
            }

            //2.权限修饰符
            System.out.print(Modifier.toString(m.getModifiers())+"\t");


            //3.方法的返回值类型
            System.out.print(m.getReturnType().getName()+"\t");

            //4.方法名
            System.out.print(m.getName());
            System.out.print("(");
            //5.形参列表
            Class[] parameterTypes = m.getParameterTypes();
            if(!(parameterTypes == null || parameterTypes.length==0)){
                for (int i = 0;i<parameterTypes.length;i++) {
                    if(i==parameterTypes.length-1){
                        //简写的名字String parameterTypes[i].getSimpleName();
                        System.out.print(parameterTypes[i].getName()+" args_"+i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName()+" args_"+i+",");
                }
            }
            System.out.print(")");

            //6.抛出的异常
            Class[] exceptionTypes = m.getExceptionTypes();
            if(exceptionTypes.length>0)
            {
                System.out.print("throws ");
                for(int j=0;j<exceptionTypes.length;j++){
                    if(j==exceptionTypes.length-1){
                        System.out.print(exceptionTypes[j].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[j].getName()+",");
                }
            }
            System.out.println();
        }

    }
}
