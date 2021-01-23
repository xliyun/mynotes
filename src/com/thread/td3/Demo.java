package com.thread.td3;

import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        int COUNT_BITS = Integer.SIZE - 3;//29为偏移量
        int CAPACITY   = (1 << COUNT_BITS) - 1;//最大容量

        System.out.println(~CAPACITY);
        System.out.println(CAPACITY);

        Map<String,String> map=new HashMap<>();
        map.put("111","aaa");
        String value=map.get("2222");
        System.out.println(value.toString());
    }
}
