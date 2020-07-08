package com.zeropercenthappy.utilslibrary.ext

import java.math.BigDecimal
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

/**
 * Double使用BigDecimal进行加法运算
 */
fun Double.BigDecimalPlus(double: Double, scale: Int? = null, mode: Int? = null): Double {
    val element0 = BigDecimal(this.toString())
    val element1 = BigDecimal(double.toString())
    val result = element0.add(element1);
    return if (scale != null && mode != null) {
        result.setScale(scale, mode).toDouble()
    } else {
        result.toDouble()
    }
}

/**
 * Float使用BigDecimal进行加法运算
 */
fun Float.BigDecimalPlus(float: Float, scale: Int? = null, mode: Int? = null): Float {
    val element0 = BigDecimal(this.toString())
    val element1 = BigDecimal(float.toString())
    val result = element0.add(element1);
    return if (scale != null && mode != null) {
        result.setScale(scale, mode).toFloat()
    } else {
        result.toFloat()
    }
}

/**
 * Double使用BigDecimal进行减法运算
 */
fun Double.BigDecimalMinus(double: Double, scale: Int? = null, mode: Int? = null): Double {
    val element0 = BigDecimal(this.toString())
    val element1 = BigDecimal(double.toString())
    val result = element0.subtract(element1);
    return if (scale != null && mode != null) {
        result.setScale(scale, mode).toDouble()
    } else {
        result.toDouble()
    }
}

/**
 * Float使用BigDecimal进行加法运算
 */
fun Float.BigDecimalMinus(float: Float, scale: Int? = null, mode: Int? = null): Float {
    val element0 = BigDecimal(this.toString())
    val element1 = BigDecimal(float.toString())
    val result = element0.subtract(element1);
    return if (scale != null && mode != null) {
        result.setScale(scale, mode).toFloat()
    } else {
        result.toFloat()
    }
}

/**
 * Double使用BigDecimal进行乘法运算
 */
fun Double.BigDecimalMultiply(double: Double, scale: Int? = null, mode: Int? = null): Double {
    val element0 = BigDecimal(this.toString())
    val element1 = BigDecimal(double.toString())
    val result = element0.multiply(element1);
    return if (scale != null && mode != null) {
        result.setScale(scale, mode).toDouble()
    } else {
        result.toDouble()
    }
}

/**
 * Float使用BigDecimal进行乘法运算
 */
fun Float.BigDecimalMultiply(float: Float, scale: Int? = null, mode: Int? = null): Float {
    val element0 = BigDecimal(this.toString())
    val element1 = BigDecimal(float.toString())
    val result = element0.multiply(element1);
    return if (scale != null && mode != null) {
        result.setScale(scale, mode).toFloat()
    } else {
        result.toFloat()
    }
}

/**
 * Double使用BigDecimal进行除法运算
 */
fun Double.BigDecimalDivide(double: Double, scale: Int? = null, mode: Int? = null): Double {
    val element0 = BigDecimal(this.toString())
    val element1 = BigDecimal(double.toString())
    val result = element0.divide(element1);
    return if (scale != null && mode != null) {
        result.setScale(scale, mode).toDouble()
    } else {
        result.toDouble()
    }
}

/**
 * Float使用BigDecimal进行乘法运算
 */
fun Float.BigDecimalDivide(float: Float, scale: Int? = null, mode: Int? = null): Float {
    val element0 = BigDecimal(this.toString())
    val element1 = BigDecimal(float.toString())
    val result = element0.divide(element1);
    return if (scale != null && mode != null) {
        result.setScale(scale, mode).toFloat()
    } else {
        result.toFloat()
    }
}