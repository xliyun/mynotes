package com.liyun.jdk8.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-12-02 19:43
 */
public class demo1 {
    public static void main(String[] args) {
        List<String> list= Arrays.asList(new String[]{"a","b","c"});

        List<Integer> integers=Arrays.asList(3,5,1,2);

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Collections.sort(list,(o1,o2)->o1.compareTo(o2));

        List<String> proNames=Arrays.asList(new String[]{"Ni","Hao","A"});

        List<String> lowercaseNames=new ArrayList<>();

        for (String proName : proNames) {
            lowercaseNames.add(proName);
        }

        List<String> lowercaseNames2=proNames.stream().map((name)->{return name.toLowerCase();}).collect(Collectors.toList());

        proNames.stream().map((name)->name.toLowerCase()).collect(Collectors.toList());

        List<String> collect = proNames.stream().map(name -> name.toLowerCase()).collect(Collectors.toList());

        proNames.forEach(System.out::println);

        List<String> collect1 = proNames.stream().map(String::toLowerCase).collect(Collectors.toList());


        Stream<List<Integer>> inputStream=Stream.of(
                Arrays.asList(1),
                Arrays.asList(2,3),
                Arrays.asList(4,5,6)
        );

        Stream<Integer> integerStream = inputStream.flatMap(child -> child.stream());

        integerStream.map(name->{
            System.out.println(name);
            return name;
        }).collect(Collectors.toList());


   /*     Arrays.asList(1,1,null,2,3,4,null,5,6,7,8,9,10)
                .*/

    }
}
