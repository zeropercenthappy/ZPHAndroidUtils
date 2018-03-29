package com.zeropercenthappy.utilslibrary;

public class ZPHTextUtils {
    public static boolean notEmpty(String... values) {
        boolean result = true;
        for (String value : values) {
            if (android.text.TextUtils.isEmpty(value)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
