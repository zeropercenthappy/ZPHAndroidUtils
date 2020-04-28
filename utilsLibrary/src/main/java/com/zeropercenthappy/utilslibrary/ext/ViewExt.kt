package com.zeropercenthappy.simpletest.simple

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

/**
 * 防抖动点击事件监听器
 *
 * @param debounce  防抖动时限
 * @param action    点击事件回调
 */
fun View.setOnDebounceClickListener(
    debounce: Long = 500,
    action: (view: View) -> Unit
) {
    var enableClickFlag = true
    val flagRunnable = Runnable { enableClickFlag = true }
    setOnClickListener {
        if (enableClickFlag) {
            enableClickFlag = false
            action(it)
            postDelayed(flagRunnable, debounce)
        }
    }
}

/**
 *  节流输入事件监听器
 *
 *  @param throttle     节流时限
 *  @param onTextChange 内容改变回调
 */
fun EditText.setOnThrottleTextChangeListener(
    throttle: Long = 500,
    onTextChange: (s: CharSequence?) -> Unit
) {
    var content: CharSequence? = null
    val runnable = Runnable {
        onTextChange.invoke(content)
    }
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            content = s
            removeCallbacks(runnable)
            postDelayed(runnable, throttle)
        }
    })
}