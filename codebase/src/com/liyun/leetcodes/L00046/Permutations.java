package com.liyun.leetcodes.L00046;

import java.util.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-27 21:05
 */
public class Permutations {
    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        int[] nums = {1,2,3};
        List<List<Integer>> permute = permutations.permute(nums);
        for (List<Integer> integers : permute) {
            for (Integer integer : integers) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int l = nums.length;
        if(l==1){
            List<Integer> list = new ArrayList<>();
            list.add(Integer.valueOf(nums[0]));
             result.add(list);
             return result;
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        Set<Integer> used = new HashSet<>();
        dfs(result,nums,linkedList,used,l);

        return result;
    }

    private void dfs(List<List<Integer>> result, int[] nums, LinkedList<Integer> linkedList,Set<Integer> used, int l) {
        if(linkedList.size()==l){
            result.add(new ArrayList<>(linkedList));
            return;
        }

        for(int k = 0; k<l;k++){
            //通过下标判断使用没使用
            if(!used.contains(nums[k])){
                linkedList.add(nums[k]);
                used.add(nums[k]);
                dfs(result,nums,linkedList,used,l);
                used.remove(nums[k]);
                linkedList.removeLast();
            }
        }
        return ;
    }


}
