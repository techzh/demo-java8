package org.my.demo.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 一、Stream 的三个操作步骤
 *
 * 1、创建 Stream
 *
 * 2、中间操作
 *
 * 3、终止操作（终端操作）
 */
public class TestStreamApi3 {
    List<Employee> employees = Arrays.asList(
            new Employee(1,"张三",18,9999.99, Employee.Status.FREE),
            new Employee(2,"李四",58,5555.55, Employee.Status.BUSY),
            new Employee(3,"王五",26,3333.33, Employee.Status.VOCATION),
            new Employee(4,"赵六",36,6666.66, Employee.Status.FREE),
            new Employee(5,"田七",12,8888.88, Employee.Status.BUSY)
    );

    // 终止操作

    /*
        查找与匹配
        allMatch  -- 检查是否匹配所有元素
        anyMatch  -- 检查是否至少匹配一个元素
        noneMatch -- 检查是否没有匹配所有元素
        findFirst -- 返回第一个元素
        findAny   -- 返回当前流中的任意元素
        count     -- 返回当前流中元素的总个数
        max       -- 返回流中最大值
        min       -- 返回流中最小值
     */

    /*
        规约
        reduce(T identity, BinaryOperator) / reduce(BinaryOperator) -- 可以将流中元素反复结合起来，得到一个值
     */

    /*
        收集
        collect -- 将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */

    /**
     * 查找与匹配
     */
    @Test
    public void test1() {
        // allMatch  -- 检查是否匹配所有元素
        boolean b = employees.stream().allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println("allMatch:"+b);

        // anyMatch  -- 检查是否至少匹配一个元素
        b = employees.stream().anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println("anyMatch:"+b);

        // noneMatch -- 检查是否没有匹配所有元素
        b = employees.stream().noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println("noneMatch:"+b);

        // findFirst -- 返回第一个元素
        Optional<Employee> o = employees.stream().sorted((e1, e2) -> Double.compare(e1.getInCome(), e2.getInCome())).findFirst();
        System.out.println("findFirst:"+o.get());

        // findAny   -- 返回当前流中的任意元素
        o = employees.stream().filter((e) -> e.getStatus().equals(Employee.Status.FREE)).findAny();
        System.out.println("findAny:"+o.get());

        // count     -- 返回当前流中元素的总个数
        long count = employees.stream().count();
        System.out.println("count:"+count);

        // max       -- 返回流中最大值
        o = employees.stream().max((e1,e2) -> Double.compare(e1.getInCome(), e2.getInCome()));
        System.out.println("max:"+o.get());

        // min       -- 返回流中最小值
        Optional<Double> o1 = employees.stream().map(Employee::getInCome).min(Double::compare);
        System.out.println("min:"+o1.get());
    }

    /** reduce(T identity, BinaryOperator) / reduce(BinaryOperator) -- 可以将流中元素反复结合起来，得到一个值 */
    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        sum = list.stream().reduce(1, (x, y) -> x + y);
        System.out.println(sum);

        Optional<Double> o = employees.stream().map(Employee::getInCome).reduce((Double::sum));
        System.out.println(o.get());
    }

    /** collect -- 将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法 */
    @Test
    public void test3() {
        // 收集名字到 list
        List<String> l = employees.stream().map(Employee::getName).collect(Collectors.toList());
        System.out.println(l);
        // 收集名字到 set
        Set<String> s = employees.stream().map(Employee::getName).collect(Collectors.toSet());
        System.out.println(s);
        // 收集名字到 HashSet
        HashSet<String> hashSet = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
        // List<String> 转 List<Long>
        List<String> ids = Arrays.asList("123","234");
        List<Long> idList = ids.stream().map((str) -> Long.valueOf(str)).collect(Collectors.toCollection(ArrayList::new));
        List<Long> idList2 = ids.stream().map(Long::valueOf).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(ids+","+idList);

        System.out.println("--------------------------------------------");
        // 总数
        Long count = employees.stream().collect(Collectors.counting());
        System.out.println(count);

        // 平均值
        Double avg = employees.stream().collect(Collectors.averagingDouble(Employee::getInCome));
        System.out.println(avg);

        // 总数
        Double sum = employees.stream().collect(Collectors.summingDouble(Employee::getInCome));
        System.out.println(sum);

        // 最大值
        Optional<Employee> o = employees.stream().collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getInCome(), e2.getInCome())));
        System.out.println(o.get().getInCome());

        // 最大值
        Optional<Double> minO = employees.stream().map(Employee::getInCome).collect(Collectors.minBy((d1, d2) -> Double.compare(d1, d2)));
        System.out.println(minO.get());

        // 分组
        Map<Employee.Status, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);

        // 多级分组 - 可以无限分下去
        Map<Employee.Status, Map<String, List<Employee>>> map1 = employees.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
            if (e.getAge() <= 35) {
                return "青年";
            } else if (e.getAge() <= 50) {
                return "中年";
            } else {
                return "老年";
            }
        })));
        System.out.println(map1);

        // 分区 - 也可以多级分区，类似多级分组
        Map<Boolean, List<Employee>> map2 = employees.stream().collect(Collectors.partitioningBy((e) -> e.getInCome() > 8000));
        System.out.println(map2);

        // 另一种收集方式
        DoubleSummaryStatistics dss = employees.stream().collect(Collectors.summarizingDouble(Employee::getInCome));
        System.out.println(dss.getSum());
        System.out.println(dss.getAverage());
        System.out.println(dss.getMax());

        // 连接  连接的数据类型是什么类型返回就是什么类型
        String s1 = employees.stream().map(Employee::getName).collect(Collectors.joining());
        System.out.println(s1);
        s1 = employees.stream().map(Employee::getName).collect(Collectors.joining(","));
        System.out.println(s1);
        s1 = employees.stream().map(Employee::getName).collect(Collectors.joining(",","start:",":end"));
        System.out.println(s1);

    }
}
