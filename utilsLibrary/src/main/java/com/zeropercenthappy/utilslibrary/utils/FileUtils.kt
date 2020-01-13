package com.zeropercenthappy.utilslibrary.utils

import android.webkit.MimeTypeMap
import java.io.*
import java.util.*

object FileUtils {


    private const val BUFFER_SIZE = 8192

    @JvmStatic
    fun closeIO(vararg closeables: Closeable?) {
        if (closeables.isNullOrEmpty()) {
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
     * Check the file is exist or not. If the file isn't exist, it will try to create all needs
     * directory and itself.
     *
     * @param file  destination file
     *
     * @return      true or false(if not exist and create failure)
     */
    @JvmStatic
    fun checkFileAndCreate(file: File): Boolean {
        return try {
            val dir = file.parentFile
            dir.mkdirs()
            if (file.exists()) {
                true
            } else {
                file.createNewFile()
                file.exists()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Check the file is exist or not. If the file isn't exist, it will try to create all needs
     * directory and itself.
     *
     * @param path  destination file's absolute path
     *
     * @return      true or false(if not exist and create failure)
     */
    @JvmStatic
    fun checkFileAndCreate(path: String): Boolean {
        return try {
            val file = File(path)
            val dir = file.parentFile
            dir.mkdirs()
            if (file.exists()) {
                true
            } else {
                file.createNewFile()
                file.exists()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Write file through InputStream.
     *
     * @param storageFile   storageFile
     *
     * @param inputStream   inputStream
     *
     * @return              write result
     */
    @JvmStatic
    fun writeFileByIS(storageFile: File, inputStream: InputStream): Boolean {
        var bufferedOutputStream: BufferedOutputStream? = null
        try {
            if (!checkFileAndCreate(storageFile)) {
                return false
            }
            bufferedOutputStream = BufferedOutputStream(FileOutputStream(storageFile))
            val buffer = ByteArray(BUFFER_SIZE)
            var currentLength: Int
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
     * Write string to a file.
     *
     * @param file      file
     *
     * @param content   content
     *
     * @param append    is append
     *
     * @return          write result
     */
    @JvmStatic
    fun writeString2File(file: File, content: String, append: Boolean) = try {
        val bufferedWriter = BufferedWriter(FileWriter(file.absolutePath, append))
        bufferedWriter.write(content)
        bufferedWriter.flush()
        closeIO(bufferedWriter)
        true
    } catch (e: IOException) {
        e.printStackTrace()
        false
    }


    /**
     *  Get file's MimeType.
     *
     * @param file  Destination file
     *
     * @return      file's MimeType or null
     */
    @JvmStatic
    fun getFileMimeType(file: File): String? {
        return getFileMimeTypeByExtension(file.extension)
    }

    /**
     * Get file's MimeType.
     *
     * @param path  File's absolute path
     *
     * @return      file's MimeType or null
     */
    @JvmStatic
    fun getFileMimeType(path: String): String? {
        val file = File(path)
        return getFileMimeTypeByExtension(file.extension)
    }

    /**
     * Get file's MimeType by extension.
     *
     * @param extension 后缀名
     *
     * @return          file's MimeType or null
     */
    @JvmStatic
    fun getFileMimeTypeByExtension(extension: String): String? {
        return MimeTypeMap
            .getSingleton()
            .getMimeTypeFromExtension(extension.toLowerCase(Locale.getDefault()))
    }

    /**
     * Get file's size, unit：Byte
     *
     * @param file  file
     *
     * @return      file's size
     */
    fun getSize(file: File?): Long {
        var size: Long = 0
        if (file == null || !file.exists()) {
            return size
        }
        if (file.isFile) {
            return file.length()
        } else if (file.isDirectory) {
            val files = file.listFiles()
            for (subFile in files) {
                size += getSize(subFile)
            }
        }
        return size
    }

    /**
     * Copy file.
     *
     * @param sourceFile  source file
     *
     * @param destinationFile  destination file
     *
     * @return copy result
     */
    fun copyFile(sourceFile: File, destinationFile: File): Boolean {
        if (!sourceFile.exists()) {
            // 若源文件不存在
            return false
        }
        if (!deleteFile(destinationFile)) {
            // 若旧目标文件删除失败
            return false
        }
        if (!checkFileAndCreate(destinationFile)) {
            // 若目标文件创建失败
            return false
        }
        var inputStream: InputStream? = null
        var bufferedOutputStream: BufferedOutputStream? = null
        try {
            inputStream = FileInputStream(sourceFile)
            bufferedOutputStream = BufferedOutputStream(FileOutputStream(destinationFile, false))
            val data = ByteArray(BUFFER_SIZE)
            while (true) {
                val length = inputStream.read(data, 0, BUFFER_SIZE)
                if (length != -1) {
                    bufferedOutputStream.write(data, 0, length)
                } else {
                    break
                }
            }
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        } finally {
            if (inputStream != null) {
                closeIO(inputStream)
            }
            if (bufferedOutputStream != null) {
                closeIO(bufferedOutputStream)
            }
        }
    }

    /**
     * Delete file or directory
     *
     * @param file  destination file or directory
     *
     * @return      delete result
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