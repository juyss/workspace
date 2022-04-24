package com.juyss.util;

import org.junit.Test;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Md5Test
 * @Desc: 测试MD5加密工具类
 * @package com.juyss.util
 * @project atguigu-CrowdFunding
 * @date 2020/10/11 23:50
 */
public class Md5Test {

    @Test
    public void MD5Test(){
        String digest = MD5Util.digest("102850");
        System.out.println(digest);
    }

}
