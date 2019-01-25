package com.zeropercenthappy.utilslibrary.utils

import android.content.Context
import android.content.SharedPreferences

object SPUtils {
    @JvmStatic
    private fun getSP(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @JvmStatic
    fun setValue(context: Context, key: String, value: String): Boolean {
        val sharedPreferences = getSP(context)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        return editor.commit()
    }

    @JvmStatic
    fun getValue(context: Context, key: String): String? {
        val sharedPreferences = getSP(context)
        return sharedPreferences.getString(key, null)
    }

}