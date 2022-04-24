import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Way03
 * @Desc:  使用DriverManager获取数据库连接
 * @package PACKAGE_NAME
 * @project JDBC_Example
 * @date 2020/7/2 18:34
 */
public class Way03 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        //配置信息
        String url = "jdbc:mysql://localhost:3306/daily?useSSL=true&serverTimezone=UTC";
        String user = "user01";
        String password = "102850";

        //获取Driver实例
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();


        //注册驱动
        DriverManager.registerDriver(driver);

        //获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);

        //关闭连接
        conn.close();
    }
}
