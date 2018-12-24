package com.zeropercenthappy.utilslibrary.utils

import android.content.Context
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author zeropercenthappy
 */

object CacheUtils {

    @JvmStatic
    fun getCacheDir(context: Context): File? {
        return context.externalCacheDir ?: context.cacheDir
    }

    @JvmStatic
    fun clearCacheDir(context: Context): Boolean {
        val cacheDir = getCacheDir(context) ?: return false
        return FileUtils.deleteFile(cacheDir)
    }

    /**
     * @param context
     * @param fileName 想要保存的缓存文件名，包括后缀名。若有已存在的同名文件将会覆盖
     * @return
     */
    @JvmStatic
    fun createCacheFile(context: Context, fileName: String): File? {
        val dir = getCacheDir(context) ?: return null
        val cacheFilePath = dir.path + File.separator + fileName
        val cacheFile = File(cacheFilePath)
        if (cacheFile.exists()) {
            cacheFile.delete()
        }
        try {
            cacheFile.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return cacheFile
    }

    /**
     * @param context
     * @param fileExtension 想要保存的缓存文件后缀名
     * @return
     */
    @JvmStatic
    fun createFormatedCacheFile(context: Context, fileExtension: String): File? {
        val simpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.US)
        val cacheDir = getCacheDir(context) ?: return null
        val formattedExtension = if (fileExtension.startsWith(".")) fileExtension else ".$fileExtension"
        val cacheFilePath = cacheDir.absolutePath + File.separator + simpleDateFormat.format(System.currentTimeMillis()) + formattedExtension
        val cacheFile = File(cacheFilePath)
        if (cacheFile.exists()) {
            cacheFile.delete()
        }
        try {
            cacheFile.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return cacheFile
    }
}
