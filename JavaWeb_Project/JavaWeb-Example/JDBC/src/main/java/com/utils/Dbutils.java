package com.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DbUtils
 * @Desc:  数据库操作工具类
 * @package com.utils
 * @project JavaWeb-Example
 * @date 2020/6/27 21:19
 */
public class Dbutils {
    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() {
        InputStream is = null;
        Connection conn = null;
        try {
//            is = Dbutils.class.getClassLoader().getResourceAsStream("resources\\druid.properties");
            is = Dbutils.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties prop = new Properties();
            prop.load(is);
            DataSource source = DruidDataSourceFactory.createDataSource(prop);
            conn = source.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeResource(Connection conn) {
        if (conn != null)
            DbUtils.closeQuietly(conn);
        System.out.println(conn.getClass()+"连接关闭");
    }

//    @Test
//    public void Test0(){
//        Connection conn = getConnection();
//        System.out.println(conn);
//        closeResource(conn);
//    }
}
