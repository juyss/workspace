import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Connector
 * @Desc:
 * @package PACKAGE_NAME
 * @project JDBC
 * @date 2020/6/2 17:09
 */
public class Connector {
    public static void main(String[] args) throws Exception {
        Driver driver = new Driver();
        String url = "jdbc:mysql://localhost:3306/dailytext?useSSL=false&serverTimezone=UTC";
        Properties prop = new Properties();
        prop.setProperty("user", "user01");
        prop.setProperty("password", "102850");
        Connection connect = driver.connect(url, prop);
        System.out.println(connect);
    }
}
