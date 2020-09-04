package com.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MyDbutils
 * @Desc:  数据库工具类,获取数据库连接和关闭资源
 * @package com.util
 * @project JDBC_Example
 * @date 2020/7/4 19:58
 */
public class MyDbutils {

    private static Properties prop;

    /**
     * 静态代码块,用于在类加载的时候,加载配置文件信息.节省系统资源.
     */
    static {
        InputStream is = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            prop = new Properties();
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用`druid数据库连接池`获取数据库连接
     * @return Connection
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        DataSource ds = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = ds.getConnection();
        return conn;
    }

    /**
     * 关闭资源
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void closeResource(Connection conn, Statement stmt, ResultSet rs){
        DbUtils.closeQuietly(conn,stmt ,rs );
    }

}
