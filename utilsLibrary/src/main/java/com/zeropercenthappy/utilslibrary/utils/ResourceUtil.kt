package com.zeropercenthappy.utilslibrary.utils

import android.content.Context

object ResourceUtil {
    /**
     * @param context
     * @param className id,layout,drawable,etc.
     * @param resourceName resource name
     */
    fun getIdByName(context: Context, className: String, resourceName: String): Int {
        val packageName = context.packageName
        var id = 0
        try {
            val mClass = Class.forName("$packageName.R")
            val classes = mClass.classes
            var desireClass: Class<*>? = null
            for (cls in classes) {
                if (cls.name.split("\$")[1] == className) {
                    desireClass = cls
                    break
                }
            }
            desireClass?.apply {
                id = getField(resourceName).getInt(this)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return id
    }
}