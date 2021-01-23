package com.thread.tc3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/*
 Vector --> ArrayList --> CopyOnWriteArrayList 可以提高读操作的效率，但是如果写操作比较多的话，我们使用同步容器Vector性能比并发容器还要好
 Hashtable --> HashMap --> ConcurrentHashMap
 vector是同步的，同步容器，ArrayList不是同步的，CopyOnWriteArrayList 并发容器
 Hashtable是同步，HashMap不是同步的
 */
public class Demo {
    public static void main(String[] args) {
        ArrayList<String> s=new ArrayList<>();
        //在所有的方法上加了synchronized (mutex) 性能差的一比
        Collections.synchronizedList(s);

        HashMap<String,Object> map=new HashMap<>();
        Collections.synchronizedMap(map);


    }
}
