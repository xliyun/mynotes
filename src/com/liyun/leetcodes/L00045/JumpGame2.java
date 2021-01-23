package com.liyun.leetcodes.L00045;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-27 15:53
 */
public class JumpGame2 {
    public static void main(String[] args) {
        JumpGame2 jumpGame2 = new JumpGame2();

        int[] nums={2,3,1,1,4};
        int jump = jumpGame2.jump(nums);
        System.out.println(jump);
    }
    public int jump(int[] nums) {
        /**
         *动态规划+贪心
         *
         * 参考 wzc1995
         *
         * 我们会发现f[i]是具有单调性的，也就是f[i + 1] >= f[i]。用反证法：假设f[i + 1] < f[i]，不妨设是从k,(k <= i)点跳到i + 1，即：k + nums[k] >= i + 1，那么k + nums[k]也必然大于i，此时：f[i + 1] = f[i]了。如果nums数组每一项都为1，则：f[i + 1] > f[i]，综上：f[i + 1] >= f[i]，与假设矛盾。
         *
         * 因此f[i]就变成了0 1...1 2...2 3...3 ......，在动态规划时瓶颈就在于更新每个点的最小值时需要遍历所有能跳到i的点，而有了单调性以后就可以用第一个能跳到i的点更新了，这里无论是取哪一个点跳到i，其最终的结果是一样的，但是取第一个点和取最后一个点所需要的步数可能不相同，所以尽量选择靠前的点，这样步数就可能会减少，贪心的思想。
         *
         */
        int l = nums.length;
        if(l==1)
            return 0;

        int[] dp = new int[l];
        int[] stepIndex = new int[l];
        dp[0]=0;

        for (int i = 0, last = 0; i < l; i++) {
            if (i == 0) dp[i] = 0;
            else {
                while (last < l && last + nums[last] < i) last++;
                dp[i] = dp[last] + 1; // 使用第一个能到i的点更新f[i]
            }
        }
        return dp[l - 1];
    }


}
