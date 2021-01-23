package com.liyun.leetcodes;

/**
    这题重点在low<high，当我们数组里剩下的都是相同的target时，
 */
public class L034 {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length==0) return new int[]{-1,-1};

        int low=0,high=nums.length-1;
        int left=-1,right=-1;
        //求左边界
        while(low<high){
            int middle=(low+high)/2;
            if(nums[middle]>target) high=middle-1;
            else if(nums[middle]<target) low=middle+1;
            else high=middle;
        }
        left=low;
        if(nums[left]!=target)
        {left=-1;right=-1;}
        else {
            //求右边界
            high = nums.length - 1;
            while (low < high) {
                int middle = (low + high) / 2;
                if (nums[middle] > target) high = middle - 1;
                else if (nums[middle] < target) low = middle + 1;
                else if (nums[middle] == target) {
                    if (high - low == 1) {
                        if (nums[high] == target)
                            low++;
                        else high--;
                    } else
                        low = middle;
                }
            }
            right=high;
        }
        return new int[]{left,right};
    }

    public static void main(String[] args) {
        int[] nums={1,5,5,5,5,5,5,5};
        L034 demo=new L034();
        int[] result=demo.searchRange(nums,5);
        System.out.println(result[0] + "---"+result[1]);

    }
}
