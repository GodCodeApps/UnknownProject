package com.lib_common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/29  20:40
 * @description
 */
public class MoneyUtil {
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static String div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        DecimalFormat myformat;
        if (scale == 1) {
            myformat = new DecimalFormat("0.0");
            return myformat.format(b1.divide(b2, scale, BigDecimal.ROUND_HALF_DOWN).doubleValue());
        } else {
            myformat = new DecimalFormat("0.00");
        }
        return myformat.format(b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue());
    }
}
