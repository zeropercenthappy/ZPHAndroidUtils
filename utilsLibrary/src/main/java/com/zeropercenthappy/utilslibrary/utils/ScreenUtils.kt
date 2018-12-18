package com.zeropercenthappy.utilslibrary.utils

import android.content.Context
import android.content.res.Resources

import java.lang.reflect.Field

/**
 * Created by ybq on 2017/4/24.
 */

object ScreenUtils {
    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    @JvmStatic
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    @JvmStatic
    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    @JvmStatic
    fun getStatusBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    /**
     * 获取导航栏高度
     *
     * @param context
     * @return
     */
    @JvmStatic
    fun getNavigationBarHeight(context: Context): Int {
        val resources = context.resources
        val identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return resources.getDimensionPixelOffset(identifier)
    }
}
