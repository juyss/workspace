import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Way05
 * @Desc:  从src目录下读取配置文件信息,使用DriverManger获取数据库连接
 * @package PACKAGE_NAME
 * @project JDBC_Example
 * @date 2020/7/2 22:56
 */
public class Way05 {
    public static void main(String[] args) {
        Connection conn = null;
        try {

//            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("resource\\db.properties");
//            InputStream is = Way05.class.getClassLoader().getResourceAsStream("..\\resource\\db.properties");
//            InputStream is = Way05.class.getClassLoader().getResourceAsStream("resource\\db.properties");

            //读取src下的文件
            InputStream is = Way05.class.getClassLoader().getResourceAsStream("resource\\db.properties");
            Properties prop = new Properties();
            prop.load(is);

            String driverClass = prop.getProperty("driverClass");
            String url = prop.getProperty("url");

            Class.forName(driverClass);

            conn = DriverManager.getConnection(url, prop);

            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
