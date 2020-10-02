package com.liyun.utils;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @version: 1.0
 * @author: 刘亮
 * @date: 2018/2/5. 15:49
 */
public final class DateUtil {

//    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//    private static DateTimeFormatter dateTimeFormatterDeatil = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//
//    //    String(yyyy-MM-dd)转Date
//    public static Date stringToDateV1(String StringDate){
//        if(StringUtils.isEmpty(StringDate)){
//            throw new RuntimeException("SYS_STRING_IS_NULL");
//        }
//        String rex = "\\d{4}-\\d{2}-\\d{2}";
//        if(!StringDate.matches(rex)){
//            throw new RuntimeException("SYS_DATE_FORMAT_NOT_TRUE");
//        }
//        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        try {
//            date = simpleDateFormat.parse(StringDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }
//
//    /**
//     * 字符串(yyyy-MM-dd)转ZonedDateTime
//     *
//     * @param StringDate 字符串日期
//     * @return ZonedDateTime
//     */
//    public static ZonedDateTime stringToZonedDateTime(String StringDate) {
//        if (StringUtils.isEmpty(StringDate)) {
//            return null;
////            throw new BizException(RespCode.SYS_STRING_IS_NULL);
//        }
//        String rex = "\\d{4}-\\d{2}-\\d{2}";
//        if (!StringDate.matches(rex)) {
//            throw new BizException("SYS_DATE_FORMAT_NOT_TRUE");
//        }
//        ZonedDateTime zonedDateTime = null;
//        try {
//            zonedDateTime = LocalDate.parse(StringDate, dateTimeFormatter).atStartOfDay(ZoneId.systemDefault());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return zonedDateTime;
//    }
//    //    Date转ZonedDateTime
//    public static ZonedDateTime dateToZoneDateTime(Date date){
//        return date==null?null:ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
//    }
//    // ZonedDateTime 转String
//    public static String ZonedDateTimeToString(ZonedDateTime dateTime) {
//        return zonedDateTimeToString(dateTime, dateTimeFormatter);
//    }
//
//    public static String zonedDateTimeToString(ZonedDateTime dateTime) {
//        return zonedDateTimeToString(dateTime, dateTimeFormatter);
//    }
//
//    public static String zonedDateTimeToHHMMSS(ZonedDateTime dateTime) {
//        return zonedDateTimeToString(dateTime, dateTimeFormatterDeatil);
//    }
//
//    public static String zonedDateTimeToString(ZonedDateTime dateTime, DateTimeFormatter formatter) {
//        if (dateTime == null) {
//            return null;
//        }
//        String str = null;
//        try {
//            str = dateTime.format(formatter);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return str;
//        }
//        return str;
//    }
//
//    public static Date stringToDate(String stringDate){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        try {
//            date = sdf.parse(stringDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }
//    /**
//     * string转ZonedDateTime +1天，主要用于日期至
//     * @param date yyyy-mm-dd
//     * @return zonedDateTime
//     */
//    public static ZonedDateTime string2ZonedDateTimeAddOne(String date){
//        String rex = "\\d{4}-\\d{2}-\\d{2}";
//        if (!date.matches(rex)) {
//            throw new BizException("请传入 yyyy-MM-dd 格式");
//        }
//        ZonedDateTime dateTo=null;
//        Calendar c = Calendar.getInstance();
//        c.setTime(DateUtil.stringToDate(date));
//        c.add(Calendar.DAY_OF_MONTH, 1);
//        Date time = c.getTime();
//        dateTo = DateUtil.dateToZoneDateTime(time);
//        return dateTo;
//    }
//
//
//    /**
//     * 今天的开始时间
//     *
//     * @return
//     */
//    public static ZonedDateTime getCurrentDayStartDate() {
//
//        return LocalDate.now().atStartOfDay(ZoneId.systemDefault());
//    }
//
//    /**
//     * 今天的开始时间UTC时间
//     *
//     * @return
//     */
//    public static ZonedDateTime getStartDateByUTC(ZonedDateTime zonedDateTime) {
//        if (null == zonedDateTime) {
//            return null;
//        }
//        return zonedDateTime.toLocalDate().atStartOfDay(ZoneId.of("UTC"));
//    }
//
//    /**
//     * 获取当前日期的开始时间，默认为系统时区
//     *
//     * @return
//     */
//    public static ZonedDateTime truncDate(ZonedDateTime zonedDateTime) {
//        if (null == zonedDateTime) {
//            return null;
//        }
//        return zonedDateTime.truncatedTo(ChronoUnit.DAYS);
//    }
//
//    /**
//     * 今天的开始时间
//     *
//     * @return
//     */
//    public static ZonedDateTime getDayEndDate(String date) {
//
//        return LocalDateTime.of(LocalDate.parse(date, dateTimeFormatter), LocalTime.MAX).atZone(ZoneId.systemDefault())
//                ;
//    }

}
