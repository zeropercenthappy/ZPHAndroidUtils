package com.zeropercenthappy.utilslibrary;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.util.Map;

public class Md5SignUtils {
    public static String MD5_KEY = "";
    public static String MD5_SECRET = "";

    private static String getMd5Sign(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(MD5_SECRET);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!TextUtils.isEmpty(entry.getValue())) {
                sb.append(entry.getKey()).append(entry.getValue());
            }
        }
        sb.append(MD5_SECRET);
        return getMD5String(sb.toString());
    }

    private static String getMD5String(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void signParamsMap(Map<String, String> paramsMap) {
        paramsMap.put("api_key", MD5_KEY);
        paramsMap.put("api_sign", getMd5Sign(paramsMap));
    }
}
