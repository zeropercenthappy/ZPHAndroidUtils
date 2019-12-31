package com.zeropercenthappy.utilslibrary.ext

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import java.io.Serializable

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.toast(text: String, length: Int) {
    Toast.makeText(this, text, length).show()
}

inline fun <reified T> Context.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T> Context.startActivity(vararg pairs: Pair<String, Any>) {
    val intent = Intent(this, T::class.java)
    for (pair in pairs) {
        when (val value = pair.second) {
            is String -> {
                intent.putExtra(pair.first, value)
            }
            is Char -> {
                intent.putExtra(pair.first, value)
            }
            is Byte -> {
                intent.putExtra(pair.first, value)
            }
            is Short -> {
                intent.putExtra(pair.first, value)
            }
            is Int -> {
                intent.putExtra(pair.first, value)
            }
            is Long -> {
                intent.putExtra(pair.first, value)
            }
            is Float -> {
                intent.putExtra(pair.first, value)
            }
            is Double -> {
                intent.putExtra(pair.first, value)
            }
            is Boolean -> {
                intent.putExtra(pair.first, value)
            }
            is Serializable -> {
                intent.putExtra(pair.first, value)
            }
            is Bundle -> {
                intent.putExtra(pair.first, value)
            }
            is IntArray -> {
                intent.putExtra(pair.first, value)
            }
            is ByteArray -> {
                intent.putExtra(pair.first, value)
            }
            is CharArray -> {
                intent.putExtra(pair.first, value)
            }
            is LongArray -> {
                intent.putExtra(pair.first, value)
            }
            is FloatArray -> {
                intent.putExtra(pair.first, value)
            }
            is Parcelable -> {
                intent.putExtra(pair.first, value)
            }
            is ShortArray -> {
                intent.putExtra(pair.first, value)
            }
            is DoubleArray -> {
                intent.putExtra(pair.first, value)
            }
            is BooleanArray -> {
                intent.putExtra(pair.first, value)
            }
            is CharSequence -> {
                intent.putExtra(pair.first, value)
            }
        }
    }
    startActivity(intent)
}