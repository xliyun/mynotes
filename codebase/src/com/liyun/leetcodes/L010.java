package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-13 19:28
 */
public class L010 {
    public boolean isMatch(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();

        int m=s.length(),n=p.length();
        int[][] dp=new int[m+1][n+1];
        dp[0][0]=1;
        for(int i=0;i<=m;i++){

            for(int j=1;j<=n;j++){
             if(j>1 && p.charAt(j-1)=='*'){
                 //一个都不匹配或者匹配多个
                 if(
                         dp[i][j-2]==1 ||  //一个都不匹配 i等于零的时候被匹配串为空，只能走一个都不匹配
                         (i>0 && (s.charAt(i-1)==p.charAt(j-2) || p.charAt(j-2)=='.') && dp[i-1][j]==1)//dp[i-1][j]就是*匹配多个字符的意思
                 ){
                     dp[i][j]=1;
                 }
                 //j等于1只能走当前字符串相等的匹配
             }else {
                  if(i>0 && dp[i-1][j-1]==1 &&(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='.')){
                      dp[i][j]=1;
                  }
             }


            }
        }


        return dp[m][n]==1?true:false;
    }
}
