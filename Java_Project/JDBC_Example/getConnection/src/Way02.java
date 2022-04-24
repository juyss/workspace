import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Way02
 * @Desc:  使用反射创建对应数据库Driver实现类的对象,消除第三方API
 * @package PACKAGE_NAME
 * @project JDBC_Example
 * @date 2020/7/2 18:22
 */
public class Way02 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        //通过反射获取Driver实例
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //统一资源定位符
        String url = "jdbc:mysql://localhost:3306/daily?useSSL=true&serverTimezone=UTC";

        //配置信息
        Properties prop = new Properties();
        prop.setProperty("user","user01");
        prop.setProperty("password","102850");

        //获取连接
        Connection conn = driver.connect(url, prop);
        System.out.println(conn);

        conn.close();
    }
}
