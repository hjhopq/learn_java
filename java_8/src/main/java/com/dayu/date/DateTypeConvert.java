package com.dayu.date;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Date;

/**
 * 各种日期类型的相互转换
 *
 * 由于
 * java.sql.Date
 * java.sql.Time
 * java.sql.Timestamp
 * 都继承于java.util.Date
 *
 * 而日期的转换  主线 java.util.Date <-> LocalDateTime
 *
 * @author hjh
 * @date 2021/1/1 23:59
 */
public class DateTypeConvert {

    /**
     * java.util.Date -> Localxxx
     *
     * @author hjh
     * @date 2021/1/2
     */
    @Test
    public void dateTo() {

        Date date = new Date();

        /*
            java.util.Date -> Instant -> ZonedDateTime
            ZonedDateTIme -> LocalDate LocalTime LocalDateTIme
        */
        ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
        LocalDate localDate = zonedDateTime.toLocalDate();
        LocalTime localTime = zonedDateTime.toLocalTime();
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

    }


    /**
     * Localxxx -> java.util.Date
     *
     * @author hjh
     * @date 2021/1/2
     */
    @Test
    public void toDate() {

        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        /*
            Localxxx -> ZonedDateTime
            再经过Date.from(Instant);
        */

        ZonedDateTime localDateZoned = localDate.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime localDateTimeZoned = localDateTime.atZone(ZoneId.systemDefault());

        Date date = Date.from(localDateZoned.toInstant());
        Date date_ = Date.from(localDateTimeZoned.toInstant());

        System.out.println(date);
        System.out.println(date_);
    }
}
