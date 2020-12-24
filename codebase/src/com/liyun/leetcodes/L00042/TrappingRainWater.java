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
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] arr2 = {4,2,0,3,2,5};
        int trap = trappingRainWater.trap(arr2);
        System.out.println(trap);
    }
    public int trap(int[] height) {
        LinkedList<Integer> stack = new LinkedList<>();
        LinkedList<Integer> stackIndex = new LinkedList<>();
        int sum = 0;
        int lowhigh = 0;
        for(int i= 0;i<height.length;i++){
                //如果栈为空，直接压元素，压坐标
                if(stack.isEmpty()){
                    stack.addFirst(height[i]);
                    stackIndex.addFirst(i);
                //如果栈顶的元素比当前元素大，压栈
                }else if(stack.getFirst()>=height[i]){
                    stack.addFirst(height[i]);
                    stackIndex.addFirst(i);
                    lowhigh = 0;
                //如果栈顶元素比当前元素小，开始计算容量
                }else if(stack.getFirst()<height[i]){
                        while(!stack.isEmpty() && stack.getFirst()<=height[i]){
                            int l = (i-stackIndex.removeFirst()-1);
                            int h = (stack.getFirst()-lowhigh);
                            int capacity = l*h;
                            sum+=capacity;
                            lowhigh = stack.removeFirst();
                    }

                    stack.addFirst(height[i]);
                    stackIndex.addFirst(i);
                    lowhigh = height[i];

                }

        }

        return sum;
    }
}
