package com.liyun.leetcodes;

import com.sun.deploy.util.StringUtils;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-13 15:30
 */
public class L005 {
    public String longestPalindrome(String s) {
        if(s.length()==0) return "";
        if(s.length()==1) return s;
        int[][] dp=new int[s.length()][s.length()];
        int len=0,left=0,right=0;

        for(int r=0;r<s.length();r++){
            for(int l=0;l<r;l++){
                    if(s.charAt(l)==s.charAt(r) &&(r-l<=2 || dp[l+1][r-1]==1)){
                    dp[l][r]=1;
                    if(r-l+1>len){
                        len=r-l+1;
                        left=l;
                        right=r;
                    }
                }

            }
        }

        return s.substring(left,right-left+1);
    }
}
