package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-19 10:02
 */
public class L070 {
    public int climbStairs(int n) {
        if(n==0) return 0;
        if(n==1) return 1;

        int[] dp=new int[n];
        dp[0]=1;dp[1]=2;
        for(int i=2;i<n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n-1];
    }
}
