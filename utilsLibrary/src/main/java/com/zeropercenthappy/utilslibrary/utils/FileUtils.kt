package com.zeropercenthappy.utilslibrary.utils

import android.text.TextUtils
import android.webkit.MimeTypeMap
import java.io.*

object FileUtils {
    /**
     * io流缓存大小
     */
    private val BUFFER_SIZE = 8192

    /**
     * 关闭io流
     *
     * @param closeables
     */
    @JvmStatic
    fun closeIO(vararg closeables: Closeable?) {
        if (closeables.isEmpty()) {
            return
        }
        for (io in closeables) {
            try {
                io?.run { close() }
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
    }

    /**
     * 检查文件是否存在，若不存在则新建文件
     *
     * @param file
     * @return
     */
    @JvmStatic
    fun checkFileAndCreate(file: File?): Boolean {
        if (file == null) {
            return false
        }
        return if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        } else {
            true
        }
    }

    /**
     * 检查文件是否存在，若不存在则新建文件
     *
     * @param path
     * @return
     */
    @JvmStatic
    fun checkFileAndCreate(path: String): Boolean {
        if (TextUtils.isEmpty(path)) {
            return false
        }
        val file = File(path)
        return if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        } else {
            true
        }
    }

    /**
     * 通过InputStream写入文件
     */
    @JvmStatic
    fun writeFileByIS(storageFile: File, inputStream: InputStream, append: Boolean): Boolean {
        if (!checkFileAndCreate(storageFile)) {
            return false
        }
        val bufferedOutputStream = BufferedOutputStream(FileOutputStream(storageFile))
        val buffer = ByteArray(BUFFER_SIZE)
        var currentLength: Int
        try {
            while (true) {
                currentLength = inputStream.read(buffer, 0, BUFFER_SIZE)
                if (currentLength != -1) {
                    bufferedOutputStream.write(buffer, 0, currentLength)
                } else {
                    break
                }
            }
            bufferedOutputStream.flush()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        } finally {
            closeIO(inputStream)
            closeIO(bufferedOutputStream)
        }
    }

    /**
     * 向文件写入content
     *
     * @param filename 目标文件
     * @param content  内容
     * @param append   是否追加
     * @return
     */
    @JvmStatic
    fun writeContent2File(filename: String, content: String, append: Boolean): Boolean {
        try {
            val bufferedWriter = BufferedWriter(FileWriter(filename, append))
            bufferedWriter.write(content)
            bufferedWriter.flush()
            closeIO(bufferedWriter)
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
    }

    /**
     * 获取文件的mimeType
     *
     * @param file
     * @return
     */
    @JvmStatic
    fun getFileMimeType(file: File): String? {
        val path = file.absolutePath
        if (TextUtils.isEmpty(path)) {
            return null
        }
        val dotPosition = path.lastIndexOf(".")
        if (dotPosition == -1) {
            return null
        }
        var extension = path.substring(dotPosition + 1)
        extension = extension.toLowerCase()
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }

    /**
     * 获取文件的mimeType
     *
     * @param path
     * @return
     */
    @JvmStatic
    fun getFileMimeType(path: String): String? {
        if (TextUtils.isEmpty(path)) {
            return null
        }
        val dotPosition = path.lastIndexOf(".")
        if (dotPosition == -1) {
            return null
        }
        var extension = path.substring(dotPosition + 1)
        extension = extension.toLowerCase()
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }

    /**
     * 删除文件或目录
     *
     * @param file
     * @return
     */
    @JvmStatic
    fun deleteFile(file: File): Boolean {
        if (!file.exists()) {
            return false
        }
        return when {
            file.isFile -> file.delete()
            file.isDirectory -> {
                val files = file.listFiles()
                files?.run {
                    for (childFile in this) {
                        deleteFile(childFile)
                    }
                }
                file.delete()
            }
            else -> false
        }
    }

}