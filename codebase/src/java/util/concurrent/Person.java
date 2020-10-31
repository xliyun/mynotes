package java.util.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-10-03 23:37
 */
public class Person {

    private Integer i = 0;
    private static sun.misc.Unsafe UNSAFE;
    //偏移量
    private static long I_OFFSET;
    private String[] table = {"1","2","3","4"};

    static{
        Field field = null;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
            I_OFFSET = UNSAFE.objectFieldOffset(Person.class.getDeclaredField("i"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Person person = new Person();

        //拿内存当中最新的值
        //数组中存储的对象的对象头大小
        int ns = UNSAFE.arrayIndexScale(String[].class);
        //数组中第一个元素的起始位置
        int base = UNSAFE.arrayBaseOffset(String[].class);
        System.out.println(UNSAFE.getObject(person.table,base+1*ns));
//
//        Thread thread= new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//               boolean b = UNSAFE.compareAndSwapInt(person,I_OFFSET,person.i,person.i+1);
//               if(b){
//                   System.out.println(UNSAFE.getIntVolatile(person,I_OFFSET));
//               }
//            }
//        });
//
//        thread.start();
//
//        Thread thread2= new Thread(new Runnable() {
//            @Override
//            public void run() {
//                UNSAFE.compareAndSwapInt(person,I_OFFSET,person.i,person.i+1);
//            }
//        });
//
//        thread2.start();
    }
}
