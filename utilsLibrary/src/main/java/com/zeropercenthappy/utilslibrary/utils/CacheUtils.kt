package com.zeropercenthappy.utilslibrary.utils

import android.content.Context
import java.io.File
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

    /**
     * 获取缓存文件夹下的子文件夹
     */
    @JvmStatic
    fun getSubCacheDir(context: Context, subDirName: String): File {
        val dir = if (subDirName.isEmpty()) {
            getCacheDir(context)
        } else {
            File(getCacheDir(context), subDirName)
        }
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    @JvmStatic
    fun clearCacheDir(context: Context): Boolean {
        val dir = getCacheDir(context)
        return FileUtils.deleteFile(dir)
    }

    @JvmStatic
    fun clearSubCacheDir(context: Context, subDirName: String): Boolean {
        val dir = getSubCacheDir(context, subDirName)
        return FileUtils.deleteFile(dir)
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
    fun createCacheFile(context: Context,
                        fileName: String,
                        createFile: Boolean): File? {
        return createCacheFile(context, "", fileName, createFile)
    }

    /**
     * @param context
     *
     * @param subDirName    缓存存放的子目录
     *
     * @param fileName      想要保存的缓存文件名，包括后缀名
     *
     * @param createFile    若cacheFile不存在，是否需要自动创建空文件
     *
     * @return
     */
    @JvmStatic
    fun createCacheFile(context: Context,
                        subDirName: String,
                        fileName: String,
                        createFile: Boolean): File? {
        return try {
            val dir = getSubCacheDir(context, subDirName)
            val cacheFile = File(dir, fileName)
            if (createFile && !cacheFile.exists()) {
                cacheFile.createNewFile()
            }
            cacheFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
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
        return createFormatCacheFile(context, "", fileExtension, createFile)
    }

    /**
     * @param context
     *
     * @param subDirName       缓存存放的子目录
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
            subDirName: String,
            fileExtension: String,
            createFile: Boolean
    ): File? {
        return try {
            val simpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.US)
            val dir = getSubCacheDir(context, subDirName)
            val formattedExtension = if (fileExtension.startsWith(".")) fileExtension else ".$fileExtension"
            val cacheFile = File(dir, simpleDateFormat.format(System.currentTimeMillis()) + formattedExtension)
            if (createFile && !cacheFile.exists()) {
                cacheFile.createNewFile()
            }
            cacheFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
