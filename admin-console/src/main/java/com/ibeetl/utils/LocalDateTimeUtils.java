package com.ibeetl.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * Description 日期时间工具类
 */
public class LocalDateTimeUtils {

    public static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATTER = "yyyy-MM-dd";
    public static final String TIME_FORMATTER = " HH:mm:ss";
    public static final String TIME_ZONE = "GMT+08:00";

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter
        .ofPattern(DATE_TIME_FORMATTER);
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMATTER);

    /**
     * 获取当前时间的LocalDateTime对象
     */
    //LocalDateTimeUtils.getCurrentDateTime();

    /**
     * 根据年月日构建LocalDateTime
     */
    //LocalDateTime.of();

    /**
     * 比较日期先后
     */
    //LocalDateTimeUtils.getCurrentDateTime().isBefore(),
    //LocalDateTimeUtils.getCurrentDateTime().isAfter(),


    /**
     * Date转换为LocalDateTime
     */
    public static LocalDateTime convertDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     */
    public static Date convertToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 日期时间字符串转换成日期时间
     */
    public static LocalDateTime parseDateTime(String str) {
        return LocalDateTime.parse(str, dateTimeFormatter);
    }

    /**
     * 日期字符串转换成日期
     */
    public static LocalDate parseDate(String str) {
        return LocalDate.parse(str, dateFormatter);
    }

    /**
     * 时间字符串转换成时间
     */
    public static LocalTime parseTime(String str) {
        return LocalTime.parse(str, timeFormatter);
    }


    /**
     * 获取指定日期的毫秒
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间的指定格式
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        if (time == null) {
            return null;
        }
        if (StringUtils.isNullOrEmpty(pattern)) {
            return time.format(dateTimeFormatter);
        }
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式
     */
    public static String formatNow(String pattern) {
        if (StringUtils.isNullOrEmpty(pattern)) {
            LocalDateTimeUtils.getCurrentDateTime().format(dateTimeFormatter);
        }
        return formatTime(LocalDateTimeUtils.getCurrentDateTime(), pattern);
    }

    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        if (time == null) {
            return null;
        }
        return time.plus(number, field);
    }

    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        if (time == null) {
            return null;
        }
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     *
     * @param field 单位(年月日时分秒)
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime,
        ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    /**
     * 获取一天的开始时间，2017,7,22 00:00
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
            .withMinute(0)
            .withSecond(0)
            .withNano(0);
    }

    /**
     * 获取一天的结束时间，2017,7,22 23:59:59.999999999
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
            .withMinute(59)
            .withSecond(59)
            .withNano(999999999);
    }

    /**
     * LocalDate转LocalDateTime，时分秒取最大值23：59：59
     */
    public static LocalDateTime transLocalDateToLocalDateTimeMax(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return LocalDateTime.of(localDate, LocalTime.of(23, 59, 59));
    }

    /**
     * LocalDate转LocalDateTime，时分秒取最小值00：00
     */
    public static LocalDateTime transLocalDateToLocalDateTimeMin(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }

/*
    private static int days = 0;
    private static LocalDate startDate = LocalDate.now();
    private static Timer timer = new Timer();
    static {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 10);

        int minute = cal.get(Calendar.MINUTE);
        cal.set(Calendar.MINUTE, minute - minute % 10);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        cal.add(Calendar.SECOND, -1);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                days ++;
                System.out.println("------------" + startDate.plusDays(days) + "--------------------------");
            }
        }, cal.getTime(), 600_000);
    }
*/

    public static LocalDate getCurrentDate() {
//        return startDate.plusDays(days);
//        return LocalDateTimeUtils.getCurrentDate();
        return LocalDate.now();
    }

    public static LocalDateTime getCurrentDateTime() {
//        return LocalDateTime.of(startDate.plusDays(days), LocalTime.now());
        return LocalDateTime.now();
    }

}
