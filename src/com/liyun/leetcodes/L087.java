package com.liyun.leetcodes;

import java.util.HashMap;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-21 19:24
 */
public class L087 {
    public boolean isScramble(String s1, String s2) {
        if(s1.length()!=s2.length()) return false;
        HashMap<String,Integer> hashMap=new HashMap<>();
        return dp(s1,s2,hashMap);

    }

    private boolean dp(String s1, String s2, HashMap<String, Integer> hashMap) {
        //从记录表里获取结果
        int result=hashMap.getOrDefault(s1+"*"+s2,-1);
        if(result==1) return true;
        else if(result==0) return false;
        //1.递归边界成功时的情况，最边界是两个单字母一样
        if(s1.equals(s2)) {
            hashMap.put(s1+"*"+s2,1);
            return true;
        }

        //2.递归边界 失败时的情况

        //因为存在s1分割交换时不是对称分割交换的
        if(s1.length()!=s2.length()){
            hashMap.put(s1+"*"+s2,0);
            return false;
        }

        int[] arrays=new int[26];
        for(int a=0;a<s1.length();a++){
            arrays[s1.charAt(a)-'a']++;
            arrays[s2.charAt(a)-'a']--;
        }

        for(int b=0;b<26;b++){
            if(arrays[b]!=0){
                hashMap.put(s1+"*"+s2,0);
                return false;
            }
        }

        //递归的情况 从i=1开始不然死循环
        for(int i=1;i<s1.length();i++){
            if(dp(s1.substring(0,i),s2.substring(0,i),hashMap) && dp(s1.substring(i),s2.substring(i),hashMap)){
                hashMap.put(s1+"*"+s2,1);
                return true;
            }
            if(dp(s1.substring(0,i),s2.substring(s2.length()-i),hashMap) && dp(s1.substring(i),s2.substring(0,s2.length()-i),hashMap)){
                hashMap.put(s1+"*"+s2,1);
                return true;
            }
        }

        hashMap.put(s1+"*"+s2,0);
        return false;
    }

    public static void main(String[] args) {
        L087 demo=new L087();
        System.out.println(demo.isScramble("great","rgeat"));
    }
}
