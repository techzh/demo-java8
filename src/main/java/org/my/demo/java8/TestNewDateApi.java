package org.my.demo.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestNewDateApi {

    // 1、LocalDate  LocalTime  LocalDateTime
    @Test
    public void test1() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2019,1,13,18,55,30);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.plusYears(1);
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt.minusMinutes(10);
        System.out.println(ldt4);

        System.out.println("------------------------------------");
        System.out.println(ldt.getYear() + "," + ldt.getMonthValue() + "," + ldt.getMonth() + "," + ldt.getDayOfMonth());
        System.out.println(ldt.getDayOfWeek() + "," + ldt.getDayOfYear());
        System.out.println(ldt.getHour() + "," + ldt.getMinute() +"," + ldt.getSecond() + "," + ldt.getNano());
    }

    // 2、Instant : 时间戳（以 Unix 元年：1970年1月1日00:00:00到某个时间之间的毫秒值
    @Test
    public void test2() {
        // 默认获取 UTC 时区 的时间
        Instant ins = Instant.now();
        System.out.println(ins);

        OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        System.out.println(ins.toEpochMilli());

        Instant ins1 = Instant.ofEpochSecond(1000);
        System.out.println(ins1);
    }

    // 3、
    // Duration : 计算两个 "时间" 之间的间隔
    // Period : 计算两个 "日期" 之间的间隔
    @Test
    public void test3() {
        Instant ins1 = Instant.now();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant ins2 = Instant.now();

        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration.toMillis());

        System.out.println("---------------------------");
        LocalTime lt1 = LocalTime.now();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime lt2 = LocalTime.now();

        System.out.println(Duration.between(lt1,lt2).toMillis());
    }

    // Period : 计算两个 "日期" 之间的间隔
    @Test
    public void test4() {
        LocalDate ld1 = LocalDate.of(2018,12,12);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1, ld2);
        System.out.println(period);
        System.out.println(period.getYears()+","+period.getMonths()+","+period.getDays());
    }

    // TemporalAdjuster ：时间校正器
    @Test
    public void test5() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        // 自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;
            DayOfWeek dow = ldt4.getDayOfWeek();
            if (dow.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dow.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

    // DateTimeFormatter : 格式化时间/日期
    @Test
    public void test6() {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;

        LocalDateTime ldt = LocalDateTime.now();
        String strDate = ldt.format(dtf);
        System.out.println(strDate);

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年 MM月 dd号 HH:mm:ss");
        strDate = dtf2.format(ldt);
        System.out.println(strDate);

        LocalDateTime ldt2 = LocalDateTime.parse(strDate, dtf2);
        System.out.println(ldt2);
    }

    // ZonedDate  ZonedTime ZonedDateTime
    @Test
    public void test7() {
        // 查看支持的时区
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        zoneIds.forEach(System.out::println);
    }

    @Test
    public void test8() {
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Monaco"));
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.now();
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("America/Shiprock"));
        System.out.println(zdt);
    }
}
