package org.my.demo.java8;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解与类型注解
 */
public class TestAnnotation {

    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void show() {
        @MyAnnotation("abc")
        String str = "abc";

    }

    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method m1 = clazz.getMethod("show");
        MyAnnotation[] mas = m1.getAnnotationsByType(MyAnnotation.class);

        for(MyAnnotation ma : mas) {
            System.out.println(ma.value());
        }
    }
}
