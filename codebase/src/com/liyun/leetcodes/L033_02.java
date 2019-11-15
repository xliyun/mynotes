package com.liyun.leetcodes;

/**
    注意为什么nums[middle]要和右面比较
 注意这里有个坑：如果待查询的范围最后只剩两个数，那么mid 一定会指向下标靠前的数字
 比如 array = [4,6]
 array[low] = 4 ;array[mid] = 4 ; array[high] = 6 ;
 如果high = mid - 1，就会一直取左面那个
 */
public class L033_02 {
    public int search(int[] nums, int target) {
        if(nums.length==0) return -1;
        if(nums.length==1) return nums[0]==target?0:-1;
        int left=0,right=nums.length-1,result=-1;


        while(left<=right){
            int middle=(left+right)/2;
            if(nums[middle]==target) return middle;
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
        return -1;
    }
}
