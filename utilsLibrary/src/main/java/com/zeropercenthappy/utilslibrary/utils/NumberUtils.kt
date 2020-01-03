package com.zeropercenthappy.utilslibrary.utils

import java.math.RoundingMode
import java.text.DecimalFormat

object NumberUtils {

    /**
     * 格式化小数
     *
     * @param content     目标数据
     * @param scaleNumber 保留的小数位数
     * @return 格式化后的String
     */
    @JvmStatic
    fun formatDecimal(content: Float, scaleNumber: Int): String {
        val ruleSB = StringBuilder("#.")
        for (i in 0 until scaleNumber) {
            ruleSB.append("0")
        }
        val decimalFormat = DecimalFormat(ruleSB.toString())
        decimalFormat.roundingMode = RoundingMode.HALF_UP
        val result = StringBuilder(decimalFormat.format(content))
        if (result[0] == '.') {
            result.insert(0, "0")
        }
        return result.toString()
    }

    /**
     * 格式化小数
     *
     * @param content     目标数据
     * @param scaleNumber 保留的小数位数
     * @return 格式化后的String
     */
    @JvmStatic
    fun formatDecimal(content: Double, scaleNumber: Int): String {
        val ruleSB = StringBuilder("#.")
        for (i in 0 until scaleNumber) {
            ruleSB.append("0")
        }
        val decimalFormat = DecimalFormat(ruleSB.toString())
        decimalFormat.roundingMode = RoundingMode.HALF_UP
        val result = StringBuilder(decimalFormat.format(content))
        if (result[0] == '.') {
            result.insert(0, "0")
        }
        return result.toString()
    }

    /**
     * 获取指定数据的二进制数某一位的数据
     * @param data          目标数据
     * @param position      指定二进制的某个位置，从0开始，从右数起
     * @return              指定位置的数，0或1
     */
    fun getBinaryDataPosition(data: Int, position: Int): Int {
        return data shr position and 1
    }

    /**
     * 获取指定数据的二进制数某一位的数据
     * @param data    目标数据
     * @param position      指定二进制的某个位置，从0开始，从右数起
     * @return              指定位置的数，0或1
     */
    fun getBinaryDataPosition(data: Long, position: Int): Int {
        return (data shr position and 1).toInt()
    }
}