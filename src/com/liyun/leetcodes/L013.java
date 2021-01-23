package com.liyun.leetcodes;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-11-12 11:35
 */
public class L013 {
    public int romanToInt(String s) {
        Map<String,Integer> map=new HashMap<>();
        //"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"
        //{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        map.put("M",1000);
        map.put("CM",900);
        map.put("D",500);
        map.put("CD",400);
        map.put("C",100);
        map.put("XC",90);
        map.put("L",50);
        map.put("XL",40);
        map.put("X",10);
        map.put("IX",9);
        map.put("V",5);
        map.put("IV",4);
        map.put("I",1);

        int sum=0;
        Integer value=0;
        int i;
        //如果字符串长度大于2
        for(i=0;i<s.length()-2;){
            //先获取两个字母拼成的罗马数字
            if((value=map.get(s.substring(i,i+2)))!=null){
                sum+=value;
                i+=2;
                //一个字母的罗马数字
            }else {
                sum+=map.get(s.substring(i,i+1));
                i+=1;
            }
        }

        if(i==s.length()-2){
            if((value=map.get(s.substring(i,i+2)))!=null){
                sum+=value;
            }else{
                sum+=map.get(s.substring(i,i+1))+map.get(s.substring(i+1,i+2));
            }
        }else{
            sum+=map.get(s.substring(i,i+1));
        }

        return sum;
    }

    public static void main(String[] args) {
        L013 d = new L013();
       System.out.println(d.romanToInt("MCMXCIV"));
    }
}
