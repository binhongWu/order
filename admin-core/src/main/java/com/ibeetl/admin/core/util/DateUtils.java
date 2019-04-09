package com.ibeetl.admin.core.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils{

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前时间
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 获取当前时间
     */
    public static Long nowLong() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前年份
     */
    public static Integer nowYear() {
        return LocalDateTime.now().getYear();
    }

    /**
     * 获取当前月份
     */
    public static Integer nowMonth() {
        return LocalDateTime.now().getMonthValue();
    }

    /**
     * 获取当前号数
     */
    public static Integer nowDay() {
        return LocalDateTime.now().getDayOfMonth();
    }

    /**
     * 获取当前时间
     * 格式: 自定义
     */
    public static String nowFormat(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 获取当前时间
     * 格式: yyyy-MM-dd HH:mm:ss
     */
    public static String nowFormat() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    /**
     * 格式化时间
     *
     * @param date 时间
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化时间
     *
     * @param date   时间
     * @param format 格式
     */
    public static String format(Date date, String format) {
        LocalDateTime localDateTime = DateLocalDateTime.dateToLocalDateTime(date);
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 字符串转Data
     *
     * @param date   字符串
     * @param format 格式
     */
    public static Date parse(String date, String format) {
        return DateLocalDateTime.localDateTimeToDate(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format)));
    }

    /**
     * 获取指定时间 年月日 00 时 00 分
     *
     * @param year  年
     * @param month 月
     * @param day   日
     */
    public static Date getSpecifiedTime(int year, int month, int day) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(0, 0));
        return DateLocalDateTime.localDateTimeToDate(localDateTime);
    }


    /**
     * 获取指定时间 年月日时 00 分
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @param hour  时
     */
    public static Date getSpecifiedTime(int year, int month, int day, int hour) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, 0));
        return DateLocalDateTime.localDateTimeToDate(localDateTime);
    }

    /**
     * 获取指定时间 年月日时分
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @param hour  时
     * @param min   分
     */
    public static Date getSpecifiedTime(int year, int month, int day, int hour, int min) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, min));
        return DateLocalDateTime.localDateTimeToDate(localDateTime);
    }

    /**
     * 获取指定时间 年月日时分
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param min    分
     * @param second 秒
     */
    public static Date getSpecifiedTime(int year, int month, int day, int hour, int min, int second) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, min, second));
        return DateLocalDateTime.localDateTimeToDate(localDateTime);
    }



    /**
     * 获取当天 0 时 0 分 0 秒
     */
    public static Date nowDayWithZero() {
        return DateLocalDateTime.localDateTimeToDate(nowDayZero());
    }

    /**
     * 获取指定天 0 时 0 分 0 秒
     */
    public static Date dayWithZero(Date date) {
        return DateLocalDateTime.localDateTimeToDate(dayZero(DateLocalDateTime.dateToLocalDateTime(date)));
    }

    /**
     * 获取指定天 0 时 0 分 0 秒
     */
    public static long dayWithZero(long date) {
        return DateLocalDateTime.localDateTimeToDate(dayZero(DateLocalDateTime.dateToLocalDateTime(new Date(date)))).getTime();
    }

    /**
     * 获取本月第一天
     */
    public static Date getThisMonthFirstDay() {
        return DateLocalDateTime.localDateTimeToDate(nowDayZero().with(TemporalAdjusters.firstDayOfMonth()));
    }

    /**
     * 获取本月最后一天
     */
    public static Date getThisMonthLastDay() {
        return DateLocalDateTime.localDateTimeToDate(nowDayZero().with(TemporalAdjusters.lastDayOfMonth()));
    }


    /**
     * 获取下个月第一天
     */
    public static Date getNextMonthFirstDay() {
        return DateLocalDateTime.localDateTimeToDate(nowDayZero().with(TemporalAdjusters.lastDayOfMonth()).plusDays(1L));
    }


    /**
     * 时间向后推移天数
     *
     * @param plusCount 推移天数
     */
    public static Date plusDays(Date date, long plusCount) {
        return DateLocalDateTime.localDateTimeToDate(DateLocalDateTime.dateToLocalDateTime(date).plusDays(plusCount));
    }


    /**
     * 时间向前推移天数
     *
     * @param date        时间
     * @param reduceCount 推移天数
     */
    public static Date reduceDays(Date date, long reduceCount) {
        return DateLocalDateTime.localDateTimeToDate(DateLocalDateTime.dateToLocalDateTime(date).plusDays(-reduceCount));
    }


    /**
     * 时间向后推移小时数
     *
     * @param plusCount 推移小时数
     */
    public static Date plusHours(Date date, long plusCount) {
        return DateLocalDateTime.localDateTimeToDate(DateLocalDateTime.dateToLocalDateTime(date).plusHours(plusCount));
    }


    /**
     * 时间向前推移小时数
     *
     * @param date        时间
     * @param reduceCount 推移小时数
     */
    public static Date reduceHours(Date date, long reduceCount) {
        return DateLocalDateTime.localDateTimeToDate(DateLocalDateTime.dateToLocalDateTime(date).plusHours(-reduceCount));
    }


    /**
     * 时间向后推移分钟数
     *
     * @param plusCount 推移分钟数
     */
    public static Date plusMinutes(Date date, long plusCount) {
        return DateLocalDateTime.localDateTimeToDate(DateLocalDateTime.dateToLocalDateTime(date).plusMinutes(plusCount));
    }


    /**
     * 时间向前推移分钟数
     *
     * @param date        时间
     * @param reduceCount 推移分钟数
     */
    public static Date reduceMinutes(Date date, long reduceCount) {
        return DateLocalDateTime.localDateTimeToDate(DateLocalDateTime.dateToLocalDateTime(date).plusMinutes(-reduceCount));
    }


    /**
     * 时间向后推移秒数
     *
     * @param plusCount 推移秒数
     */
    public static Date plusSeconds(Date date, long plusCount) {
        return DateLocalDateTime.localDateTimeToDate(DateLocalDateTime.dateToLocalDateTime(date).plusSeconds(plusCount));
    }


    /**
     * 时间向前推移秒数
     *
     * @param date        时间
     * @param reduceCount 推移秒数
     */
    public static Date reduceSeconds(Date date, long reduceCount) {
        return DateLocalDateTime.localDateTimeToDate(DateLocalDateTime.dateToLocalDateTime(date).plusSeconds(-reduceCount));
    }

    /**
     * 时间向后推移
     *
     * @param date    时间
     * @param days    天
     * @param hours   小时
     * @param minutes 分钟
     * @param seconds 秒
     */
    public static Date plus(Date date, long days, long hours, long minutes, long seconds) {
        return DateLocalDateTime.localDateTimeToDate(DateLocalDateTime.dateToLocalDateTime(date)
                .plusDays(days)
                .plusHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds));
    }


    /**
     * 时间向前推移
     *
     * @param date    时间
     * @param days    天
     * @param hours   小时
     * @param minutes 分钟
     * @param seconds 秒
     */
    public static Date reduce(Date date, long days, long hours, long minutes, long seconds) {
        return DateLocalDateTime.localDateTimeToDate(DateLocalDateTime.dateToLocalDateTime(date)
                .plusDays(-days)
                .plusHours(-hours)
                .plusMinutes(-minutes)
                .plusSeconds(-seconds));
    }


    /**
     * 获取当天 0 时 0 分 0 秒
     */
    private static LocalDateTime nowDayZero() {
        return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 指定天 0 时 0 分 0 秒
     */
    private static LocalDateTime dayZero(LocalDateTime localDateTime) {
        return localDateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 与当前时间比较 大于 返回 true 否则 false
     *
     * @param date 传入时间
     */
    public static boolean greaterThanNow(long date) {
        return greaterThan(date, System.currentTimeMillis());
    }

    /**
     * 时间比较前者大于后着返回 true 否则 false
     *
     * @param firstDate  时间1
     * @param secondDate 时间2
     */
    public static boolean greaterThan(Date firstDate, Date secondDate) {
        return greaterThan(firstDate.getTime(), secondDate.getTime());
    }


    /**
     * 时间比较前者 大于 后着返回 true 否则 false
     *
     * @param firstDate  时间1
     * @param secondDate 时间2
     */
    public static boolean greaterThan(long firstDate, long secondDate) {
        return firstDate > secondDate;
    }


    /**
     * 时间比较前者 大于等于 后着返回 true 否则 false
     *
     * @param firstDate  时间1
     * @param secondDate 时间2
     */
    public static boolean greaterThanOrEqual(Date firstDate, Date secondDate) {
        return greaterThanOrEqual(firstDate.getTime(), secondDate.getTime());
    }


    /**
     * 时间比较前者 大于等于 后着返回 true 否则 false
     *
     * @param firstDate  时间1
     * @param secondDate 时间2
     */
    public static boolean greaterThanOrEqual(long firstDate, long secondDate) {
        return firstDate >= secondDate;
    }


    /**
     * 判断时间是否相等
     *
     * @param firstDate  时间1
     * @param secondDate 时间2
     */
    public static boolean isEqual(Date firstDate, Date secondDate) {
        return isEqual(firstDate.getTime(), secondDate.getTime());
    }


    /**
     * 判断时间是否相等
     *
     * @param firstDate  时间1
     * @param secondDate 时间2
     */
    public static boolean isEqual(long firstDate, long secondDate) {
        return firstDate == secondDate;
    }


    /**
     * 返回时间更迟的
     *
     * @param firstDate  时间1
     * @param secondDate 时间2
     */
    public static Date findGreaterThan(Date firstDate, Date secondDate) {
        return greaterThanOrEqual(firstDate, secondDate) ? firstDate : secondDate;
    }

    /**
     * 返回时间更迟的
     *
     * @param firstDate  时间1
     * @param secondDate 时间2
     */
    public static long findGreaterThan(long firstDate, long secondDate) {
        return greaterThanOrEqual(firstDate, secondDate) ? firstDate : secondDate;
    }


    /**
     * 返回时间更早的
     *
     * @param firstDate  时间1
     * @param secondDate 时间2
     */
    public static Date findSmall(Date firstDate, Date secondDate) {
        return greaterThanOrEqual(firstDate, secondDate) ? secondDate : firstDate;
    }

    /**
     * 返回时间更早的
     *
     * @param firstDate  时间1
     * @param secondDate 时间2
     */
    public static long findSmall(long firstDate, long secondDate) {
        return !greaterThanOrEqual(firstDate, secondDate) ? secondDate : firstDate;
    }


    /**
     * 返回时间差值天数
     */
    public static long differenceDay(Date var1, Date var2) {
        return Math.abs(difference(var1, var2).toDays());
    }

    /**
     * 返回时间差值天数
     */
    public static long differenceDay(long var1, long var2) {
        return Math.abs(difference(var1, var2).toDays());
    }


    /**
     * 返回时间差值小时数
     */
    public static long differenceHours(Date var1, Date var2) {
        return Math.abs(difference(var1, var2).toHours());
    }


    /**
     * 返回时间差值小时数
     */
    public static long differenceHours(long var1, long var2) {
        return Math.abs(difference(var1, var2).toHours());
    }


    /**
     * 返回时间差值分钟数
     */
    public static long differenceMinutes(Date var1, Date var2) {
        return Math.abs(difference(var1, var2).toMinutes());
    }


    /**
     * 返回时间差值分钟数
     */
    public static long differenceMinutes(long var1, long var2) {
        return Math.abs(difference(var1, var2).toMinutes());
    }

    /**
     * 返回时间差值秒数
     */
    public static long differenceMillis(Date var1, Date var2) {
        return Math.abs(difference(var1, var2).getSeconds());
    }


    /**
     * 返回时间差值秒数
     */
    public static long differenceMillis(long var1, long var2) {
        return Math.abs(difference(var1, var2).getSeconds());
    }


    /**
     * 时间差值
     *
     * @return Duration
     */
    private static Duration difference(Date var1, Date var2) {
        return Duration.between(DateLocalDateTime.dateToLocalDateTime(var1), DateLocalDateTime.dateToLocalDateTime(var2));
    }

    /**
     * 时间差值
     *
     * @return Duration
     */
    private static Duration difference(long var1, long var2) {
        return Duration.between(DateLocalDateTime.dateToLocalDateTime(new Date(var1)), DateLocalDateTime.dateToLocalDateTime(new Date(var2)));
    }

    /**
     * 获取某天的 00:00:00  或者 23:59:59
     * wbh   2018-09-03
     * @param pattern
     * @param date
     * @return
     */
    public static Date getZeroOrMidNight(Date date,String pattern){
        return DateUtils.parseDate(DateFormatUtils.format(date, pattern));
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null){
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 根据传入的日期、天数，推算日期
     * @param date
     * @param days
     * @return
     */
    public static Date getComputeDate(Date date,int days){
        Calendar calendar=Calendar.getInstance();
        // 传入的日期
        calendar.setTime(date);
        // 传入的日期加上天数days后生成的日期
        calendar.add(Calendar.DATE,+days);
        return calendar.getTime();
    }



    static class DateLocalDateTime {

        /**
         * LocalDateTime 转 Date
         */
        static Date localDateTimeToDate(LocalDateTime localDateTime) {
            ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
            return Date.from(zdt.toInstant());
        }


        /**
         * Date 转 LocalDateTime
         */
        static LocalDateTime dateToLocalDateTime(Date date) {
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        }
    }
}
