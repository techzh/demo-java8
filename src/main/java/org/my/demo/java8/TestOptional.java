package org.my.demo.java8;

import org.junit.Test;

import java.util.Optional;

/**
 *
 */
public class TestOptional {
    /*
        Optional 容器类的常用方法
        Optional.of(T t)         -- 创建一个Optional类
        Optional.empty()         -- 创建一个空的Optional实例
        Optional.ofNullable(T t) -- 若 t 不为 null,创建 Optional 实例，否则创建空实例
        isPresent()              -- 判断是否包含值
        orElse(T t)              -- 如果调用对象包含值，返回该值，否则返回t
        orElseGet(Supplier s)    -- 如果调用对象包含值，返回该值，否则返回 s 获取的值
        map(Function f)          -- 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
        flatMap(Function mapper) -- 与map类似，要求返回值必须是Optional
     */

    @Test
    public void test1() {
        // Optional.of(T t) -- 创建一个Optional类 注意：参数不能是null,否则会空指针异常
        Optional<Employee> op = Optional.of(new Employee());
        Employee employee = op.get();
        System.out.println(employee);
        System.out.println("--------------------------------------");

        // Optional.empty() -- 创建一个空的Optional实例
        op= Optional.empty();
        // 空值时 get 会报无值错误
//        System.out.println(op.get());
        System.out.println("--------------------------------------");

        // Optional.ofNullable(T t) -- 若 t 不为 null,创建 Optional 实例，否则创建空实例
        op = Optional.ofNullable(null);
        // 空值时 get 会报无值错误
//        System.out.println(op.get());
        System.out.println("--------------------------------------");

        // isPresent() -- 判断是否包含值
        op= Optional.empty();
        if(op.isPresent()) {
            System.out.println("a:"+op.get());
        }
        op= Optional.of(new Employee());
        if(op.isPresent()) {
            System.out.println("b:"+op.get());
        }
        System.out.println("--------------------------------------");

        // orElse(T t) -- 如果调用对象包含值，返回该值，否则返回t
        op= Optional.empty();
        Employee employee1 = op.orElse(new Employee(1,"张三",30,6666.66));
        System.out.println(employee1);
        System.out.println("--------------------------------------");

        // orElseGet(Supplier s) -- 如果调用对象包含值，返回该值，否则返回 s 获取的值
        op= Optional.empty();
        Employee employee2 = op.orElseGet(() -> new Employee(2, "李四", 30, 8888.88));
        System.out.println(employee2);
        System.out.println("--------------------------------------");

        // map(Function f) -- 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
        op = Optional.ofNullable(new Employee(3));
        System.out.println(op.get());
        // 有值时返回对值的处理后的Optional
        Optional<Integer> op1 = op.map((e) -> e.getId());
        System.out.println(op1.get());

        op= Optional.empty();
        op1 = op.map((e) -> e.getId());
        // 无值时返回 Optional.empty()
//        System.out.println(op1.get());
        System.out.println("--------------------------------------");

        // flatMap(Function mapper) -- 与map类似，要求返回值必须是Optional
        op = Optional.ofNullable(new Employee(5));
        Optional<Integer> op2 = op.flatMap((e) -> Optional.of(e.getId()));
        System.out.println(op2.get());
    }
}
