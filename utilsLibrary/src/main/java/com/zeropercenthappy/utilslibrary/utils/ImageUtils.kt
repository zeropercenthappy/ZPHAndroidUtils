package com.zeropercenthappy.utilslibrary.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.text.TextUtils
import android.util.Base64
import java.io.*

object ImageUtils {

    /**
     * @param imgFile   图像文件
     * @param scale 缩放的倍数，必须为2的整数倍
     * @param cacheFile 压缩后文件储存的位置
     */
    fun compressImageByScale(imgFile: File, scale: Int, cacheFile: File): Boolean {
        val imageFileCheck = FileUtils.checkFileAndCreate(imgFile)
        val cacheFileCheck = FileUtils.checkFileAndCreate(cacheFile)
        if (!imageFileCheck || !cacheFileCheck) {
            return false
        }

        val options = BitmapFactory.Options()
        options.inSampleSize = scale
        val bitmap = BitmapFactory.decodeFile(imgFile.absolutePath, options)
        val bufferedOutputStream = BufferedOutputStream(FileOutputStream(cacheFile))
        val result = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream)
        bufferedOutputStream.flush()
        bufferedOutputStream.close()
        return result
    }

    /**
     * @param imgFile 图像文件
     * @param maxSideLength 缩放后bitmap的最长边长度，单位：像素
     * @param cacheFile 压缩后储存的位置
     */
    fun compressImageByMaxScale(imgFile: File, maxSideLength: Int, cacheFile: File): Boolean {
        val scaledBitmap = loadScaledBitmap(imgFile, maxSideLength) ?: return false
        val bufferedOutputStream = BufferedOutputStream(FileOutputStream(cacheFile))
        val result = scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream)
        bufferedOutputStream.flush()
        bufferedOutputStream.close()
        return result
    }

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
     * @param cacheFile 压缩后储存的位置
     */
    fun rotateImage(imgFile: File, cacheFile: File): Boolean {
        try {
            val bitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            val exifInterface = ExifInterface(imgFile.absolutePath)
            val matrix = Matrix()
            var angle = 0f
            val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> angle = 90f
                ExifInterface.ORIENTATION_ROTATE_180 -> angle = 180f
                ExifInterface.ORIENTATION_ROTATE_270 -> angle = 270f
            }
            matrix.postRotate(angle)
            val rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            val bufferedOutputStream = BufferedOutputStream(FileOutputStream(cacheFile))
            val result = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream)
            bufferedOutputStream.flush()
            bufferedOutputStream.close()
            rotatedBitmap.recycle()
            return result
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
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