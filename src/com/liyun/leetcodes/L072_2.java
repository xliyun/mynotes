package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-20 20:45
 */
public class L072_2 {
    public int minDistance(String word1, String word2) {
        if(word1.length()==0 ) return word2.length();
        if(word2.length()==0) return word1.length();

        int m=word1.length(),n=word2.length();
        // System.out.println(m+" "+n);
        int[][] dp=new int[m+1][n+1];

        return helper(word1,0,word2,0,dp);
    }

    private int helper(String word1, int i, String word2, int j, int[][] dp) {
        for(int s=0;s<dp.length;s++){
            for(int ss=0;ss<dp[0].length;ss++){
                System.out.print(dp[s][ss]+" ");
            }
            System.out.println();
        }
        System.out.println("=========================");
        //递归边界
        if(i==word1.length()) return word2.length()-j;
        if(j==word2.length()) return word1.length()-i;
        if(dp[i][j]>0) return dp[i][j];
        int result=0;
        if(word1.charAt(i)==word2.charAt(j)){
            return helper(word1,i+1,word2,j+1,dp);
        }else {
            int inserCount=helper(word1,i,word2,j+1,dp);
            int deleteCount=helper(word1,i+1,word2,j,dp);
            int replaceCount=helper(word1,i+1,word2,j+1,dp);
            result=Math.min(inserCount,Math.min(deleteCount,replaceCount))+1;
        }
        return dp[i][j]=result;
    }

    public static void main(String[] args) {
        L072_2 demo=new L072_2();


        System.out.println(demo.minDistance("horse","ros"));
    }
}
