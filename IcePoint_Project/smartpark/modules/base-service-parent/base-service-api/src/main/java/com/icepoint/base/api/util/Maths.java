package com.icepoint.base.api.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Maths {

    // 4.浮点数据处理宏
    private static final Float FLOAT_LOWDATA = 0.000001f;

    /**
     * 四舍五入,返回Int
     *
     * @param num
     * @return
     */
    public static int halfUp(String num) {
        int n = 0;
        if (StringUtils.isEmpty(num))
            return n;
        BigDecimal dec = new BigDecimal(num);
        return (int) dec.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double twoNumber(Double money) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(money));
    }

    // 判断两个浮点数据相等
    public static boolean FLOAT_EQUAL(float a, float b) {
        return (Math.abs((a) - (b)) < FLOAT_LOWDATA);
    }

    // 判断两个浮点数据不相等
    public static boolean FLOAT_NOEQUAL(float a, float b) {
        return (!FLOAT_EQUAL(a, b));
    }

    // 判断浮点数小于等于零
    public static boolean FLOAT_SMALLANDEQUALZERO(float a) {
        return ((a) <= FLOAT_LOWDATA);
    }

    public static boolean FLOAT_GREATERZERO(float a) {
        return ((a) > FLOAT_LOWDATA);
    }

    // 判断浮点数等于零
    public static boolean FLOAT_EQUALZERO(float a) {
        return (Math.abs(a) < FLOAT_LOWDATA);
    }

    // 判断浮点数不等于零
    public static boolean FLOAT_NOEQUALZERO(float a) {
        return (!FLOAT_EQUALZERO(a));
    }

    // 大于等于0且小于等于1
    public static boolean FLOAT_ZERO2ONE(float a) {
        return (FLOAT_EQUALZERO(a) || (FLOAT_GREATERZERO(a) && (FLOAT_SMALLANDEQUALZERO(a - 1.0f))));
    }

}
