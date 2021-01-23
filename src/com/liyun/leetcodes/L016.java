package com.liyun.leetcodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-11-14 9:09
 */
public class L016 {
    public static void main(String[] args) {
        L016 l016 = new L016();
        int[] nums={1,1,1,0};
        int[] nums2={0,2,1,-3};
        int[] nums3={-1,0,1,1,55};
        System.out.println(l016.threeSumClosest(nums3,3));
    }
    public int threeSumClosest(int[] nums, int target) {
       /* if(nums.length<3 || nums==null){
            return target;
        }*/

       Arrays.sort(nums);
       //用一开始的三数之和先赋值数差
       int  oldsum=nums[0]+nums[1]+nums[nums.length-1];
       //int ab=sum-target;
        for(int head=0;head<nums.length;head++){
            int mid=head+1,tail=nums.length-1;
            //如果
            while(mid<tail){
                int sum=nums[head]+nums[mid]+nums[tail];
                if(sum==target){
                    return sum;
                }
                if(sum>target){//三数之和比target大，那么数就要变小一点
                    //记录数差
                    if(Math.abs(sum-target)<Math.abs(oldsum-target)) {
                        oldsum=sum;
                    }
                    //你尾指针去重可不能带上人起点指针,尾指针向前移了，起点指针再后移起点数都变了
                    //while(mid<tail && nums[mid]==nums[mid+1]) mid++;
                    while(mid<tail && nums[tail]==nums[tail-1]) tail--;
                    tail--;
                }else if(sum<target){
                    if(Math.abs(sum-target)<Math.abs(oldsum-target)) {
                        oldsum=sum;
                    }
                    while(mid<tail && nums[mid]==nums[mid+1]) mid++;
                    //while(mid<tail && nums[tail]==nums[tail-1]) tail--;
                    mid++;
                }
            }
        }

        return oldsum;
    }

}
