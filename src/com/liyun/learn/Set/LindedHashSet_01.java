package com.liyun.learn.Set;

import java.util.LinkedHashSet;

/*
java.util.LinkedHashSet集合 extends HashSet集合
LinkedHashSet集合特点:
    底层是一个哈希表(数组+链表)+链表:多了一条链表(记录元素的存储顺序)，保证元素有序
 */
public class LindedHashSet_01 {
    public static void main(String[] args) {
        LinkedHashSet linkedHashSet=new LinkedHashSet();
        linkedHashSet.add("www");
        linkedHashSet.add("baidu");
        linkedHashSet.add("com");
    }
}
