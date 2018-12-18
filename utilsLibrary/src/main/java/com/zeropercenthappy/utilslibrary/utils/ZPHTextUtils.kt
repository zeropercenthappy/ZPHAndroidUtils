package com.zeropercenthappy.utilslibrary.utils

import android.text.TextUtils

object ZPHTextUtils {
    @JvmStatic
    fun notEmpty(vararg values: String): Boolean {
        for (value in values) {
            if (TextUtils.isEmpty(value)) {
                return false
            }
        }
        return true
    }

    @JvmStatic
    fun hasEmpty(vararg values: String): Boolean {
        for (value in values) {
            if (TextUtils.isEmpty(value)) {
                return true
            }
        }
        return false
    }
}
