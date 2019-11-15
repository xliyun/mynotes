package com.liyun.leetcodes;

import java.util.Arrays;

/**
 下面这种写法是上面解法的精简模式，这里我们可以建立一个256位大小的整型数组来代替HashMap，
 这样做的原因是ASCII表共能表示256个字符，但是由于键盘只能表示128个字符，所以用128也行，
 然后我们全部初始化为-1，这样的好处是我们就不用像之前的HashMap一样要查找当前字符是否存在映射对了，
 对于每一个遍历到的字符，我们直接用其在数组中的值来更新left，因为默认是-1，
 而left初始化也是-1，所以并不会产生错误，这样就省了if判断的步骤，其余思路都一样：
 */
public class L003Longest_Substring_1 {
    public static void main(String[] args) {
        //abcabcbb  cdd tmmzuxt  abba
        int len = lengthOfLongestSubstring("abba");
        System.out.println(len);
    }

    public static  int lengthOfLongestSubstring(String s) {
        int[] m=new int[256];
        Arrays.fill(m,-1);
        int left=0;
        int res=0;
        int max=0;
        for(int i=0;i<s.length();i++){
            if(m[s.charAt(i)]>-1){
                left=Math.max(m[s.charAt(i)]+1,left);
                m[s.charAt(i)]=i;
                max=Math.max(max,res);
                res=i-left+1;

            }else{
                m[s.charAt(i)]=i;
                res++;
            }

        }
        max=Math.max(max,res);
        return max;
    }
}
