package com.zeropercenthappy.utilslibrary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ybq on 2017/4/24.
 */

public class DateUtils {
    private static final SimpleDateFormat DATE_FORMAT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    private static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("HH:mm:ss", Locale.US);

    /**
     * 默认格式化格式时间:yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatDataTime(long date) {
        synchronized (DATE_FORMAT_DATETIME) {
            return DATE_FORMAT_DATETIME.format(new Date(date));
        }
    }

    /**
     * 默认格式化格式时间:yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String formatDate(long date) {
        synchronized (DATE_FORMAT_DATE) {
            return DATE_FORMAT_DATE.format(new Date(date));
        }
    }

    /**
     * 默认格式化格式时间:HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatTime(long date) {
        synchronized (DATE_FORMAT_TIME) {
            return DATE_FORMAT_TIME.format(new Date(date));
        }
    }

    /**
     * 自定义格式化
     *
     * @param dateStr 字符串时间戳
     * @param format  自定义的格式
     * @return
     */
    public static String formatDateCustom(String dateStr, String format) {
        return new SimpleDateFormat(format, Locale.US).format(new Date(Long.parseLong(dateStr)));
    }

    /**
     * 自定义格式化
     *
     * @param date   时间Date
     * @param format 自定义的格式
     * @return
     */
    public static String formatDateCustom(Date date, String format) {
        return new SimpleDateFormat(format, Locale.US).format(date);
    }

    /**
     * 获取指定年月的Calendar
     *
     * @param year  年份
     * @param month 月份 (1-12)
     * @return
     */
    public static Calendar getCalendarByYearMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);    //Calendar月份为0-11，所以减1
        return calendar;
    }

    /**
     * 获得某个月最大天数
     *
     * @param year  年份
     * @param month 月份 (1-12)
     * @return 某个月最大天数
     */
    public static int getMaxDayByYearMonth(int year, int month) {
        Calendar calendar = getCalendarByYearMonth(year, month);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取某年某月第一天对应周几
     *
     * @param year  年份
     * @param month 月份 (1-12)
     * @return 中国时间的周几
     */
    public static int getFirstDayOfWeekByYearMonth(int year, int month) {
        Calendar calendar = getCalendarByYearMonth(year, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        //Calendar中周日为1，转换为中国时间要减1，为负数时是周日
        if (week < 0) {
            return 7;
        } else {
            return calendar.get(Calendar.DAY_OF_WEEK) - 1;
        }
    }

    /**
     * 获取某年某月某天对应周几
     *
     * @param year  年份
     * @param month 月份 (1-12)
     * @param day   日期
     * @return 中国时间的周几
     */
    public static int getDayOfWeekByYearMonthDay(int year, int month, int day) {
        Calendar calendar = getCalendarByYearMonth(year, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        //Calendar中周日为1，转换为中国时间要减1，为负数时是周日
        if (week < 0) {
            return 7;
        } else {
            return calendar.get(Calendar.DAY_OF_WEEK) - 1;
        }
    }


    /**
     * 获取当前月最大天数
     *
     * @return
     */
    public static int getCurrentMonthMaxDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int getCurrentYear() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy", Locale.US);
        String yearStr = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return Integer.parseInt(yearStr);
    }

    /**
     * 获取当前月
     *
     * @return 中国时间的当前月
     */
    public static int getCurrentMonth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM", Locale.US);
        String monthStr = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return Integer.parseInt(monthStr);
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static int getCurrentDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd", Locale.US);
        String dayStr = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return Integer.parseInt(dayStr);
    }

    /**
     * 获取当前时
     *
     * @return 当前时
     */
    public static int getCurrentHour() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH", Locale.US);
        String monthStr = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return Integer.parseInt(monthStr);
    }


    /**
     * 获取指定时间的Date
     *
     * @param dateFormated 格式化好的时间str
     * @param style        时间的格式
     * @return 指定时间的Date对象或null
     */
    public static Date string2Date(String dateFormated, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(style, Locale.US);
        Date date = null;
        if (dateFormated == null || dateFormated.length() < 6) {
            return null;
        }
        try {
            date = simpleDateFormat.parse(dateFormated);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式化数字
     *
     * @param content 数字
     * @return 若content为0-9，则补0至两位数；若content大于等于10，则原样返回
     */
    public static String formatNumber(int content) {
        if (content >= 0 && content < 10) {
            return "0" + content;
        } else if (content >= 10) {
            return String.valueOf(content);
        } else {
            return "";
        }
    }

    /**
     * 格式化数字
     *
     * @param content 数字
     * @return 若content为0-9，则补0至两位数；若content大于等于10，则原样返回
     */
    public static String formatNumber(String content) {
        int contentInt = Integer.parseInt(content);
        if (contentInt >= 0 && contentInt < 10) {
            return "0" + content;
        } else if (contentInt >= 10) {
            return String.valueOf(content);
        } else {
            return "";
        }
    }
}
