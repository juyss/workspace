package com.github.tangyi.core.common;


import com.github.tangyi.core.common.util.DateUtils;

import java.util.Date;

/**
 * 基础常量
 *
 * @author hedongzhou
 * @date 2018/06/13
 */
public abstract class BaseConstants {

    /**
     * asterisk
     */
    public static final String ASTERISK = "*";

    /**
     * separator dot
     */
    public static final String SEPARATOR_DOT = ".";

    /**
     * separator comma
     */
    public static final String SEPARATOR_COMMA = ",";

    /**
     * separator colon
     */
    public static final String SEPARATOR_COLON = ":";

    /**
     * separator underline
     */
    public static final String SEPARATOR_UNDERLINE = "_";

    /**
     * separator hyphen
     */
    public static final String SEPARATOR_HYPHEN = "-";

    /**
     * separator bitwise
     */
    public static final String SEPARATOR_BITWISE = "|";

    /**
     * separator blank
     */
    public static final String SEPARATOR_BLANK = " ";

    /**
     * default pattern time
     */
    public static final String DEFAULT_PATTERN_TIME = "HH:mm:ss";

    /**
     * default pattern date
     */
    public static final String DEFAULT_PATTERN_DATE = "yyyy-MM-dd";

    /**
     * default pattern date time
     */
    public static final String DEFAULT_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * default pattern date time
     */
    public static final String NONDEFAULT_PATTERN_DATETIME = "yyyyMMddHHmmss";

    /**
     * default encoding
     */
    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 最大时间
     */
    public static final Date MAX_TIME = DateUtils.parseDate("2100-01-01");


}
