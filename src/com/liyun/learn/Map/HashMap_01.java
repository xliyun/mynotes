package com.liyun.learn.Map;

import java.util.HashMap;
import java.util.Map;

/*
java.util.Map<k,v>
Map集合的特点:
    1.Map集合是一个双列集合，一个元素包含两个值(一个key,一个value)
    2.Map集合中的元素，key和value的数据类型可以相同，也可以不同
    3.Map集合中的元素，key是不允许重复的，value是可以重复的
    4.Map集合中的元素,key和value是一一对应
Map常用方法
    public V put(K key,V value): 把指定的键与指定的值添加到Map集合中
        存储键值对时，如果key不重复，返回值V是null
        如果key重复，会返回旧的value值
    public V remove(Object key): 把指定的键 所对应的键值对元素 在Map集合中删除，返回被删除元素的值
        key存在，v返回被删除的值
        key不存在，返回null
    public V get(Object key): 根据指定的键，在Map集合中获取对应的值
    boolean containKey(Object key): 判断集合中是否包含指定的键
    boolean containValue(Object value): 判断集合中是否包含指定的值 包含返回true,不包含返回false
    public Set<K> keySet(): 获取Map集合中所有的键，存储到Set集合中
    public Set<Map.Entry<K,v>> entrySet(): 获取到Map集合中所有的键值对对象的集合(Set集合)

java.util.HashMap<k,v>集合
HashMap集合的特点
    1.HashMap集合底层是哈希表:查询的速度特别的快
        JDK1.8之前:数组+单向链表
        JDK1.8之后:数组+单向链表/红黑树(链表长度超过8)提高查询速度
    2.HasMap集合是一个无序的集合，存储和取出元素的顺序有可能不一致
java.util.LinkedHashMap<k,v>集合 extends HashMap<k,v>集合
LinkedHashMap的特点:
    1.LinedHashMap集合底层是哈希表+链表
    2.LinedHashMap集合是一个有序的集合，存储元素和取出元素的顺序是一致的

 */
public class HashMap_01 {
    public static void main(String[] args) {
        Map<String,String> hashmap=new HashMap<>();
        String v1=hashmap.put("周杰伦","昆凌");
        System.out.println("map里没有数据返回的是"+v1);//是null
        String v2=hashmap.put("周杰伦","昆凌2");
        System.out.println(v1);

        hashmap.put("黄晓明","杨颖");
        hashmap.put("张杰","谢娜");
        hashmap.put("冯绍峰","赵丽颖");

        /*
          public V remove(Object key): 把指定的键 所对应的键值对元素 在Map集合中删除，返回被删除元素的值
        */
        Map<String,Integer> hashmap2=new HashMap<>();
        hashmap2.put("小明",22);
        hashmap2.put("小红",33);
        hashmap2.put("小刚",34);

        System.out.println("第一次删除周杰伦返回的值: "+(hashmap2.remove("小明")));
        //这里注意我们接受返回值时不能用int等常量类型，它们不接收null！！！！！！会报NullPointException
        int xiaoming=hashmap2.remove("小明");
        System.out.println("第二次删除周杰伦返回的值: "+(hashmap2.remove("小明")));
        System.out.println(hashmap);
        /*
          public V get(Object key): 根据指定的键，在Map集合中获取对应的值
         */
        String yy=hashmap.get("黄晓明");
        System.out.println(yy);
        /*
        boolean containKey(Object key): 判断集合中是否包含指定的键 包含返回true,不包含返回false
        boolean containValue(Object value): 判断集合中是否包含指定的值 包含返回true,不包含返回false
         */
        System.out.println("是否包含小萝卜: "+hashmap2.containsKey("小萝卜")+" 是否包含22: "+hashmap2.containsValue(22));
    }
}
