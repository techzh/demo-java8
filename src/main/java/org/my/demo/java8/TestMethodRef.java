package org.my.demo.java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 一、方法引用： 若Lambda体重的内容有方法已经实现了，我们可以使用方法引用
 *          （可以理解为 方法引用 是 Lambda 表达式的另外一种表现形式）
 *
 * 主要有三种语法形式：
 *      前提：需要实现的抽象方法的参数列表和返回值与Lambda中方法引用的方法的参数类别和返回值类型一致
 *
 * 1、对象 :: 实例方法名
 *
 * 2、类 :: 静态方法名
 *
 * 3、类 :: 实例方法名
 *      前提：如果Lambda的第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，可以使用 类 :: 实例方法名
 *
 *
 * 二、构造器引用
 *
 * 语法格式：
 *
 * ClassName :: new
 *      前提：构造器的参数列表和函数式接口抽象方法的参数列表一致
 *
 *
 * 三、数组引用
 *
 * Type :: new;
 */
public class TestMethodRef {


    // 对象 :: 实例方法名
    @Test
    public void tese1() {
        Consumer<String> c = (x) -> System.out.println(x);

        PrintStream ps  = System.out;
        Consumer<String> c1 = ps :: println;

        Consumer<String> c2 = System.out :: println;
        c2.accept("abc");
    }

    // 对象 :: 实例方法名
    @Test
    public void test2() {
        Employee emp = new Employee(1,"张三", 18, 2000d);
        Supplier<String> s = () -> emp.getName();
        System.out.println(s.get());

        Supplier<String> s1 = emp :: getName;
        System.out.println(s1.get());

        Supplier<Integer> s2 = emp :: getAge;
        System.out.println(s2.get());
    }

    // 类 :: 静态方法名
    @Test
    public void test3() {
        Comparator<Integer> c = (x,y) -> Integer.compare(x,y);

        Comparator<Integer> c1 = Integer::compare;
    }

    // 类 :: 实例方法名
    public void test4() {
        BiPredicate<String, String> bp = (x,y) -> x.equals(y);
        System.out.println(bp.test("abc", "sss"));

        BiPredicate<String, String> bp1 = String :: equals;
    }

    // 构造器引用  // 构造器的参数列表和函数式接口抽象方法的参数列表一致
    @Test
    public void test5() {
        Supplier<Employee> s = () -> new Employee();

        Supplier<Employee> s1 = Employee::new;

        Function<Integer, Employee> f = (x) -> new Employee(x);
        Employee e2 = f.apply(10);
        System.out.println(e2);

        Function<Integer, Employee> f1 = Employee::new;
        Employee e3 = f1.apply(12);
        System.out.println(e3);

        BiFunction<Integer,String,Employee> bf1 = Employee::new;
        bf1.apply(15,"张三");
    }

    // 数组引用
    @Test
    public void test6() {
        Function<Integer,String[]> f = (x) -> new String[x];


        Function<Integer,String[]> f1 = String[]::new;
        String[] s2 = f1.apply(5);
        System.out.println(s2.length);
    }
}
