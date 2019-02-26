package org.my.demo.java8;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 练习
 */
public class TestStareamApi4 {
    List<Employee> employees = Arrays.asList(
            new Employee(1,"张三",18,9999.99, Employee.Status.FREE),
            new Employee(2,"李四",58,5555.55, Employee.Status.BUSY),
            new Employee(3,"王五",26,3333.33, Employee.Status.VOCATION),
            new Employee(4,"赵六",36,6666.66, Employee.Status.FREE),
            new Employee(5,"田七",12,8888.88, Employee.Status.BUSY)
    );

    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    /*
        1、给定一个数字列表，如何返回一个由每个数的平方构成的列表？
        给定[1,2,3,4,5] 返回 [1,4,8,16,25]
     */
    @Test
    public void test1(){
        Integer[] nums = new Integer[]{1,2,3,4,5};
        Arrays.stream(nums).map((x) -> x*x).forEach(System.out::println);
    }

    /*
        怎样使用 map 和 reduce 方法数一数流中由多少个Employee?
     */
    @Test
    public void test2() {
        Optional<Integer> o = employees.stream().map((e) -> 1).reduce(Integer::sum);
        System.out.println(o.get());
        Long l = employees.stream().count();
        System.out.println(l);
    }

    /*
        1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
        2. 交易员都在哪些不同的城市工作过？
        3. 查找所有来自剑桥的交易员，并按姓名排序
        4. 返回所有交易员的姓名字符串，按字母顺序排序
        5. 有没有交易员是在米兰工作的？
        6. 打印生活在剑桥的交易员的所有交易额
        7. 所有交易中，最高的交易额是多少
        8. 找到交易额最小的交易
     */

    @Test
    public void test3() {
        // 1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
        transactions.stream().filter((t) -> t.getYear() == 2011).sorted((t1,t2) -> Integer.compare(t1.getValue(),t2.getValue())).forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        // 2. 交易员都在哪些不同的城市工作过？
        transactions.stream().map((t) -> t.getTrader().getCity()).forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        // 3. 查找所有来自剑桥的交易员，并按姓名排序
        transactions.stream().filter((t) -> "Cambridge".equals(t.getTrader().getCity())).sorted((t1,t2) -> t1.getTrader().getName().compareTo(t2.getTrader().getName())).forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        // 4. 返回所有交易员的姓名字符串，按字母顺序排序
        String names = transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.joining(","));
        System.out.println(names);
        System.out.println("-----------------------------------------------------");
        // 5. 有没有交易员是在米兰工作的？
        Optional<Transaction> o = transactions.stream().filter((t) -> "Milan".equals(t.getTrader().getCity())).findAny();
        System.out.println(o.get());
        System.out.println("-----------------------------------------------------");
        // 6. 打印生活在剑桥的交易员的所有交易额
        Double sum = transactions.stream().filter((t) -> "Cambridge".equals(t.getTrader().getCity())).collect(Collectors.summingDouble(Transaction::getValue));
        System.out.println(sum);
        System.out.println("-----------------------------------------------------");
        // 7. 所有交易中，最高的交易额是多少
        Optional<Transaction> o1 = transactions.stream().collect(Collectors.maxBy((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue())));
        System.out.println(o1.get());
        System.out.println("-----------------------------------------------------");
        // 8. 找到交易额最小的交易
        Optional<Transaction> o2 = transactions.stream().collect(Collectors.minBy((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue())));
        System.out.println(o2.get());
        System.out.println("-----------------------------------------------------");
    }

    /*
        将平铺的 List<Depart> 转为多级结构的 List<Depart>
     */
    @Test
    public void test4() {
        List<Depart> departs = Arrays.asList(
            new Depart(1,"外科",1, 0),
            new Depart(2,"骨外科",2, 1),
            new Depart(3,"骨髓外科",3, 2),
            new Depart(4,"骨质外科",2, 1),
            new Depart(5,"内科",1, 0),
            new Depart(6,"心内科",2, 5),
            new Depart(7,"肝内科",2, 5),
            new Depart(8,"心左内科",3, 6),
            new Depart(9,"心左瓣内科",4, 8)
        );
    }
}
