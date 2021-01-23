package com.liyun.leetcodes.L00042;

import java.util.LinkedList;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-24 15:19
 */
public class TrappingRainWater2 {
    public static void main(String[] args) {
        TrappingRainWater2 trappingRainWater = new TrappingRainWater2();
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] arr2 = {4,2,0,3,2,5};
        int trap = trappingRainWater.trap(arr2);
        System.out.println(trap);
    }
    public int trap(int[] height) {
        int sum = 0;
        //最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            //找出左边最高
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }
            int max_right = 0;
            //找出右边最高
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }
            //找出两端较小的
            int min = Math.min(max_left, max_right);
            //只有较小的一段大于当前列的高度才会有水，其他情况不会有水
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

}
