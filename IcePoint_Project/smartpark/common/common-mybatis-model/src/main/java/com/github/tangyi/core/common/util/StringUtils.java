package com.github.tangyi.core.common.util;


import com.github.tangyi.core.common.BaseConstants;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 字符串工具
 *
 * @author hedongzhou
 * @date 2018/06/13
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 模板正则
     */
    private static final Pattern TEMPLATE_PATTERN = Pattern.compile("\\$\\{(.+?)\\}");

    /**
     * Emoji表情符正则
     */
    private static final Pattern EMOJI_PATTERN = Pattern.compile("[^(\u2E80-\u9FFF\\w\\s`~!@#\\$%\\^&\\*\\(\\)_+-？（）——=\\[\\]{}\\|;。，、《》”：；“！……’:'\"<,>\\.?/\\\\*)]");

    /**
     * 转换成字符串
     *
     * @param obj
     * @return
     */
    public static String value(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return String.valueOf(obj);
        }
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成无横杆的UUID
     *
     * @return
     */
    public static String getNoLineUUID() {
        return replace(UUID.randomUUID().toString(), "-", "");
    }

    /**
     * 对比
     *
     * @param start
     * @param end
     * @param compare
     * @return
     */
    public static boolean between(String start, String end, String compare) {
        return start.compareTo(compare) < 1 && end.compareTo(compare) > -1;
    }

    /**
     * 是否为空，如果为空，则取值replace
     *
     * @param value
     * @param replace
     * @return
     */
    public static String ifBlank(String value, String replace) {
        return isNotBlank(value) ? value : replace;
    }

    /**
     * 左补充
     *
     * @param value
     * @param maxLength
     * @param rightLength
     * @param replace
     * @return
     */
    public static String leftFill(Object value, int maxLength, int rightLength, Object replace) {
        String str = ifBlank(value(value), "");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < maxLength - rightLength; i++) {
            sb.append(replace);
        }

        sb.append(str.substring(str.length() - rightLength));

        return sb.toString();
    }

    /**
     * 右补充
     *
     * @param value
     * @param maxLength
     * @param leftLength
     * @param replace
     * @return
     */
    public static String rightFill(Object value,
                                   int maxLength,
                                   int leftLength,
                                   Object replace) {
        String str = ifBlank(value(value), "");

        StringBuilder sb = new StringBuilder(substring(str, 0, leftLength));

        for (int i = 0; i < maxLength - leftLength; i++) {
            sb.append(replace);
        }

        return sb.toString();
    }


    /**
     * 获取字符串中文的个数
     *
     * @param str
     * @return
     */
    public static Integer chineseNums(String str) {
        if (isBlank(str)) {
            return 0;
        }
        String regEx = "[\\u4e00-\\u9fa5]";
        String term = str.replaceAll(regEx, "aa");
        return term.length() - str.length();
    }

    /**
     * 下划线转驼峰
     *
     * @param value
     * @return
     */
    public static String convertSnakeToCamel(String value) {
        if (isBlank(value) || !value.contains(BaseConstants.SEPARATOR_UNDERLINE)) {
            return value;
        }

        value = value.toLowerCase();

        StringBuilder result = new StringBuilder();

        String[] split = StringUtils.split(value, "_");
        int index = 0;
        for (String s : split) {
            if (index == 0) {
                result.append(s);
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                if (s.length() > 1) {
                    result.append(s.substring(1));
                }
            }
            index++;
        }

        return result.toString();
    }

    /**
     * 快捷模板渲染
     *
     * @param template
     * @param data
     * @return
     */
    public static String render(String template, Map<String, Object> data) {
        if (CheckUtils.isEmpty(template)) {
            return "";
        }
        if (CheckUtils.isEmpty(data)) {
            return template;
        }

        try {
            StringBuffer sb = new StringBuffer();
            Matcher matcher = TEMPLATE_PATTERN.matcher(template);
            while (matcher.find()) {
                String name = matcher.group(1);
                String value = value(data.get(name));
                if (CheckUtils.isEmpty(value)) {
                    value = "";
                }
                matcher.appendReplacement(sb, value);
            }
            matcher.appendTail(sb);

            return sb.toString();
        } catch (Exception e) {
            LogUtils.error(e);
        }

        return template;
    }

    /**
     * byte数组转换成16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        if (src == null || src.length <= 0) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        String hv;
        for (byte b : src) {
            hv = Integer.toHexString(b & 0xFF);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 分隔，通过trim排序空字符串
     *
     * @param str
     * @param separatorChars
     * @return
     */
    public static List<String> splitToList(String str,
                                           String separatorChars) {
        str = trim(str);
        if (isBlank(str)) {
            return new ArrayList<>(0);
        }

        return Arrays.stream(split(str, separatorChars))
                .map(StringUtils::trim)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
    }

    /**
     * 去掉Emoji表情符
     *
     * @param str
     * @return
     */
    public static String replaceEmoji(String str) {
        Matcher matcher = EMOJI_PATTERN.matcher(str);
        return trimToEmpty(matcher.replaceAll(""));
    }

}
