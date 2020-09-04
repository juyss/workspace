package com.juyss.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MyDbutils
 * @Desc:  自定义数据库工具类
 * @package com.juyss.util
 * @project smbms_practice
 * @date 2020/7/11 19:04
 */
public class MyDbutils {

    /**
     * 保存配置文件信息
     */
    private static Properties prop;

    /**
     * 载入配置文件信息
     */
    static {
        InputStream is = MyDbutils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return Connection
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        DataSource ds = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = ds.getConnection();
        return conn;
    }
}
