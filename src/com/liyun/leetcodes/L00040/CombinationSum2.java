package com.liyun.leetcodes.L00040;

import java.util.*;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2020-12-24 9:25
 */
public class CombinationSum2 {
    public static void main(String[] args) {
        CombinationSum2 combinationSum = new CombinationSum2();
        int[] candidates = {10,1,2,7,6,1,5};

        int target = 8;
        List<List<Integer>> lists = combinationSum.combinationSum(candidates, target);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates.length==0)
            return result;
        Map<Integer,Integer> map = new HashMap<>();

        LinkedList<Integer> arr = new LinkedList<>();
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int i = 0; i < candidates.length; i++){
            Integer integer = map.get(candidates[i]);
            if(integer==null){
                map.put(candidates[i],1);
            }else{
                map.put(candidates[i],++integer);
            }
            hashSet.add(candidates[i]);
        }
        Set<Integer> set = new TreeSet(hashSet);            // 利用TreeSet排序
        Integer[] integers = set.toArray(new Integer[]{});

        int[] candidates2 = new int[integers.length];            // 我们排序、去重后的结果数组
        for (int i = 0; i < integers.length; i++){
            candidates2[i] = integers[i].intValue();
        }


        getCombinationSum(result,candidates2,arr,map,target,0,0);
        return result;
    }

    private int getCombinationSum(List<List<Integer>> result,int[] candidates, LinkedList<Integer> arr,Map<Integer,Integer> map,int target,int sum,int start) {
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
            Integer count = map.get(candidates[i]);
            if(count>0 && count!=null){
                sum = sum+candidates[i];
                arr.addLast(candidates[i]);
                map.put(candidates[i],--count);
                if(getCombinationSum(result,candidates,arr,map,target,sum,i)==0){
                    sum = sum-candidates[i];
                    arr.removeLast();
                    map.put(candidates[i],++count);
                    break;
                }
                sum = sum-candidates[i];
                arr.removeLast();
                map.put(candidates[i],++count);
            }

        }

        return -1;
    }

}
