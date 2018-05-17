package com.zeropercenthappy.utilslibrary

import android.os.Build
import java.io.File
import java.util.*
import java.util.regex.Pattern

object DeviceUtils {
    /**
     * 获取内核数
     *
     * @return
     */
    fun getcoreNum(): Int {
        val dir = File("/sys/devices/system/cpu/")
        val files = dir.listFiles { pathname -> Pattern.matches("cpu[0-9]", pathname.name) }
        dir.listFiles({ pathname -> Pattern.matches("cpu[0-9]", pathname.name) })
        return files.size
    }

    /**
     * 获取Android设备物理唯一标识符
     *
     * @return
     */
    fun getPsuedoUniqueID(): String {
        var devIDShort = "35" + Build.BOARD.length % 10 + Build.BRAND.length % 10
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            devIDShort += Build.SUPPORTED_ABIS[0].length % 10
        } else {
            devIDShort += Build.CPU_ABI.length % 10
        }
        devIDShort += Build.DEVICE.length % 10 + Build.MANUFACTURER.length % 10 + Build.MODEL.length % 10 + Build.PRODUCT.length % 10
        var serial: String
        try {
            serial = Build::class.java.getField("SERIAL").get(null).toString()
            return UUID(devIDShort.hashCode().toLong(), serial.hashCode().toLong()).toString()
        } catch (e: Exception) {
            serial = "ESYDV000"
        }
        return UUID(devIDShort.hashCode().toLong(), serial.hashCode().toLong()).toString()
    }
}