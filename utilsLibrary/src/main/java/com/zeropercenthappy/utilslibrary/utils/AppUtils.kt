package com.zeropercenthappy.utilslibrary.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import java.io.File

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
    @JvmStatic
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
    @JvmStatic
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
    @JvmStatic
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

    /**
     * 安装apk文件
     *targetSdkVersion大于25时必须获取权限：
     *<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
     */
    @JvmStatic
    fun installAPk(context: Context, apkFile: File) {
        if (!apkFile.exists()) {
            return
        }
        val intent = Intent(Intent.ACTION_VIEW)
        val type = FileUtils.getFileMimeTypeByExtension("apk")
        val data: Uri?
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            data = Uri.fromFile(apkFile)
        } else {
            val authority = context.packageName + ".fileProvider"
            data = FileProvider.getUriForFile(context, authority, apkFile)
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.grantUriPermission(context.packageName, requireNotNull(data), Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setDataAndType(data, type)
        context.startActivity(intent)
    }
}
