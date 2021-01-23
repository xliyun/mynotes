package com.liyun.leetcodes;

import java.util.Arrays;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-04-30 19:12
 */
public class L003Longest_Substring_2 {
    public static void main(String[] args) {
        //abcabcbb  cdd tmmzuxt  abba
        int len = lengthOfLongestSubstring("abba");
        System.out.println(len);
    }

    public static  int lengthOfLongestSubstring(String s) {
        int[] m = new int[256];
        Arrays.fill(m, -1);
        int res = 0, left = -1;
        //重点在这个++i处理的很好
        for (int i = 0; i < s.length(); ++i) {
            left = Math.max(left, m[s.charAt(i)]);
            m[s.charAt(i)] = i;
            res = Math.max(res, i - left);
            System.out.println(m[s.charAt(i)]);
            System.out.println(s.charAt(i));
        }
        return res;
    }
}
