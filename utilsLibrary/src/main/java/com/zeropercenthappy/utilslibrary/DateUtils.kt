package com.zeropercenthappy.utilslibrary

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ybq on 2017/4/24.
 */

object DateUtils {
    private val DATE_FORMAT_DATETIME = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    private val DATE_FORMAT_DATE = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    private val DATE_FORMAT_TIME = SimpleDateFormat("HH:mm:ss", Locale.US)


    /**
     * 获取当前月最大天数
     *
     * @return
     */
    val currentMonthMaxDay: Int
        get() {
            val calendar = Calendar.getInstance()
            return calendar.getActualMaximum(Calendar.DATE)
        }

    /**
     * 获取当前年
     *
     * @return
     */
    val currentYear: Int
        get() {
            val simpleDateFormat = SimpleDateFormat("yyyy", Locale.US)
            val yearStr = simpleDateFormat.format(Date(System.currentTimeMillis()))
            return Integer.parseInt(yearStr)
        }

    /**
     * 获取当前月
     *
     * @return 中国时间的当前月
     */
    val currentMonth: Int
        get() {
            val simpleDateFormat = SimpleDateFormat("MM", Locale.US)
            val monthStr = simpleDateFormat.format(Date(System.currentTimeMillis()))
            return Integer.parseInt(monthStr)
        }

    /**
     * 获取当前日
     *
     * @return
     */
    val currentDay: Int
        get() {
            val simpleDateFormat = SimpleDateFormat("dd", Locale.US)
            val dayStr = simpleDateFormat.format(Date(System.currentTimeMillis()))
            return Integer.parseInt(dayStr)
        }

    /**
     * 获取当前时
     *
     * @return 当前时
     */
    val currentHour: Int
        get() {
            val simpleDateFormat = SimpleDateFormat("HH", Locale.US)
            val monthStr = simpleDateFormat.format(Date(System.currentTimeMillis()))
            return Integer.parseInt(monthStr)
        }

    /**
     * 默认格式化格式时间:yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    fun formatDataTime(date: Long): String {
        return DATE_FORMAT_DATETIME.format(Date(date))
    }

    /**
     * 默认格式化格式时间:yyyy-MM-dd
     *
     * @param date
     * @return
     */
    fun formatDate(date: Long): String {
        return DATE_FORMAT_DATE.format(Date(date))
    }

    /**
     * 默认格式化格式时间:HH:mm:ss
     *
     * @param date
     * @return
     */
    fun formatTime(date: Long): String {
        return DATE_FORMAT_TIME.format(Date(date))
    }

    /**
     * 自定义格式化
     *
     * @param dateStr 字符串时间戳
     * @param format  自定义的格式
     * @return
     */
    fun formatDateCustom(dateStr: String, format: String): String {
        return SimpleDateFormat(format, Locale.US).format(Date(java.lang.Long.parseLong(dateStr)))
    }

    /**
     * 自定义格式化
     *
     * @param date   时间Date
     * @param format 自定义的格式
     * @return
     */
    fun formatDateCustom(date: Date, format: String): String {
        return SimpleDateFormat(format, Locale.US).format(date)
    }

    /**
     * 获取指定年月的Calendar
     *
     * @param year  年份
     * @param month 月份 (1-12)
     * @return
     */
    fun getCalendarByYearMonth(year: Int, month: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)    //Calendar月份为0-11，所以减1
        return calendar
    }

    /**
     * 获得某个月最大天数
     *
     * @param year  年份
     * @param month 月份 (1-12)
     * @return 某个月最大天数
     */
    fun getMaxDayByYearMonth(year: Int, month: Int): Int {
        val calendar = getCalendarByYearMonth(year, month)
        return calendar.getActualMaximum(Calendar.DATE)
    }

    /**
     * 获取某年某月第一天对应周几
     *
     * @param year  年份
     * @param month 月份 (1-12)
     * @return 中国时间的周几
     */
    fun getFirstDayOfWeekByYearMonth(year: Int, month: Int): Int {
        val calendar = getCalendarByYearMonth(year, month)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        //Calendar中周日为1，转换为中国时间要减1
        val week = calendar.get(Calendar.DAY_OF_WEEK) - 1
        return if (week == 0) {
            7
        } else {
            week
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
    fun getDayOfWeekByYearMonthDay(year: Int, month: Int, day: Int): Int {
        val calendar = getCalendarByYearMonth(year, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        //Calendar中周日为1，转换为中国时间要减1
        val week = calendar.get(Calendar.DAY_OF_WEEK) - 1
        return if (week == 0) {
            7
        } else {
            week
        }
    }


    /**
     * 获取指定时间的Date
     *
     * @param dateFormatted 格式化好的时间str
     * @param style        时间的格式
     * @return 指定时间的Date对象或null
     */
    fun string2Date(dateFormatted: String, style: String): Date? {
        val simpleDateFormat = SimpleDateFormat(style, Locale.US)
        if (dateFormatted.length < 6) {
            return null
        }
        return simpleDateFormat.parse(dateFormatted)
    }

    /**
     * 格式化数字
     *
     * @param content 数字
     * @return 若content为0-9，则补0至两位数；若content大于等于10，则原样返回
     */
    fun formatNumber(content: Int): String {
        return if (content in 0..9) {
            "0$content"
        } else if (content >= 10) {
            content.toString()
        } else {
            ""
        }
    }

    /**
     * 格式化数字
     *
     * @param content 数字
     * @return 若content为0-9，则补0至两位数；若content大于等于10，则原样返回
     */
    fun formatNumber(content: String): String {
        val contentInt = Integer.parseInt(content)
        return if (contentInt in 0..9) {
            "0$content"
        } else if (contentInt >= 10) {
            content
        } else {
            ""
        }
    }
}
