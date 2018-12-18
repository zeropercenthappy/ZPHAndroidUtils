package com.zeropercenthappy.utilslibrary.utils

import android.util.Log

object LogUtils {
    @JvmStatic
    var LOG_TAG = "DEBUG"
    @JvmStatic
    var ENABLE = false

    @JvmStatic
    fun i(content: String) {
        if (ENABLE) {
            Log.i(LOG_TAG, content)
        }
    }

    @JvmStatic
    fun v(content: String) {
        if (ENABLE) {
            Log.v(LOG_TAG, content)
        }
    }

    @JvmStatic
    fun w(content: String) {
        if (ENABLE) {
            Log.w(LOG_TAG, content)
        }
    }

    @JvmStatic
    fun e(content: String) {
        if (ENABLE) {
            Log.e(LOG_TAG, content)
        }
    }

    @JvmStatic
    fun wtf(content: String) {
        if (ENABLE) {
            Log.wtf(LOG_TAG, content)
        }
    }
}