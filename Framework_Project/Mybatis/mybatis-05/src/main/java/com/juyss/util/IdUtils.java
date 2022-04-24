package com.juyss.util;

import java.util.UUID;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: IdUtils
 * @Desc: 获取UUID字符串
 * @package com.juyss.util
 * @project Mybatis
 * @date 2020/8/28 16:25
 */
public class IdUtils {

    public static String getUUID(){
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        return id;
    }
}
