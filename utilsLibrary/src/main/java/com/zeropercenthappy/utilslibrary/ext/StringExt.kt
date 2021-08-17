@file:Suppress("FunctionName", "unused")

package com.zeropercenthappy.utilslibrary.ext

import android.util.Base64
import java.nio.charset.Charset

const val STRING_EXT_SEPARATOR = ","

fun String?.toByteSafely(defaultValue: Byte = 0): Byte {
    return try {
        this?.toByte() ?: defaultValue
    } catch (e: Exception) {
        defaultValue
    }
}

fun String?.toShortSafely(defaultValue: Short = 0): Short {
    return try {
        this?.toShort() ?: defaultValue
    } catch (e: Exception) {
        defaultValue
    }
}

fun String?.toIntSafely(defaultValue: Int = 0): Int {
    return try {
        this?.toInt() ?: defaultValue
    } catch (e: Exception) {
        defaultValue
    }
}

fun String?.toLongSafely(defaultValue: Long = 0L): Long {
    return try {
        this?.toLong() ?: defaultValue
    } catch (e: Exception) {
        defaultValue
    }
}

fun String?.toFloatSafely(defaultValue: Float = 0f): Float {
    return try {
        this?.toFloat() ?: defaultValue
    } catch (e: Exception) {
        defaultValue
    }
}

fun String?.toDoubleSafely(defaultValue: Double = 0.0): Double {
    return try {
        this?.toDouble() ?: defaultValue
    } catch (e: Exception) {
        defaultValue
    }
}

fun String.toAscii(): String {
    return map { it.code.toString(16) }.joinToString(separator = STRING_EXT_SEPARATOR)
}

fun String.asciiToString(): String {
    return toInt(16).toChar().toString()
}

fun String.toBase64(): String {
    return Base64.encodeToString(this.toByteArray(), Base64.DEFAULT)
}

fun String.base64ToString(): String {
    return String(Base64.decode(this, Base64.DEFAULT))
}

fun String.toHex(): String {
    return this.toByteArray().joinToString(separator = "") { "%02x".format(it) }
}

fun String.hexToString(): String {
    return String(
        this.chunked(2)
            .map { it.uppercase().toInt(16).toByte() }
            .toByteArray()
    )
}

fun String.toUcs2(): ByteArray {
    val charset = Charsets.UTF_16LE
    return this.toByteArray(charset)
}

fun ByteArray.ucs2ToString(): String {
    val charset = Charsets.UTF_16LE
    return String(this, charset)
}

fun String.toUTF16LE(): ByteArray {
    val charset = Charsets.UTF_16LE
    return this.toByteArray(charset)
}

fun ByteArray.UTF16LEToString(): String {
    val charset = Charsets.UTF_16LE
    return String(this, charset)
}

fun String.toLatin1(): ByteArray {
    val charset = Charset.forName("latin1")
    return this.toByteArray(charset)
}

fun ByteArray.latin1ToString(): String {
    val charset = Charset.forName("latin1")
    return String(this, charset)
}