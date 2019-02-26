package org.my.demo.java8;

public interface MyInterface {

    // 接口中的默认方法
    default String getName() {
        return "hello java8";
    }

    // 接口中的静态方法
    static void show() {
        System.out.println("接口中的静态方法 show");
    }
}
