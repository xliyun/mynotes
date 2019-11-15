package com.liyun.learcn.Map;

import java.util.HashMap;
import java.util.Hashtable;

/*
java.util.Hashtable<K,V>集合 implements Map<K,V>
Hashtable:底层也是一个哈希表，是一个线程安全的集合，是单线程集合，速度慢
HashMap:底层也是一个哈希表，是一个线程不安全的集合，是多线程的集合，速度快

HashMap集合(之前学的所有的集合):可以存储null值，null键
Hashtable集合，不能存储null值，null键

Hashtable和Vector集合一样，在jdk1.2之后被更先进的集合(HashMap,ArrayList)给取代了
Hashtable的子类Properties依然活跃在历史的舞台
Properties集合是唯一和IO流相结合的集合

 */
public class HashTable_01 {
    public static void main(String[] args) {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put(null,"a");
        hashMap.put("b",null);
        hashMap.put(null,null);
        System.out.println(hashMap);//{null=null,b=null}

        Hashtable<String,String> hashtable=new Hashtable<>();
        hashtable.put(null,null);
    }
}
