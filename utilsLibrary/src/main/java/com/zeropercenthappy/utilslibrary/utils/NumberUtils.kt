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

}