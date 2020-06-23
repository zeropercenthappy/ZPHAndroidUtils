package com.zeropercenthappy.utilslibrary.utils

import android.util.Log

interface ZPHLogger {

    companion object {
        var SHOW_LOG = true
    }

    fun warn(execute: () -> String) {
        warn(execute.invoke())
    }

    fun warn(content: String) {
        if (SHOW_LOG) {
            Log.w(this::class.java.simpleName, content)
        }
    }

    fun debug(execute: () -> String) {
        debug(execute.invoke())
    }

    fun debug(content: String) {
        if (SHOW_LOG) {
            Log.d(this::class.java.simpleName, content)
        }
    }

    fun info(execute: () -> String) {
        info(execute.invoke())
    }

    fun info(content: String) {
        if (SHOW_LOG) {
            Log.i(this::class.java.simpleName, content)
        }
    }

    fun error(execute: () -> String) {
        error(execute.invoke())
    }

    fun error(content: String) {
        if (SHOW_LOG) {
            Log.e(this::class.java.simpleName, content)
        }
    }

    fun verbose(execute: () -> String) {
        verbose(execute.invoke())
    }

    fun verbose(content: String) {
        if (SHOW_LOG) {
            Log.v(this::class.java.simpleName, content)
        }
    }
}