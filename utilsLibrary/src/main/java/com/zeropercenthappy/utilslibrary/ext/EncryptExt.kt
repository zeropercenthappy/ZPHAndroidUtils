package com.zeropercenthappy.utilslibrary.ext

import android.util.Base64
import java.nio.charset.Charset
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/** 加密算法 */
private const val KEY_ALGORITHM = "AES"

/** 字符编码 */
private val CHARSET = Charset.forName("UTF-8")

/** 加解密算法/工作模式/填充方式 */
private const val CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding"

/**
 * 对字符串加密
 * @param key 16位key
 * @return  加密后的字符串
 */
fun String.aesEncrypt(key: String): String {
    val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
    val byteArray = key.toByteArray(CHARSET)
    val keySpec = SecretKeySpec(byteArray, KEY_ALGORITHM)
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, IvParameterSpec(byteArray))
    val encrypted = cipher.doFinal(this.toByteArray(CHARSET))
    return Base64.encodeToString(encrypted, Base64.NO_WRAP)
}

/**
 * 对字符串解密
 * @param key 16位key
 * @return  解密得到的字符串
 */
fun String.aesDecrypt(key: String): String {
    val encrypted = Base64.decode(this.toByteArray(CHARSET), Base64.NO_WRAP)
    val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
    val byteArray = key.toByteArray(CHARSET)
    val keySpec = SecretKeySpec(byteArray, KEY_ALGORITHM)
    cipher.init(Cipher.DECRYPT_MODE, keySpec, IvParameterSpec(byteArray))
    val original = cipher.doFinal(encrypted)
    return String(original, CHARSET)
}

/**
 * 字符串MD5
 */
fun String.toMD5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.joinToString("") { "%02x".format(it) }
}