package com.zeropercenthappy.utilslibrary

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v4.content.ContextCompat

object NetworkUtils {
    /**
     * 网络是否连通
     *
     * @param context
     * @return
     */
    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo.isConnected && activeNetworkInfo.state == NetworkInfo.State.CONNECTED
        } else {
            false
        }
    }

    /**
     * 是否是wifi网络
     *
     * @param context
     * @return
     */
    fun isWiFi(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // wifi的状态：ConnectivityManager.TYPE_WIFI
        // 3G的状态：ConnectivityManager.TYPE_MOBILE
        return connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
    }
}