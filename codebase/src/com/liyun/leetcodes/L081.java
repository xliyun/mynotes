package com.liyun.leetcodes;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-07-12 15:42
 */
public class L081 {
    public boolean search(int[] nums, int target) {
        if(nums.length==0) return false;
        if(nums.length==1) return nums[0]==target?true:false;
        int left=0,right=nums.length-1,result=-1;


        while(left<=right){
            int middle=(left+right)/2;
            if(nums[middle]==target) return true;
            else if(nums[middle]==nums[right])
                right=--right;
            else if(nums[middle]<nums[right]){//右侧是顺序的
                if(nums[middle]<target && nums[right]>=target){
                    left=middle+1;
                }else{
                    right=middle-1;
                }
            }else {//nums[middle]>nums[right] 左侧是顺序的
                if(nums[left]<=target && nums[middle]>target)
                    right=middle-1;
                else
                    left=middle+1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
       L081 demo=new L081();
        int arrays[]={1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,6};
        //System.out.println(demo.binay_search(arrays,7,0,6));
        int arrays2[]={1,6,1,1,1};
        int arrays3[]={1,1,1,6,1};
        System.out.println(demo.search(arrays2,6));
    }
}
