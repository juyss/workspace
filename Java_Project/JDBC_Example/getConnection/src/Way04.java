import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Way04
 * @Desc:  com.mysql.cj.jdbc.Driver类内部已经注册,只需加载类即可完成Driver
 * @package PACKAGE_NAME
 * @project JDBC_Example
 * @date 2020/7/2 21:18
 */
public class Way04 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //配置信息
        String url = "jdbc:mysql://localhost:3306/daily?useSSL=true&serverTimezone=UTC";
        String user = "user01";
        String password = "102850";

        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);

        conn.close();
    }
}
