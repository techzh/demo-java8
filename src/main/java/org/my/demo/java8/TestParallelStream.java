package org.my.demo.java8;

import org.junit.Test;

import java.util.stream.LongStream;

/**
 * 并行流
 */
public class TestParallelStream {


    @Test
    public void test1() {
        long sum = LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0, Long::sum);
        System.out.println(sum);
    }

    @Test
    public void test2() {

    }
}
