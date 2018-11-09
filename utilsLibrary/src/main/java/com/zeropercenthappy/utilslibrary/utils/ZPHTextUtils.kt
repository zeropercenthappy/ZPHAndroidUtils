package com.zeropercenthappy.utilslibrary.utils

import android.text.TextUtils

object ZPHTextUtils {
    fun notEmpty(vararg values: String): Boolean {
        for (value in values) {
            if (TextUtils.isEmpty(value)) {
                return false
            }
        }
        return true
    }

    fun hasEmpty(vararg values: String): Boolean {
        for (value in values) {
            if (TextUtils.isEmpty(value)) {
                return true
            }
        }
        return false
    }
}
