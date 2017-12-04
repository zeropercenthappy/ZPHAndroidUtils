package com.zeropercenthappy.utilslibrary;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class NumberUtils {

    /**
     * 格式化小数
     *
     * @param content     目标数据
     * @param scaleNumber 保留的小数位数
     * @return 格式化后的String
     */
    public static String formatDecimal(double content, int scaleNumber) {
        StringBuilder ruleSB = new StringBuilder("#.");
        for (int i = 0; i < scaleNumber; i++) {
            ruleSB.append("0");
        }
        DecimalFormat decimalFormat = new DecimalFormat(ruleSB.toString());
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        StringBuilder result = new StringBuilder(decimalFormat.format(content));
        if (result.charAt(0) == '.') {
            result.insert(0, "0");
        }
        return result.toString();
    }

}