package com.liyun.jdk8.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class TestTransaction {
    List<Transaction> transactions= null;


    public void before(){
        Trader raoul = new Trader("Raoul","Carbridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Carbridge");
        Trader brian = new Trader("Brian","Carbridge");

        transactions = Arrays.asList(
                new Transaction(brian,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2012,950)
        );

    }

    //1.找出2011年发生的所有交易，并按交易额排序（从低到高）
    public void test1(){
        transactions.stream()
                .filter(t->t.getYear() == 2011)
                .sorted((t1,t2)->Integer.compare(t1.getValue(),t2.getValue()))
                .forEach(System.out::println);
    }
    //2.交易员都在哪些不同的城市工作过？
    public void test2(){
        transactions.stream()
                .map((t)->t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }
    //3.查找素有来自剑桥的交易员，并按姓名排序
    public void test3(){
        transactions.stream()
                .filter((t)->t.getTrader().getCity().equals("Carbridge"))
                .map((t)->t.getTrader())
                .distinct()
                .sorted((t1,t2)->t1.getName().compareTo(t2.getName()))
                .forEach(System.out::println);
    }
    //4.返回所有交易员的姓名字符串，按字母顺序排序
    public void test4(){
        transactions.stream()
                .map(t->t.getTrader().getName())
                .sorted()
                .forEach(System.out::println);
        System.out.println("---------------------");

        transactions.stream()
                .map(t->t.getTrader().getName())
                .sorted()
                .reduce("",String::concat);

        System.out.println("---------------------");
        //把字符串中的字符一个个提取出来，然后排序
        transactions.stream()
                .map(t->t.getTrader().getName())
                .flatMap(this::filterCharacter)
                .sorted((s1,s2)->s1.compareToIgnoreCase(s2))//忽略大小写
                .forEach(System.out::println);
    }

    public Stream<String> filterCharacter(String str){
        List<String> list = new ArrayList<>();

        for (Character c : str.toCharArray()) {
            list.add(c.toString());
        }

        return list.stream();
    }
    //5.有没有交易员是在米兰工作的
    public void test5(){
        boolean b1 = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(b1);
    }
    //6.打印生活在剑桥的交易员的所有交易额
    public void test6(){
        Integer sum = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Carbridge"))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }
    //7.所有交易中，最高的交易额是多少
    public void test7(){
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::max);
        System.out.println(max);
    }
    //8.找到交易额最小的交易
    public void test8(){
        Optional<Transaction> min = transactions.stream()
                .min((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));
        System.out.println(min.get());
    }

    //转换成hashmap 名字-金额
    public void test9(){
        Map<String, Integer> map = transactions.stream()
                .collect(Collectors.toMap((k) -> k.getTrader().getName(), v -> v.getValue()
                        , (k1, k2) -> k1));

    }
}
