import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Jtest03
 * @Desc:  获取数据库连接
 *          MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
 *          从properties文件读取配置信息
 * @package PACKAGE_NAME
 * @project JDBC
 * @date 2020/6/2 16:25
 */
public class Jtest01 {
    public static void main(String[] args) throws Exception {

        //获取输入流(两种方法)
        InputStream is = Jtest01.class.getClassLoader().getResourceAsStream("jdbc.properties");
        //main方法中文件路径为Project下
        FileInputStream fis = new FileInputStream("TEST02\\src\\jdbc.properties");

        Properties prop = new Properties();
        prop.load(fis);

        //获取配置信息
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String url = prop.getProperty("url");
        String driverClass = prop.getProperty("driverClass");

        //注册驱动
        Class.forName(driverClass);

        //获得Connection对象,建立与数据库的连接
        Connection connection1 = DriverManager.getConnection(url, user, password);
        Connection connection2 = DriverManager.getConnection(url, prop);

        System.out.println(connection1);
        System.out.println(connection2);
    }
}
