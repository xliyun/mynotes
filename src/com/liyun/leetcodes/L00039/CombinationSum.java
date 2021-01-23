package com.liyun.leetcodes.L00039;

import org.junit.platform.commons.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-24 9:25
 */
public class CombinationSum {
    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> lists = combinationSum.combinationSum(candidates, target);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates.length==0)
            return result;

//        List<Integer> arr = new ArrayList<>();
        LinkedList<Integer> arr = new LinkedList<>();
        Arrays.sort(candidates);
        getCombinationSum(result,candidates,arr,target,0,0);
        return result;
    }

    private int getCombinationSum(List<List<Integer>> result,int[] candidates, LinkedList<Integer> arr, int target,int sum,int start) {
        if(sum == target){
            List<Integer> r = new ArrayList<>(arr);
            result.add(r);
            return 0;
        }else if(sum>target){
            return 1;
        }

        //start是为了防止结果重复。
        //数组排了序，后面只填充比自己大的
        //如果有结果了，或者填充后比目标值大，都没必要再循环去找了
        for(int i = start;i<candidates.length;i++){
            sum = sum+candidates[i];
            arr.addLast(candidates[i]);
            if(getCombinationSum(result,candidates,arr,target,sum,i)>=0){
                sum = sum-candidates[i];
                arr.removeLast();
               break;
            }
            sum = sum-candidates[i];
            arr.removeLast();
        }

        return -1;
    }

}
