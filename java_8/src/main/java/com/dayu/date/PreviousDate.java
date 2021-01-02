package com.dayu.date;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Jav 8之前的日期类型使用
 *
 * java.util.Date 年月日 时分秒
 * java.sql.Date 年月日
 * java.sql.Time 时分秒
 * java.sql.Timestamp 年月日 时分秒（毫微秒）继承自java.util.Date
 *
 * java.util.Date是java.sql.Date和java.sql.Time的父类
 * java.sql.Date的构造方法Date(long l)
 * java.util.Date的getTime()可以得到一个long类型的毫秒值。
 *
 * @author hjh
 * @date 2021/1/1 17:01
 */
public class PreviousDate {

    /**
     * 测试sql包下的几个日期类型
     *
     * 日期的核心是long类型的一个毫秒值
     *
     * 如：java.util.Date的构造方法
     * public Date() {
     *      this(System.currentTimeMillis());
     * }
     *
     * 而各日期类型的转换也是对这个毫秒值的处理
     *
     * @author hjh
     * @date 2021/1/1
     */
    @Test
    public void testPreviousDate() {

        //获取当前时间
        java.util.Date utilDate = new java.util.Date();

        //java.sql.Date没有无参构造函数,java.sql.Time同样如此
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        Timestamp timestamp = new Timestamp(utilDate.getTime());

        /*
            可以看出sql包下的Date类型，为util包Date的扩展。
            为SQL准备的日期类型。

            联系MySQL的日期类型
            Date、Time、DateTime，字面意思，处理的精度不一样，对应sql.Date、sql.Time、util.Date

            DateTime和Timestamp都可以精确到毫秒级
            使用DateTime类型用于不同时区（国内和国外同时使用）的时候会出现各种问题
            使用Timestamp则不会。

        */

        //sql包下的日期类型获取时间毫秒值
        long sqlDateToTimeMillis = sqlDate.getTime();
        long sqlTimeToTimeMillis = sqlTime.getTime();

        /*
            对于Timestamp获取毫秒值
            getTime() 和 toInstant().toEpochMilli()得到的毫秒值一样，
            前者直接在Timestamp对象中处理，后者需要转换为Instant对象。
         */
        long timestampToTimeMillis = timestamp.toInstant().toEpochMilli();
        long timestampGetTime = timestamp.getTime();

        System.out.println(sqlDateToTimeMillis);
        System.out.println(sqlTimeToTimeMillis);

        System.out.println(timestampToTimeMillis);
        System.out.println(timestampGetTime);

    }

    /**
     * 关于时间类型 --> String
     *
     * @author hjh
     * @date 2021/1/1
     */
    @Test
    public void dateToString() {
        long timeMillis = System.currentTimeMillis();

        java.sql.Date sqlDate = new java.sql.Date(timeMillis);
        java.sql.Time sqlTime = new java.sql.Time(timeMillis);
        Timestamp timestamp = new Timestamp(timeMillis);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sqlDateString = dateFormat.format(sqlDate);
        String sqlTimeString = dateFormat.format(sqlTime);
        String timestampString = dateFormat.format(timestamp);

        /*
            结果均为 2021-01-01 17:46:02
            因为从sqlDate、sqlTime、timestamp三个变量拿到的毫秒值一致。

            而且三者都继承自java.util.Date
         */
        System.out.println(sqlDateString);
        System.out.println(sqlTimeString);
        System.out.println(timestampString);

    }


    /**
     * 日期字符串  --> Date
     * 注意pattern的精度，避免出现ParseException
     *
     * @author hjh
     * @date 2021/1/1
     */
    @Test
    public void stringToDate() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateTimeString = "2021-01-01 18:00:00";
        Date dateTime = dateFormat.parse(dateTimeString);
        System.out.println(dateTime);

        /*
            String dateString = "2021-01-01";
            dateFormat的pattern：yyyy-MM-dd HH:mm:ss
            报错 java.text.ParseException: Unparseable date: "2021-01-01"
        */

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = "2021-01-01 18:00:00";
        Date date = format.parse(dateString);

        /*
            Fri Jan 01 00:00:00 CST 2021
            根据pattern决定 Date的精度
            所以当pattern精度更高时，时间字符串精度不足，则会报错。
         */
        System.out.println(date);
    }

    /**
     * 日期计算
     *
     * @author hjh
     * @date 2021/1/1
     */
    @Test
    public void calculateDate() throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        Date before = dateFormat.parse("2021-01-01 18:00:00");
        Date after = dateFormat.parse("2021-01-01 20:00:00");

        //Date类 支持before和after日期的判断
        System.out.println(before.before(after));
        System.out.println(after.after(before));

        /*
            Date加一天或减一天
            需要依赖于Calendar日历类型
            Calendar依赖于时区TimeZone.getDefaultRef()
        */
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();

        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1); //加一天  负数为减一天
        calendar.add(Calendar.MONTH, -1); //加一月 负数为减一月
        date = calendar.getTime();

        System.out.println(date);
    }

}
