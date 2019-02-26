package org.my.demo.java8;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Java8 四大内置核心函数式接口
 *
 * Consumer<T> : 消费消费型接口
 *      void accept(T t);
 *
 * Supplier<T> : 供给型接口
 *      T get();
 *
 * Function<T, R> : 函数型接口
 *      R apply(T t);
 *
 * Predicate<T> : 断言型接口
 *      boolean test(T t);
 */
public class TestLambda {

    // Consumer<T> : 消费消费型接口
    @Test
    public void test1() {
        happy(100, (m) -> System.out.println("吃饭消费 "+m+" 元"));
    }

    private void happy(double money, Consumer<Double> c) {
        c.accept(money);
    }

    // Supplier<T> : 供给型接口
    @Test
    public void test2() {
        List<Integer> list = getNumList(5, () -> (int) (Math.random() * 100));
        for(Integer i : list) {
            System.out.println(i);
        }

    }

    private List<Integer> getNumList(int num, Supplier<Integer> s) {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<num;i++) {
            list.add(s.get());
        }
        return list;
    }

    // Function<T, R> : 函数型接口
    @Test
    public void test3() {
        String s = strHandler("\t\t\tabc  " , (str) -> str.trim());
        System.out.println(s);
    }

    private String strHandler(String str, Function<String, String> f) {
        return f.apply(str);
    }

    // Predicate<T> : 断言型接口
    @Test
    public void test4() {
        List<String> list = filterStr(Arrays.asList("hello", "java", "lambda"), (s) -> s.length() > 5);
        for(String s : list) {
            System.out.println(s);
        }
    }

    private List<String> filterStr(List<String> list, Predicate<String> p) {
        List<String> strList = new ArrayList<>();
        for(String s : list) {
            if(p.test(s)) {
                strList.add(s);
            }
        }
        return strList;
    }
}
