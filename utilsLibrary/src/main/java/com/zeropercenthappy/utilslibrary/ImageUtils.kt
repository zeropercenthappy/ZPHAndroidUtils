package com.zeropercenthappy.utilslibrary

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.TextUtils
import android.util.Base64
import java.io.*

object ImageUtils {

    /**
     * @param imgFile 图像文件
     * @param quality 期望的质量
     * @param cacheFile 压缩后储存的位置
     */
    fun compressImageByQuality(imgFile: File, quality: Int, cacheFile: File): Boolean {
        val imageFileCheck = FileUtils.checkFileAndCreate(imgFile)
        val cacheFileCheck = FileUtils.checkFileAndCreate(cacheFile)
        if (!imageFileCheck || !cacheFileCheck) {
            return false
        }
        val bitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
        val bufferedOutputStream = BufferedOutputStream(FileOutputStream(cacheFile))
        val result = bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bufferedOutputStream)
        bufferedOutputStream.flush()
        bufferedOutputStream.close()
        return result
    }

    /**
     * @param imgFile 图像文件
     * @param maxSideLength 缩放后bitmap的最长边长度，单位：像素
     */
    fun loadScaledBitmap(imgFile: File, maxSideLength: Int): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(imgFile.absolutePath, options)
        var currentMaxSide = Math.max(options.outWidth, options.outHeight)
        var sampleSize = 0
        while (currentMaxSide > maxSideLength) {
            sampleSize += 2
            currentMaxSide /= 2
        }
        options.inJustDecodeBounds = false
        options.inSampleSize = sampleSize
        options.inPreferredConfig = Bitmap.Config.RGB_565
        return BitmapFactory.decodeFile(imgFile.absolutePath, options)
    }

    /**
     * 图片文件转base64
     */
    fun imageToBase64(path: String): String? {
        if (TextUtils.isEmpty(path)) {
            return null
        }
        var fileInputStream: FileInputStream? = null
        return try {
            fileInputStream = FileInputStream(path)
            val data = ByteArray(fileInputStream.available())
            fileInputStream.read(data)
            Base64.encodeToString(data, Base64.DEFAULT)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } finally {
            try {
                fileInputStream?.apply { close() }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * bitmap转base64
     */
    fun bitmapToBase64(bitmap: Bitmap?): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        return if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            byteArrayOutputStream.flush()
            byteArrayOutputStream.close()
            val byteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        } else {
            null
        }
    }

}