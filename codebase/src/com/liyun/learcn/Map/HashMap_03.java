package com.liyun.learcn.Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
    Map集合遍历的第二种方式:使用Entry对象遍历

    Map集合中方法:
        Set<Map.Entry<K,V> entrySet() 返回此映射中包含的映射关系的Set视图。
    实现步骤:
        1.使用Map集合中的方法entrySet(),把Map集合的多个Entry对象取出来，存储到一个Set集合中
        2.遍历 Set集合，获取每一个Entry对象
        3.使用Entry对象中的方法getKey()和getValue()获取键与值

 */
public class HashMap_03 {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("盖伦",185);
        map.put("瑞文",175);
        map.put("贾克斯",168);
        //1.使用Map集合中的方法entrySet(),把Map集合的多个Entry对象取出来，存储到一个Set集合中
        Set<Map.Entry<String,Integer>> set=map.entrySet();
        //2.遍历 Set集合，获取每一个Entry对象
        //使用迭代器遍历Set集合
        Iterator<Map.Entry<String, Integer>> it = set.iterator();
        while(it.hasNext()){
            //3.使用Entry对象中的方法getKey()和getValue()获取键与值
            Map.Entry<String,Integer> entry=it.next();
            System.out.println("键: "+entry.getKey()+" 值: "+entry.getKey());
        }
        System.out.println("-----------------------------------------------");
        for (Map.Entry<String, Integer> entry : set) {
            //3.使用Entry对象中的方法getKey()和getValue()获取键与值
            String key=entry.getKey();
            Integer value=entry.getValue();
            System.out.println("键: "+key+" 值: "+value);
        }
    }
}
