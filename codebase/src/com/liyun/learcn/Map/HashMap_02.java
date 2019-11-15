package com.liyun.learcn.Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
    Map集合的第一种遍历方式:通过键找值的方式
    Map集合中的方法keySet() 返回樱色中包含的键的Set 视图
    实现步骤:
        1.使用Map集合中的方法keySet()，把Map集合所有的key取出来，存储到一个Set集合中
        2.遍历set集合，获取Map集合中的每一个key
        3.通过Map集合中的方法get(key),通过key找到value
 */
public class HashMap_02 {
    public static void main(String[] args) {
        Map<String,Integer> hashmap=new HashMap<>();
        hashmap.put("小明",22);
        hashmap.put("小萝卜",33);
        hashmap.put("小红",18);
        Set<String> set=hashmap.keySet();
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()){
            String key=iterator.next();
            Integer value=hashmap.get(key);
            System.out.println(value);
        }
        Set<String> set2=hashmap.keySet();
        for (String key : set2) {//(String key: hashmap.keySet())
            Integer value=hashmap.get(key);
        }
    }
}
