package com.liyun.leetcodes.L00041;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-24 14:01
 */
public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] arr = {1,2,0};
        int[] arr2 = {3,4,-1,1};
        int[] arr3 = {1,1};
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        int i = firstMissingPositive.firstMissingPositive(arr3);
        System.out.println(i);
    }
    public int firstMissingPositive(int[] nums) {
        if(nums.length==0)
            return 1;

        /**
         * 争取的格式是
         * index 0 1 2 3 4 5
         * value 1 2 3 4 5 6
         */
        for(int i=0;i<nums.length;i++){
            //如果nums[i]在数组范围之外，那么就置零
            if(nums[i]<=0 || nums[i]>nums.length){
                nums[i]=0;
            }else if(nums[i]!=i+1){
                //nums[i] 和 nums[nums[i]-1]交换
                int ii = nums[i]; //当前值
                int tmp = nums[ii-1]; //要交换的位置的值
                    //如果要交换的元素位置的值tmp和当前元素相等，也就是在它应该在的位置
                    if(tmp == ii){
                        nums[i] = 0;
                    }else{
                        nums[i] = tmp;
                        nums[ii-1] = ii;
                        i--;
                    }

            }
        }
        /**
         * 3 4 -1 1
         * i = 0
         * ii = nums[0] = 3
         * tmp = nums[3-1] = -1
         * nums[0] = -1
         * nums[3-1]= 3
         *
         * -1 4 3 1
         * 0 4 3 1
         * 0 1 3 4
         * ii = 1
         * tmp = 0
         *
         */

        for(int i=1;i<=nums.length;i++){
            if(nums[i-1]!=i){
                return i;
            }
        }
        return nums.length+1;
    }
}
