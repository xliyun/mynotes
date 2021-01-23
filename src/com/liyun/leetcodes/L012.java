package com.liyun.leetcodes;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-11-12 11:19
 */
public class L012 {
    public String intToRoman(int num) {
       int[] number={1000,900,500,400,100,90,50,40,10,9,5,4,1};
       String[] rom={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder result=new StringBuilder();
       while(num>0){
           for(int i=0;i<number.length;i++){
               if(num>=number[i]){
                   num-=number[i];
                   result.append(rom[i]);
                   break;
               }
           }
       }

        return result.toString();
    }

    public static void main(String[] args) {
        L012 demo=new L012();
        System.out.println(demo.intToRoman(1994));
    }
}
