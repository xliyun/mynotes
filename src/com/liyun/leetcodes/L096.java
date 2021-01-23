package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-11-13 9:42
 */
//跟L095一样的题，只不过这次是返回个数，正好换动态规划来做
public class L096 {
    public int numTrees(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
         int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;


        //总个数就是每个数字作为根节点的搜索树数量的和
        for (int root = 2; root <= n; root++) {

            //root作为根节点的搜索树个数
            for (int i = 1; i <=root; i++) {
                int left = i - 1;
                int right = root - i;
                dp[root] += dp[left] * dp[right];

            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        L096 l096 = new L096();
        System.out.println(l096.numTrees(3));
    }
}
