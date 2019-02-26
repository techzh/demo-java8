package org.my.demo.java8;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestDateFormat {
    public static void main(String[] args) throws Exception{

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("2016-12-18",dtf);
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> results = new ArrayList<>();

        for(int i=0; i<10; i++) {
            results.add(pool.submit(task));
        }

        for(Future<LocalDate> future : results) {
            System.out.println(future.get());
        }
    }
}
