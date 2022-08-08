package com.henucg.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    /**
     * SimpleDateFormat 和 DateTimeFormat 格式化日期的注意点
     * YYYY: 根据每周最后一天计算当前时间属于那一年，例如 2020-12-27号，当天是周日，属于该周第一天，周六是最后一天，所以解析后的日期是 2021-12-27
     * yyyy：当年
     * MM：月
     * mm：分钟
     * DD：每年第几天
     * dd：当月第几天
     * HH：24时进制格式
     * hh：12时进制格式
     * ss：秒
     * S：毫秒
     */

    private static String DATE_FORMAT_YYYYMMMDD = "yyyyMMdd";

    private static String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    private static String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    private static String DATE_FORMAT_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    private static DateTimeFormatter DTF_YYYYMMDD = DateTimeFormatter.ofPattern(DATE_FORMAT_YYYYMMMDD);

    private static DateTimeFormatter DTF_YYYY_MM_DD = DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM_DD);

    private static DateTimeFormatter DTF_YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern(DATE_FORMAT_YYYYMMDDHHMMSS);

    private static DateTimeFormatter DTF_YYYY_MM_DD_HHMMSS = DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM_DD_HHMMSS);

    /**
     * 格式化当天 yyyyMMdd
     * @return
     */
    public static int getCurrentDayInt(){
        return Integer.valueOf(LocalDate.now().format(DTF_YYYYMMDD));
    }

    /**
     * 格式化当天 yyyy-MM-dd
     * @return
     */
    public static String getCurrentDayStr(){
        return LocalDate.now().format(DTF_YYYY_MM_DD);
    }

    /**
     * 格式化当天 yyyyMMddHHmmss
     * @return
     */
    public static long getCurrentTimeLong(){
        return Long.valueOf(LocalDateTime.now().format(DTF_YYYYMMDDHHMMSS));
    }

    /**
     * 格式化当天 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTimeStr(){
        return LocalDateTime.now().format(DTF_YYYY_MM_DD_HHMMSS);
    }

    /**
     * 是否是当天时间
     * @param timestamp: 时间戳（精确到毫秒，默认时区为机器当前时区)
     * @return
     */
    public static boolean isCurrentDay(long timestamp){
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),ZoneId.systemDefault());
        return (localDateTime.isEqual(startTime)
                || localDateTime.isAfter(startTime))
                && localDateTime.isBefore(endTime);
    }

    /**
     * 两个日期时间差 date2 - date1
     * @param date1 yyyyMMdd
     * @param date2 yyyyMMdd
     * @return
     *  Period
     *  .getYears()
     *  .getMonths()
     *  .getDays()
     */
    public static Period dateBetween(int date1,int date2) {
        LocalDate ld1 = LocalDate.parse(String.valueOf(date1),DTF_YYYYMMDD);
        LocalDate ld2 = LocalDate.parse(String.valueOf(date2),DTF_YYYYMMDD);
        return Period.between(ld1, ld2);
    }

    /**
     * 两个日期时间差 date2 - date1
     * @param date1 yyyyMMdd
     * @param date2 yyyyMMdd
     * @return
     *  Period
     *  .getYears()
     *  .getMonths()
     *  .getDays()
     */
    public static Period dateUntil(int date1,int date2) {
        LocalDate ld1 = LocalDate.parse(String.valueOf(date1),DTF_YYYYMMDD);
        LocalDate ld2 = LocalDate.parse(String.valueOf(date2),DTF_YYYYMMDD);
        return ld2.until(ld1);
    }

    /**
     * 两个日期时间差 date2 - date1
     * @param date1 yyyy-MM-dd
     * @param date2 yyyy-MM-dd
     * @return
     *  Period
     *  .getYears()
     *  .getMonths()
     *  .getDays()
     */
    public static Period dateBetween(String date1,String date2) {
        LocalDate ld1 = LocalDate.parse(date1,DTF_YYYY_MM_DD);
        LocalDate ld2 = LocalDate.parse(date2,DTF_YYYY_MM_DD);
        return Period.between(ld1, ld2);
    }

    /**
     * 两个日期时间差 date2 - date1
     * @param date1 yyyy-MM-dd
     * @param date2 yyyy-MM-dd
     * @return
     *  Period
     *  .getYears()
     *  .getMonths()
     *  .getDays()
     */
    public static Period dateUntil(String date1,String date2) {
        LocalDate ld1 = LocalDate.parse(date1,DTF_YYYY_MM_DD);
        LocalDate ld2 = LocalDate.parse(date2,DTF_YYYY_MM_DD);
        return ld2.until(ld1);
    }

    /**
     * 两个日期时间差 date2 - date1 (默认时区为机器当前时区)
     * @param date1
     * @param date2
     * @return
     *  Duration
     *  .toDays()
     *  .toHours()
     *  .toMinutes()
     *  .getSeconds()
     *  .toMillis()
     */
    public static Duration dateTimeBetween(Date date1,Date date2) {
        LocalDateTime ldt1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(date1.getTime()),ZoneId.systemDefault());
        LocalDateTime ldt2 = LocalDateTime.ofInstant(Instant.ofEpochMilli(date2.getTime()),ZoneId.systemDefault());
        return Duration.between(ldt1, ldt2);
    }

    /**
     * 两个日期时间差 date2 - date1 (默认时区为机器当前时区)
     * @param date1
     * @param date2
     * @return
     *  Duration
     *  .toDays()
     *  .toHours()
     *  .toMinutes()
     *  .getSeconds()
     *  .toMillis()
     */
    public static Duration duration(long date1,long date2) {
        LocalDateTime ldt1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(date1),ZoneId.systemDefault());
        LocalDateTime ldt2 = LocalDateTime.ofInstant(Instant.ofEpochMilli(date2),ZoneId.systemDefault());
        return Duration.between(ldt1, ldt2);
    }
}
