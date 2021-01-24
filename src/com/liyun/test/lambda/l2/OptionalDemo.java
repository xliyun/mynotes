package com.liyun.test.lambda.l2;

import java.util.Optional;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-09-27 16:51
 */
public class OptionalDemo {
    public static void main(String[] args) {

        Optional<String> name=Optional.of("Sanaulla");

        Optional empty=Optional.ofNullable(null);

        if(name.isPresent()){
            System.out.println(name.get());
        }

        try {
            System.out.println(empty.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        name.ifPresent((value)->{
            System.out.println("");
        });
    }
}
