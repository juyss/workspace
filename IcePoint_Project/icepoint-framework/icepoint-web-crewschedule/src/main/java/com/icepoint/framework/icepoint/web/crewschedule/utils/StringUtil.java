package com.icepoint.framework.icepoint.web.crewschedule.utils;

/**
 * @author Juyss (Copy from park)
 * @version 1.0
 * @ClassName StringUtil
 * @description
 * @since 2021-07-30 15:30
 */
public class StringUtil {

    /**
     * 全部替换
     *
     * @param text 替换目标文本
     * @param repl 旧值
     * @param with 新值
     * @return String
     * @author zhangzhong
     * date: 2020/2/28 15:41
     */
    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    /**
     * with max次替换 repl
     *
     * @param text 替换目标文本
     * @param repl 旧值
     * @param with 新值
     * @param max  替换次数
     * @return String
     * @author zhangzhong
     * date: 2020/2/28 15:37
     */
    public static String replace(String text, String repl, String with, int max) {
        if (isInvalid(text) || isInvalid(repl) || with == null || max == 0) {
            return text;
        }
        int start = 0;
        int end = text.indexOf(repl, start);
        if (end == -1) {
            return text;
        }
        int replLength = repl.length();
        int increase = with.length() - replLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        StringBuffer buf = new StringBuffer(text.length() + increase);
        while (end != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(repl, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * 判断字符串是否有效
     *
     * @param s 字符串
     * @return boolean
     * @author zhangyao
     * date: 2020/8/14 10:56
     */
    public static boolean isInvalid(String s) {
        return s == null || s.trim().length() == 0;
    }
}
