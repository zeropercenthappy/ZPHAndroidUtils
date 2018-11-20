package com.zeropercenthappy.utilslibrary.utils

import android.content.Context
import android.content.pm.PackageManager

/**
 * @author ybq
 */

object AppUtils {
    /**
     * 获取应用版本名称
     *
     * @param context
     * @return
     */
    fun getAppVersionName(context: Context): String {
        val packageName = context.packageName
        return try {
            val packageInfo = context.packageManager.getPackageInfo(packageName, 0)
            packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            ""
        }
    }

    /**
     * 获取应用版本号
     *
     * @param context
     * @return
     */
    fun getAppVersionCode(context: Context): Int {
        val packageName = context.packageName
        return try {
            val packageInfo = context.packageManager.getPackageInfo(packageName, 0)
            packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            0
        }
    }

    /**
     * 获取应用名称
     *
     * @param context
     * @param packageName
     * @return
     */
    fun getAppName(context: Context, packageName: String): String {
        val pm = context.packageManager
        return try {
            val applicationInfo = pm.getApplicationInfo(packageName, 0)
            pm.getApplicationLabel(applicationInfo).toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            ""
        }
    }
}
