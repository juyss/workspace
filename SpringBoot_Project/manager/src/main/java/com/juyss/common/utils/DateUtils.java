package com.juyss.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: DateUtils
 * @Desc: 日期处理
 * @package com.juyss.common.utils
 * @project manager
 * @date 2021/1/12 19:01
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式(yyyyMMdd)
     */
    public final static String DATEPATTERN = "yyyyMMdd";

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
}
