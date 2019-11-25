package com.zeropercenthappy.utilslibrary.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

/**
 * Created by ybq on 2017/4/24.
 */

object ScreenUtils {
    /**
     * 获取屏幕宽度
     *
     * @return
     */
    @JvmStatic
    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager ?: return -1
        val point = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.defaultDisplay.getRealSize(point)
        } else {
            wm.defaultDisplay.getSize(point)
        }
        return point.x
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    @JvmStatic
    fun getScreenHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager ?: return -1
        val point = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.defaultDisplay.getRealSize(point)
        } else {
            wm.defaultDisplay.getSize(point)
        }
        return point.y
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    @JvmStatic
    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return Resources.getSystem().getDimensionPixelSize(resourceId)
    }

    /**
     * 获取当前状态栏是否可见
     */
    @JvmStatic
    fun isStatusBarVisible(activity: Activity): Boolean {
        val flags = activity.window.attributes.flags
        return flags and WindowManager.LayoutParams.FLAG_FULLSCREEN == 0
    }

    /**
     * 设置状态栏是否为浅色模式
     */
    @JvmStatic
    fun setStatusBarLightMode(activity: Activity, lightMode: Boolean) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }
        val decorView = activity.window.decorView ?: return
        var systemUiVisibility = decorView.systemUiVisibility
        systemUiVisibility = if (lightMode) {
            systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
        decorView.systemUiVisibility = systemUiVisibility
    }

    /**
     * 获取导航栏高度
     *
     * @return
     */
    @JvmStatic
    fun getNavigationBarHeight(context: Context): Int {
        val res = context.resources
        val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId != 0) {
            res.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }

    /**
     * 获取导航栏是否可见
     */
    @JvmStatic
    fun isNavigationBarVisible(activity: Activity): Boolean {
        var isVisible = false
        val decorView = activity.window.decorView as ViewGroup
        for (i in 0 until decorView.childCount) {
            val child = decorView.getChildAt(i)
            val id = child.id
            if (id != View.NO_ID) {
                val resourceEntryName = activity.resources.getResourceEntryName(id)
                if ("navigationBarBackground" == resourceEntryName && child.visibility == View.VISIBLE) {
                    isVisible = true
                    break
                }
            }
        }
        if (isVisible) {
            val visibility = decorView.systemUiVisibility
            isVisible = visibility and View.SYSTEM_UI_FLAG_HIDE_NAVIGATION == 0
        }
        return isVisible
    }
}
