package com.liyun.learn.Map;

import java.util.LinkedHashMap;

/*
java.util.linkedHashMap<K,V> extends HashMap<K,V>
Map 接口的哈希表和链接列表实现，具有可预知的迭代顺序
底层原理:
    哈希表+链表(记录元素的顺序)
 */
public class LinkedHashMap_01 {
    public static void main(String[] args) {
        LinkedHashMap<String,String> linked=new LinkedHashMap<>();
        linked.put("a","a");
        linked.put("b","b");
        linked.put("c","c");
        System.out.println(linked);
    }
}
