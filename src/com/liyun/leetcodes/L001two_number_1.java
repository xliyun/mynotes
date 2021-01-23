package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-04-30 9:51
 */
public class L001two_number_1 {
    public static void main(String[] args) {
        int[] arr={2,7,11,15};
        int target=9;
        int[] result=twoSum(arr,target);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] twoSum(int[] nums, int target) {


        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(target==(nums[i]+nums[j])){
                    int[] result={i,j};
                    return result;
                }
            }
        }
        int[] r={};
        return r ;
    }
}
