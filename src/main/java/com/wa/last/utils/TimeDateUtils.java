package com.wa.last.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class TimeDateUtils {

    public static final String SHANGHAI_ZONE = "Asia/Shanghai";

    public static final ZoneId DEFAULT_ZONE = ZoneId.of(SHANGHAI_ZONE);

    /**
     * 时间取星期(英文)
     *
     * @param date
     * @return str
     */
    public static String getWeekOfDate(Date date) {
        String[] week = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return week[intWeek];
    }

    /**
     * 时间取星期(中文)
     *
     * @param date
     * @return str
     */
    public static String getChineseWeekOfDate(Date date) {
        String[] week = {"星期天", "星期六", "星期五", "星期四", "星期三", "星期二", "星期一"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return week[intWeek];
    }

    /**
     * 字符串转换成日期(yyyy-MM-dd)
     *
     * @param str
     * @return date
     */
    public static Date strToDate(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String sdf = "yyyy-MM-dd";
        switch (str.length()) {
            case 8:
                sdf = "yyyyMMdd";
                break;
            case 10:
                sdf = "yyyy-MM-dd";
                break;
            case 13:
                return new Date(Long.valueOf(str));
            case 16:
                sdf = "yyyy-MM-dd HH:mm";
                break;
            case 19:
                sdf = "yyyy-MM-dd HH:mm:ss";
                break;
        }
        return strToDate(str, sdf);
    }

    /**
     * 字符串转换成日期
     *
     * @param str 日期字符串
     * @param sdf 日期格式（如："yyyy-MM-dd"）
     * @return date
     */
    public static Date strToDate(String str, String sdf) {
        SimpleDateFormat format = new SimpleDateFormat(sdf);
        Date date = null;
        try {
            if (StringUtils.isEmpty(str)) {
                return date;
            }
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转字符串(yyyy-MM-dd) 当天
     */
    public static String dateToStr(String sdf) {
        SimpleDateFormat format = new SimpleDateFormat(sdf);
        String string = null;
        try {
            string = format.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 日期转字符串(yyyy-MM-dd) 当天
     */
    public static String dateToStr() {
        return dateToStr(new Date(), "yyyy-MM-dd");
    }

    /**
     * 日期转字符串(yyyy-MM-dd)
     */
    public static String dateToStr2(Date date) {
        String string = dateToStr(date, "yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isNotBlank(string)) {
            return string.endsWith("00:00:00") ? string.substring(0, 10) : string;
        }
        return string;
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param sdf  所需字符串（如："yyyy-MM-dd"）
     * @return date
     */
    public static String dateToStr(Date date, String sdf) {
        SimpleDateFormat format = new SimpleDateFormat(sdf);
        String string = StringUtils.EMPTY;
        try {
            string = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }
    /**
     * 将日期字符串转换为新的日期字符串
     *
     * @param old    旧日期字符串
     * @param oldsdf 旧日期格式
     * @param newsdf 新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String old, String oldsdf, String newsdf) {
        return dateToStr(strToDate(old, oldsdf), newsdf);
    }

    /**
     * 字符串日期加减天数
     *
     * @param old    旧日期字符串
     * @param oldsdf 旧日期格式
     * @param day    加减天数
     * @return 新日期字符串（旧日期格式返回）
     */
    public static String StringToString(String old, String oldsdf, int day) {
        SimpleDateFormat format = new SimpleDateFormat(oldsdf);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(old, oldsdf));
        calendar.add(Calendar.DAY_OF_YEAR, day);
        String string = null;
        try {
            string = format.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 日期加减时间
     *
     * @param old   旧日期
     * @param type  加减类型(年，月，日)
     * @param value 加减值
     * @return 新日期
     */
    public static Date datePlusDate(Date old, String type, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(old);
        if ("年".equals(type)) {
            calendar.add(Calendar.YEAR, value);
        }
        if ("月".equals(type)) {
            calendar.add(Calendar.MONTH, value);
        }
        if ("日".equals(type)) {
            calendar.add(Calendar.DATE, value);
        }
        return calendar.getTime();
    }

    /**
     * 日期差
     *
     * @param minuend 被减数
     * @param minus   减数
     * @return 日期差（天数）
     */
    public static Integer dayBetween(Date minuend, Date minus) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(minuend);
        long _minuend = cal.getTimeInMillis();
        cal.setTime(minus);
        long _minus = cal.getTimeInMillis();
        return Integer.valueOf(String.valueOf((_minuend - _minus) / (1000 * 3600 * 24)));
    }

    /**
     * 获取当月第一天
     */
    public static Date getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当月最后一天
     */
    public static Date getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static boolean isValidDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            format.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static Date getCurrentDay() {
        return Date.from(LocalDate.now().atStartOfDay().atZone(DEFAULT_ZONE).toInstant());
    }

    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(DEFAULT_ZONE).toInstant());
    }

    /**
     * 根据生日获取年龄
     *
     * @param birthday
     * @return
     */
    public static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
//                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
//                    age += 1;
//                }
            }
            return age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
            return 0;
        }
    }


    /**
     * 日期距离现在几个月
     *
     * @return
     * @throws ParseException
     */
    public static int getMonth(Date start) {
        Date end = Calendar.getInstance().getTime();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }

    /**
     * 获取今天0点
     *
     * @return
     */
    public static Date getNow() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        return instance.getTime();
    }
    /**
     * 获取当前月份
     *
     * @return
     */
    public static String getThisMonth() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
        return formatters.format(today);
    }
    /**
     * 获取上个月份
     *
     * @return
     */
    public static String getLastMonth() {
        LocalDate today = LocalDate.now();
        today = today.minusMonths(1);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
        return formatters.format(today);
    }
    /**
     * 获取昨天0点
     *
     * @return
     */
    public static Date getYesterday() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.DAY_OF_MONTH, -1);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        return instance.getTime();
    }

    /**
     * 获取明天0点
     *
     * @return
     */
    public static Date getTomorrow() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.DAY_OF_MONTH, 1);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        return instance.getTime();
    }

    /**
     * 当前时间加减
     *
     * @return
     */
    public static String getAfter(Integer field, Integer value) {
        Calendar now = Calendar.getInstance();
        now.add(field, value);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(now.getTimeInMillis());
    }

//    public static void main(String[] args) {
//        Date yesterday = TimeDateUtils.getYesterday();
//        System.out.println(yesterday);
//    }

    /**
     * 生成时间戳
     *
     * @return
     */
    public static String timestamp() {
        long currentTimeMillis = System.currentTimeMillis();
        //转换成秒
        long second = currentTimeMillis / 1000L;
        //截取前10位返回
        return String.valueOf(second).substring(0, 10);
    }

    /**
     * 当前时间
     *
     * @return
     */
    public static String getServerTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return df.format(new Date());
    }
}
