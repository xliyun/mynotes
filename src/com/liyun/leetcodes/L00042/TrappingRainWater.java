package com.liyun.leetcodes.L00042;

import java.util.LinkedList;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-24 15:19
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] arr2 = {4, 2, 0, 3, 2, 5};
        int trap = trappingRainWater.trap(arr2);
        System.out.println(trap);
    }

    public int trap(int[] height) {
        LinkedList<Integer> stackIndex = new LinkedList<>();
        int sum = 0;

        for (int i = 0; i < height.length; i++) {
            /**
             * 0 1 0 2 1 0 1 3 2 1 2
             * 0 1 2 3 4 5 6 7 8 9
             */
            //循环知道栈剩下最后一个元素，或者有元素比当前元素高
            while (!stackIndex.isEmpty() && height[stackIndex.getFirst()] < height[i]) {
                int popHigh = height[stackIndex.removeFirst()];
                //栈空了，说明左面没有墙了
                if (stackIndex.isEmpty()) {
                    break;
                }

                int l = i - stackIndex.getFirst()-1;//左右两个墙之间的距离
                int minHigh = Math.min(height[stackIndex.getFirst()], height[i]);
                sum += l * (minHigh - popHigh);
            }

            stackIndex.addFirst(i);
        }
        return sum;
    }
}
