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

    /**
     * 获取缓存文件夹
     * context.externalCacheDir ->  /storage/emulated/0/Android/data/{packageName}/cache
     * context.cacheDir -> /data/user/0/{packageName}/cache
     */
    @JvmStatic
    fun getCacheDir(context: Context): File {
        return context.externalCacheDir ?: context.cacheDir
    }

    @JvmStatic
    fun clearCacheDir(context: Context): Boolean {
        val cacheDir = getCacheDir(context)
        return FileUtils.deleteFile(cacheDir)
    }

    /**
     * @param context
     *
     * @param fileName      想要保存的缓存文件名，包括后缀名
     *
     * @param createFile    若cacheFile不存在，是否需要自动创建空文件
     *
     * @return
     */
    @JvmStatic
    fun createCacheFile(context: Context, fileName: String, createFile: Boolean): File? {
        val dir = getCacheDir(context)
        val cacheFilePath = dir.path + File.separator + fileName
        val cacheFile = File(cacheFilePath)
        try {
            if (createFile && !cacheFile.exists()) {
                cacheFile.createNewFile()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return cacheFile
    }

    /**
     * @param context
     *
     * @param fileExtension 想要保存的缓存文件后缀名
     *
     * @param createFile    若cacheFile不存在，是否需要自动创建空文件
     *
     * @return
     */
    @JvmStatic
    fun createFormatCacheFile(
        context: Context,
        fileExtension: String,
        createFile: Boolean
    ): File? {
        val simpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.US)
        val cacheDir = getCacheDir(context)
        val formattedExtension =
            if (fileExtension.startsWith(".")) fileExtension else ".$fileExtension"
        val cacheFilePath =
            cacheDir.absolutePath + File.separator + simpleDateFormat.format(System.currentTimeMillis()) + formattedExtension
        val cacheFile = File(cacheFilePath)
        try {
            if (createFile && !cacheFile.exists()) {
                cacheFile.createNewFile()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return cacheFile
    }
}
