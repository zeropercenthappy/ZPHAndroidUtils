package com.zeropercenthappy.utilslibrary.utils

import android.util.Log

interface ZPHLogger {

    companion object {
        var SHOW_LOG = true
    }

    fun warn(content: String) {
        if (SHOW_LOG) {
            Log.w(this::class.java.simpleName, content)
        }
    }

    fun debug(content: String) {
        if (SHOW_LOG) {
            Log.d(this::class.java.simpleName, content)
        }
    }

    fun info(content: String) {
        if (SHOW_LOG) {
            Log.i(this::class.java.simpleName, content)
        }
    }

    fun error(content: String) {
        if (SHOW_LOG) {
            Log.e(this::class.java.simpleName, content)
        }
    }

    fun verbose(content: String) {
        if (SHOW_LOG) {
            Log.v(this::class.java.simpleName, content)
        }
    }
}