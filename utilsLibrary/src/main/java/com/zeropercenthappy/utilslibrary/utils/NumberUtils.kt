package com.zeropercenthappy.utilslibrary.utils

object NumberUtils {

    /**
     * 获取指定数据的二进制数某一位的数据
     *
     * @param data      目标数据
     * @param position  指定二进制的某个位置，从0开始，从右数起
     * @return          指定位置的数，0或1
     */
    @JvmStatic
    fun getBinaryDataPosition(data: Int, position: Int): Int {
        return data shr position and 1
    }

    /**
     * 获取指定数据的二进制数某一位的数据
     *
     * @param data      目标数据
     * @param position  指定二进制的某个位置，从0开始，从右数起
     * @return          指定位置的数，0或1
     */
    @JvmStatic
    fun getBinaryDataPosition(data: Long, position: Int): Int {
        return (data shr position and 1).toInt()
    }

    /**
     * 安全转换String为Byte
     *
     * @param data      目标数据
     * @param defValue  转换出错时的默认值
     * @return          目标数据的对应类型或默认值
     */
    @JvmStatic
    fun stringToByteSafely(data: String?, defValue: Byte): Byte {
        return try {
            data?.toByte() ?: defValue
        } catch (e: Exception) {
            defValue
        }
    }

    /**
     * 安全转换String为Short
     *
     * @param data      目标数据
     * @param defValue  转换出错时的默认值
     * @return          目标数据的对应类型或默认值
     */
    @JvmStatic
    fun stringToShortSafely(data: String?, defValue: Short): Short {
        return try {
            data?.toShort() ?: defValue
        } catch (e: Exception) {
            defValue
        }
    }

    /**
     * 安全转换String为Int
     *
     * @param data      目标数据
     * @param defValue  转换出错时的默认值
     * @return          目标数据的对应类型或默认值
     */
    @JvmStatic
    fun stringToIntSafely(data: String?, defValue: Int): Int {
        return try {
            data?.toInt() ?: defValue
        } catch (e: Exception) {
            defValue
        }
    }

    /**
     * 安全转换String为Long
     *
     * @param data      目标数据
     * @param defValue  转换出错时的默认值
     * @return          目标数据的对应类型或默认值
     */
    @JvmStatic
    fun stringToLongSafely(data: String?, defValue: Long): Long {
        return try {
            data?.toLong() ?: defValue
        } catch (e: Exception) {
            defValue
        }
    }

    /**
     * 安全转换String为Float
     *
     * @param data      目标数据
     * @param defValue  转换出错时的默认值
     * @return          目标数据的对应类型或默认值
     */
    @JvmStatic
    fun stringToFloatSafely(data: String?, defValue: Float): Float {
        return try {
            data?.toFloat() ?: defValue
        } catch (e: Exception) {
            defValue
        }
    }

    /**
     * 安全转换String为Double
     *
     * @param data      目标数据
     * @param defValue  转换出错时的默认值
     * @return          目标数据的对应类型或默认值
     */
    @JvmStatic
    fun stringToDoubleSafely(data: String?, defValue: Double): Double {
        return try {
            data?.toDouble() ?: defValue
        } catch (e: Exception) {
            defValue
        }
    }
}