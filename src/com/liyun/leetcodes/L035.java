package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-13 11:46
 */
public class L035 {
    public int searchInsert(int[] nums, int target) {
        if(nums==null || nums.length==0) return 0;
        int low=0,high=nums.length-1;
        if(nums[high]<target) return high+1;
       // System.out.println("high: "+high);
        while(low<high){
            int middle=(low+high)/2;
            //System.out.println("middle: "+middle);
            if(nums[middle]==target) return middle;
            else if(nums[middle]<target) low=middle+1;
            else high=middle;
            //System.out.println(low);
        }
        return low;
    }

    public static void main(String[] args) {
        L035 demo=new L035();
        int[] nums={1,3,5,6};
        System.out.println(demo.searchInsert(nums,2));
    }
}
