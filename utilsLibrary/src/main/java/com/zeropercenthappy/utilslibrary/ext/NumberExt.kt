package com.zeropercenthappy.utilslibrary.ext

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * @param scaleNumber 保留的小数位数
 * @return 格式化后的字符串
 */
fun Float.format(scaleNumber: Int): String {
    val ruleSB = StringBuilder("#.")
    for (i in 0 until scaleNumber) {
        ruleSB.append("0")
    }
    val decimalFormat = DecimalFormat(ruleSB.toString())
    decimalFormat.roundingMode = RoundingMode.HALF_UP
    val result = StringBuilder(decimalFormat.format(this))
    if (result[0] == '.') {
        result.insert(0, "0")
    }
    return result.toString()
}

/**
 * @param scaleNumber 保留的小数位数
 * @return 格式化后的字符串
 */
fun Double.format(scaleNumber: Int): String {
    val ruleSB = StringBuilder("#.")
    for (i in 0 until scaleNumber) {
        ruleSB.append("0")
    }
    val decimalFormat = DecimalFormat(ruleSB.toString())
    decimalFormat.roundingMode = RoundingMode.HALF_UP
    val result = StringBuilder(decimalFormat.format(this))
    if (result[0] == '.') {
        result.insert(0, "0")
    }
    return result.toString()
}