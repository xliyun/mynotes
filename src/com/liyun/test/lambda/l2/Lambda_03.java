package com.liyun.test.lambda.l2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-25 13:41
 */
public class Lambda_03 {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        List<Integer> numsWithoutNull = nums.stream().filter(name -> name != null)
                .collect(() -> new ArrayList<Integer>(),
                        (list, item) -> list.add(item),
                        (list1, list2) -> list1.addAll(list2));

        //简化版Collectors汇聚
        List<Integer> numsWithoutNull2=nums.stream().filter(name->name!=null).collect(Collectors.toList());

        //reduce汇聚，三种方式，下面写两种常用的
        List<Integer> ints=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println("ints sum is: "+
                ints.stream().reduce((sum,item)->sum+item).get());

        //提供reduce循环的初始值
        System.out.println("ints sum is:" +
                ints.stream().reduce(0, (sum, item) -> sum + item));

        //count 统计数量
        System.out.println(ints.stream().count());

        //搜索相关
        System.out.println(ints.stream().allMatch(item->item<100));
        //如果不为空就输出
        /*
– allMatch：是不是Stream中的所有元素都满足给定的匹配条件
– anyMatch：Stream中是否存在任何一个元素满足匹配条件
– findFirst: 返回Stream中的第一个元素，如果Stream为空，返回空Optional
– noneMatch：是不是Stream中的所有元素都不满足给定的匹配条件
– max和min：使用给定的比较器（Operator），返回Stream中的最大|最小值
         */
        //如果有值就输出
        ints.stream().max((o1,o2)->o1.compareTo(o2)).ifPresent(System.out::println);
    }
}
