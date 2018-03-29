package com.zeropercenthappy.utilslibrary;

public class TextUtils {
    public static boolean notEmpty(String... values) {
        boolean result = false;
        for (String value : values) {
            if (android.text.TextUtils.isEmpty(value)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
