package util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 * Copyright (C), 2020-2020, IoT数据采集测试
 *
 * @author zhangyao
 * @version 1.0
 * date:     2020/8/9 11:16
 * history:
 */
public class StringUtil {

    private static final Log LOGGER = LogFactory.getLog(StringUtil.class);

    /**
     * 整数正则
     */
    private static Pattern NUMBER_PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");

    /**
     * 手机号正则
     */
    private static Pattern MOBILE_PATTERN = Pattern.compile("^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    /**
     * 邮箱正则
     */
    private static Pattern MAIL_PATTERN = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

    /**
     * ip正则
     */
    private static Pattern IP_PATTERN = Pattern.compile(
            "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)");


    /**
     * 经度正则
     */
    private static Pattern LONGITUDE_PATTERN = Pattern.compile("^(\\-|\\+)?(((\\d|[1-9]\\d|1[0-7]\\d|0{1,3})\\.\\d{0,6})|(\\d|[1-9]\\d|1[0-7]\\d|0{1,3})|180\\.0{0,6}|180)$");

    /**
     * 纬度正则
     */
    private static Pattern LATITUDE_PATTERN = Pattern.compile("^(\\-|\\+)?([0-8]?\\d{1}\\.\\d{0,6}|90\\.0{0,6}|[0-8]?\\d{1}|90)$");

    /**
     * DEF_CHARSET
     */
    private static final String DEF_CHARSET = "utf-8";


    /**
     * 截取以逗号分隔的字符串
     *
     * @param str 要截取的字符串，多个字符串以逗号隔开
     * @return List<String>
     * @author zhangyao
     * date: 2020/8/9 11:18
     */
    public static List<String> getList(String str) {
        List<String> list = new ArrayList<String>();
        String[] strArr = str.split(",");
        for (int i = 0; i < strArr.length; i++) {
            list.add(strArr[i]);
        }
        return list;
    }

    public static List<Long> getLongList(String str) {
        List<Long> list = new ArrayList<Long>();
        String[] strArr = str.split(",");
        for (int i = 0; i < strArr.length; i++) {
            list.add(Long.valueOf(strArr[i]));
        }
        return list;
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

    /**
     * 获取字符串长度
     *
     * @param s 字符串
     * @return int
     * @author xiaozhiwei
     * date: 2020/8/21 10:13
     */
    public static int length(String s) {
        if (isInvalid(s)) {
            return 0;
        }
        return s.getBytes().length;
    }

    /**
     * 校验字符串是否为整数
     *
     * @param s 字符串
     * @return true-是，false-否
     * @author xiaozhiwei
     * date: 2020/8/21 11:10
     */
    public static boolean isInteger(String s) {
        return NUMBER_PATTERN.matcher(s).matches();
    }

    /**
     * 是否手机号
     *
     * @param s 字符串
     * @return true-是，false-否
     * @author xiaozhiwei
     * date: 2020/8/21 16:34
     */
    public static boolean isMobile(String s) {
        return MOBILE_PATTERN.matcher(s).matches();
    }

    /**
     * 是否邮箱
     *
     * @param s 字符串
     * @return true-是，false-否
     * @author xiaozhiwei
     * date: 2020/8/21 16:34
     */
    public static boolean isMail(String s) {
        return MAIL_PATTERN.matcher(s).matches();
    }

    /**
     * 是否IP地址
     *
     * @param s 字符串
     * @return true-是，false-否
     * @author xiaozhiwei
     * date: 2020/8/21 16:34
     */
    public static boolean isIP(String s) {
        return IP_PATTERN.matcher(s).matches();
    }

    /**
     * 是否经度
     *
     * @param s 字符串
     * @return true-是，false-否
     * @author xiaozhiwei
     * date: 2020/8/21 16:34
     */
    public static boolean isLongitude(String s) {
        return LONGITUDE_PATTERN.matcher(s).matches();
    }

    /**
     * 是否纬度
     *
     * @param s 字符串
     * @return true-是，false-否
     * @author xiaozhiwei
     * date: 2020/8/21 16:34
     */
    public static boolean isLatitude(String s) {
        return LATITUDE_PATTERN.matcher(s).matches();
    }

    /**
     * tostring
     *
     * @param s 字符串
     * @return String
     * @author xiaozhiwei
     * date: 2020/10/18 16:59
     */
    public static String toString(String s) {
        if (s == null) {
            return "";
        }
        return s;
    }

    /**
     * 字符串转码
     *
     * @param str 字符串
     * @return String
     * @author zhangyao
     * date: 2020/11/9 18:03
     */
    public static String encode(String str) {
        return encode(str, DEF_CHARSET);
    }

    /**
     * 字符串转码
     *
     * @param str     字符串
     * @param charset 转码规则
     * @return String
     * @author zhangyao
     * date: 2020/11/9 18:10
     */
    public static String encode(String str, String charset) {
        String s = null;
        if (!isInvalid(str) && !isInvalid(charset)) {
            try {
                s = URLEncoder.encode(str, charset);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("字符转码错误" + e.toString());
            }
        }
        return s;
    }

    /**
     * obj to string
     *
     * @param obj object
     * @return String
     * @author xiaozhiwei
     * date: 2020/11/19 19:43
     */
    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

    /**
     * 处理火狐导出中文文件名异常
     *
     * @param fileName 文件名
     * @param request  请求
     * @return String
     * @author zhaoyf
     * date:   2020/11/28 13:47
     */
    public static String encodeDownloadFileName(String fileName, HttpServletRequest request) {
        try {
            String agent = request.getHeader("USER-AGENT");
            if (agent != null && agent.toLowerCase().indexOf("firefox") > 0) {
                fileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
            } else {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.debug("处理下载文件名失败", e);
        }
        return fileName;
    }

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
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String convertToCamelCase(String name)
    {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty())
        {
            // 没必要转换
            return "";
        }
        else if (!name.contains("_"))
        {
            // 不含下划线，仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels)
        {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty())
            {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * 驼峰式命名法 例如：user_name->userName
     */
    public static String toCamelCase(String s)
    {
        if (s == null)
        {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (c == '_')
            {
                upperCase = true;
            }
            else if (upperCase)
            {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            }
            else
            {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰命名
     */
    public static String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // 前置字符是否大写
        boolean preCharIsUpperCase = true;
        // 当前字符是否大写
        boolean curreCharIsUpperCase = true;
        // 下一字符是否大写
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }

            curreCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1)) {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                sb.append('_');
            } else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
                sb.append('_');
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    public static  String addZeroLeftForNum(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
            sb = new StringBuffer();
            sb.append("0").append(str);// 左补0
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

    public static  String addZeroRightForNum(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
            sb = new StringBuffer();
            //sb.append("0").append(str);// 左补0
             sb.append(str).append("0");//右补0
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

}
