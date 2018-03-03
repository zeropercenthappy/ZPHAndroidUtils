package com.zeropercenthappy.utilslibrary;

import android.util.Log;

/**
 * Created by Administrator on 2017/4/1.
 */

public class LogUtils {
    private static String LogTag = "debug";

    private static boolean enable = false;

    public static boolean isEnable() {
        return enable;
    }

    public static void setEnable(boolean enable) {
        LogUtils.enable = enable;
    }

    public static String getLogTag() {
        return LogTag;
    }

    public static void setLogTag(String logTag) {
        LogTag = logTag;
    }

    public static void i(String content) {
        if (enable) {
            Log.i(LogTag, content);
        }
    }

    public static void i(String tag, String content) {
        if (enable) {
            Log.i(tag, content);
        }
    }

    public static void e(String content) {
        if (enable) {
            Log.e(LogTag, content);
        }
    }

    public static void e(String tag, String content) {
        if (enable) {
            Log.e(tag, content);
        }
    }

}
