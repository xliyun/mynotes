package com.liyun.learn.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
    练习:
        计算一个字符串中每个字符出现的次数


 */
public class MapTest {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入一个字符串");
        String str=sc.nextLine();

        //2.创建Map集合,key是字符串中的字符,value是字符的个数
        HashMap<Character, Integer> hashMap=new HashMap<>();

        //3.遍历字符串，获取每一个字符
        for(char c:str.toCharArray()){
            //4.使用获取到的字符，去Map集合判断key是否存在
            if(hashMap.containsKey(c)){
                Integer value=hashMap.get(c);
                value++;
                hashMap.put(c,value);
            }else{
                hashMap.put(c,1);
            }
        }
        //遍历集合
        for(Map.Entry<Character,Integer> entry:hashMap.entrySet()){
            System.out.println("键-->"+entry.getKey()+" 值-->"+entry.getValue());
        }
    }
}
