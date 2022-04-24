package com.github.tangyi.core.common.util;

import com.github.tangyi.core.common.BaseConstants;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码工具
 *
 * @author hedongzhou
 * @date 2018/06/14
 */
public class CoderUtils {

    /**
     * UTF-8编码
     *
     * @param str
     * @return
     */
    public static final String encode(String str) {
        return encode(str, BaseConstants.DEFAULT_ENCODING);
    }

    /**
     * 编码
     *
     * @param str
     * @param charset
     * @return
     */
    public static final String encode(String str, String charset) {
        try {
            return URLEncoder.encode(str, charset);
        } catch (Exception e) {
            LogUtils.error(e);

            return str;
        }
    }

    /**
     * UTF-8解码
     *
     * @param str
     * @return
     */
    public static final String decode(String str) {
        return decode(str, BaseConstants.DEFAULT_ENCODING);
    }

    /**
     * 解码
     *
     * @param str
     * @param charset
     * @return
     */
    public static final String decode(String str, String charset) {
        try {
            return URLDecoder.decode(str, charset);
        } catch (Exception e) {
            LogUtils.error(e);

            return str;
        }
    }

}
