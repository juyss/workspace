package com.juyss.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: GetConnection
 * @Desc: 去获取数据库连接的基本方式的最佳方案, 从配置文件加载信息, 使用DriverManager获取连接
 * @package com.juyss.connection
 * @project DailyDemo
 * @date 2020/7/3 8:55
 */
public class GetConnection {

    public static void main(String[] args) {

        Connection conn = null;
        try {

            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("resource\\db.properties");
            Properties info = new Properties();
            info.load(is);

            String url = info.getProperty("url");
            String driverClass = info.getProperty("driverClass");

            Class.forName(driverClass);

            conn = DriverManager.getConnection(url, info);
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
   