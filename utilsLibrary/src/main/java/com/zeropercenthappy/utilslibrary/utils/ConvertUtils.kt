package com.zeropercenthappy.utilslibrary.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

/**
 * @author ybq
 */

object ConvertUtils {
    /**
     * drawable转bitmap
     *
     * @param drawable drawable对象
     * @return bitmap
     */
    @JvmStatic
    fun drawable2Bitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }
        val bitmapConfig =
                if (drawable.opacity != PixelFormat.OPAQUE) {
                    Bitmap.Config.ARGB_8888
                } else {
                    Bitmap.Config.RGB_565
                }
        val bitmap =
                if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
                    Bitmap.createBitmap(1, 1, bitmapConfig)
                } else {
                    Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, bitmapConfig)
                }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    /**
     * bitmap转drawable
     *
     * @param bitmap bitmap对象
     * @return drawable
     */
    @JvmStatic
    fun bitmap2Drawable(bitmap: Bitmap?): Drawable? {
        return if (bitmap == null) null else BitmapDrawable(Resources.getSystem(), bitmap)
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    @JvmStatic
    fun dp2px(dpValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * px转dp
     *
     * @param pxValue px值
     * @return dp值
     */
    @JvmStatic
    fun px2dp(pxValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * sp转px
     *
     * @param spValue sp值
     * @return px值
     */
    @JvmStatic
    fun sp2px(spValue: Float): Int {
        val fontScale = Resources.getSystem().displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * px转sp
     *
     * @param pxValue px值
     * @return sp值
     */
    @JvmStatic
    fun px2sp(pxValue: Float): Int {
        val fontScale = Resources.getSystem().displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

}
