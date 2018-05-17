package com.zeropercenthappy.utilslibrary

import android.util.Log

object LogUtils {
    var LOG_TAG = "DEBUG"
    var ENABLE = false

    fun i(content: String) {
        if (ENABLE) {
            Log.i(LOG_TAG, content)
        }
    }

    fun v(content: String) {
        if (ENABLE) {
            Log.v(LOG_TAG, content)
        }
    }

    fun w(content: String) {
        if (ENABLE) {
            Log.w(LOG_TAG, content)
        }
    }

    fun e(content: String) {
        if (ENABLE) {
            Log.e(LOG_TAG, content)
        }
    }

    fun wtf(content: String) {
        if (ENABLE) {
            Log.wtf(LOG_TAG, content)
        }
    }
}