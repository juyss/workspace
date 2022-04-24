package com.juyss.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MyDbutils
 * @Desc: 数据库连接工具类
 * @package com.juyss.util
 * @project BookStore
 * @date 2020/8/7 14:32
 */
public class MyDbutils {

    private static DataSource dataSource;

    /**
     * 用于获取配置信息
     */
    static {
        try {
            InputStream is = MyDbutils.class.getClassLoader().getResourceAsStream("DatabaseInfo.properties");
            System.out.println("InputStream-->" + is);
            Properties prop = new Properties();
            prop.load(is);
            System.out.println("Properties-->" + prop);
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return Connection conn
     * @throws Exception SQLException
     */
    public static Connection getConnection() throws Exception {
        Connection conn = dataSource.getConnection();
        System.out.println("数据库连接-->"+conn);
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn Connection
     */
    public static void closeConnection(Connection conn){
        DbUtils.closeQuietly(conn);
        System.out.println("数据库连接关闭");
    }

    /**
     * 关闭资源
     * @param conn Connection
     * @param stmt Statement
     * @param rs ResultSet
     */
    public static void CloseResources(Connection conn, Statement stmt , ResultSet rs){
        DbUtils.closeQuietly(conn, stmt, rs);
        System.out.println("资源关闭");
    }
}
