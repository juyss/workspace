import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: FirstWay
 * @Desc:  最基本的获取数据库连接的代码实现
 * @package PACKAGE_NAME
 * @project JDBC_Example
 * @date 2020/7/2 17:52
 */
public class Way01 {
    public static void main(String[] args) throws SQLException {
        //获取对应数据库的Driver实现类对象
        Driver driver = new com.mysql.cj.jdbc.Driver();

        //配置文件
        String url = "jdbc:mysql://localhost:3306/daily?useSSL=true&serverTimezone=UTC";
        Properties prop = new Properties();
        prop.setProperty("user","user01");
        prop.setProperty("password","102850");

        //获得数据库连接
        Connection conn = driver.connect(url, prop);
        System.out.println(conn);
        conn.close();
    }
}
