package com.liyun.leetcodes.L00047;

import java.util.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-27 21:05
 */
public class Permutations2 {
    public static void main(String[] args) {
        Permutations2 permutations = new Permutations2();
        int[] nums = {1,1,2};
        List<List<Integer>> permute = permutations.permuteUnique(nums);
        for (List<Integer> integers : permute) {
            for (Integer integer : integers) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int l = nums.length;
        if(l==1){
            List<Integer> list = new ArrayList<>();
            list.add(Integer.valueOf(nums[0]));
            result.add(list);
            return result;
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        int[] used = new int[l];
        Arrays.fill(used,0);
        Arrays.sort(nums);
        dfs(result,nums,linkedList,used,l);

        return result;
    }

    private void dfs(List<List<Integer>> result, int[] nums, LinkedList<Integer> linkedList,int[] used, int l) {
        if(linkedList.size()==l){
            result.add(new ArrayList<>(linkedList));
            return;
        }

        for(int k = 0; k<l;k++){
            if (used[k]==1) {
                continue;
            }
            //剪枝 如果当前坐标和前一个节点的值相等
            /**
             * 剪枝
             * 1.如果当前坐标和前一个节点的值相等,得剪枝
             * 2.used[k-1]!=1 表示前面一个和自己值一样下标的元素已经回溯完了
             * 如果是用used[k-1]==0 表示当前元素和前一个元素如果相等，并且前一个元素已经用了的话，就不再用当前元素，
             * 比如：某个分支下 1 1 1 只能用下标 2 1 0的顺序来排序，不如used[k-1]!=1好理解
             *
             */
            if(k>0 && nums[k]==nums[k-1] && used[k-1]!=1){
                continue;
            }
            //通过下标判断使用没使用
                linkedList.addLast(nums[k]);
                used[k]=1;
                dfs(result,nums,linkedList,used,l);
                used[k]=0;
                linkedList.removeLast();

        }
        return ;
    }


}
