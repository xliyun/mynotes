package com.liyun.leetcodes;

/**
    dp[i]表示到当前索引的最大子序列
 */
public class L053 {
    public int maxSubArray(int[] nums) {
        if(nums.length==0) return 0;

        int[] dp=new int[nums.length+1];
        dp[0]=nums[0];
        int max=nums[0];
        for(int i=1;i<nums.length;i++){
            dp[i]=Math.max(dp[i-1]+nums[i],nums[i]);
            if(dp[i]>max) max=dp[i];
            for (int i1 : dp) {
                System.out.print(i1+" ");
            }
            System.out.println();
        }

        return max;
    }

    public static void main(String[] args) {
        L053 demo=new L053();
        int[] array={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(demo.maxSubArray(array));
    }
}
