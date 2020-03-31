package com.zeropercenthappy.utilslibrary.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import java.io.File
import java.util.*
import java.util.regex.Pattern

object DeviceUtils {
    /**
     * 获取内核数
     */
    @JvmStatic
    fun getCoreNum(): Int {
        val dir = File("/sys/devices/system/cpu/")
        val files = dir.listFiles { pathname -> Pattern.matches("cpu[0-9]", pathname.name) }
        dir.listFiles { pathname -> Pattern.matches("cpu[0-9]", pathname.name) }
        return files.size
    }

    /**
     * 获取Android设备物理唯一标识符
     *
     * 使用AndroidId来生成UUID，若是系统重置或是刷机后，再调用此方法获取到的唯一标识符将会变化
     */
    @SuppressLint("HardwareIds")
    @JvmStatic
    fun getUniqueID(context: Context): String {
        val androidId =
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return UUID.nameUUIDFromBytes(androidId.toByteArray()).toString()
    }
}