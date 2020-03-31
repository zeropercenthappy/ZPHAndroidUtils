package com.zeropercenthappy.utilslibrary.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
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
     * 当Serial值获取不到（为unknown）时，将使用AndroidId来生成UUID，此时若是系统重置或是刷机，
     * 再调用此方法获取到的唯一标识符将会变化
     */
    @SuppressLint("HardwareIds")
    @JvmStatic
    fun getUniqueID(context: Context): String {
        val serial = Build::class.java.getField("SERIAL")[null].toString()
        if (!TextUtils.equals("unknown", serial.toLowerCase(Locale.ROOT))) {
            return UUID.nameUUIDFromBytes(serial.toByteArray()).toString()
        }
        val androidId =
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return UUID.nameUUIDFromBytes(androidId.toByteArray()).toString()
    }
}