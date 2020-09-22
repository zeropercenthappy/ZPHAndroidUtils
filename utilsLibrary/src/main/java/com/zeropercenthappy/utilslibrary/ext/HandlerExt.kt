package com.zeropercenthappy.utilslibrary.ext

import android.os.Handler
import android.os.Looper

fun mainHandlerPost(runnable: () -> Unit) {
    Handler(Looper.getMainLooper()).post(runnable)
}

fun mainHandlerPostDelay(delay: Long, runnable: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(runnable, delay)
}