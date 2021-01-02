package com.dayu.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Java 8的日期类型
 *
 * @author hjh
 * @date 2021/1/1 21:03
 */
public class NowDate {


    /**
     * LocalDate、LocalTime、LocalDateTime
     *
     * @author hjh
     * @date 2021/1/1
     */
    @Test
    public void testDate() {

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);
    }

    /**
     * 日期 --> String
     * @author hjh
     * @date 2021/1/1
     */
    @Test
    public void dateToString() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        String localDateString = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String localTimeString = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println(localDateString);
        System.out.println(localTimeString);
        System.out.println(localDateTimeString);
    }

    /**
     * String --> LocalDate LocalTime LocalDateTime
     * @author hjh
     * @date 2021/1/1
     */
    @Test
    public void stringToDate() {

        String dateString = "2020-01-01";
        String timeString = "20:00:00";
        String dateTimeString = "2020-01-01 20:00:00";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 正常情况
        LocalDate localDate = LocalDate.parse(dateString, dateFormatter);
        LocalTime localTime = LocalTime.parse(timeString, timeFormatter);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        //异常情况 java.time.format.DateTimeParseException: Text '2020-01-01' could not be parsed at index 10
        //注意 日期字符串精度不能小于Pattern的精度
        LocalDateTime exceptionDateParse = LocalDateTime.parse(dateString, dateTimeFormatter);
        System.out.println(exceptionDateParse);
    }

    @Test
    public void calculateDate() {

        LocalDateTime localDateTime = LocalDateTime.now();


        // Localxxxxx可以 plusxxx()各种方法，进行时间的加操作
        //Localxxxxx可以 minusxxx()各种方法，进行时间的减操作
        LocalDateTime localDateTimeAddDay = localDateTime.plusDays(1); //加一天
        LocalDateTime localDateTimeMinusWeek = localDateTime.minusWeeks(1); //减一周

        System.out.println(localDateTime);
        System.out.println(localDateTimeMinusWeek);
    }

}
