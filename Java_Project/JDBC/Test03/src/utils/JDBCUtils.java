package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: JDBCUtils
 * @Desc:  JDBC工具类
 * @package utils
 * @project JDBC
 * @date 2020/6/2 21:20
 */
public class JDBCUtils {

    /**
     * @Desc: 获取数据库连接
     * @return  Connection connection
     * @throws Exception e
     */
    public static Connection getConnection() throws Exception {

        //默认路径为当前Class所在Module的src目录下
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        //在main方法中时,默认路径为为Project下
        //在@Test单元测试方法中路径为当前Class所在Module目录下
        FileInputStream is = new FileInputStream("Test03\\jdbc.properties");

        Properties prop = new Properties();
        prop.load(is);
        String url = prop.getProperty("url");
        String driverClass = prop.getProperty("driverClass");

        Class.forName(driverClass);

        Connection connection = DriverManager.getConnection(url, prop);
        return connection;
    }

    /**
     * @Desc: 关闭资源操作
     * @param fis FileInputStream
     * @param st Statement
     * @param conn Connection
     */
    public static void closeResource(FileInputStream fis, Statement st,Connection conn){
        try {
            if (fis!=null)
                fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (conn!=null)
                conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (st!=null)
                st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * closeResource重载方法
     * @param st
     * @param conn
     */
    public static void closeResource(Statement st,Connection conn){
        try {
            if (conn!=null)
                conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (st!=null)
                st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
