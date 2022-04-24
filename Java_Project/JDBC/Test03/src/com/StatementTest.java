package com;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: com.StatementTest
 * @Desc:  Statement存在拼串和SQL注入问题(Where条件恒成立的情况下会发生)
 *          所以使用PreparedStatement,来对数据表进行'增改删'和'查'(有无返回值).
 * @package PACKAGE_NAME
 * @project JDBC
 * @date 2020/6/2 17:37
 */
public class StatementTest {
    public static void main(String[] args) {

    }

    //向数据库表中插入一条数据
    @Test
    public void insertTest(){
        FileInputStream fis = null;
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            //获取连接信息
            fis = new FileInputStream("jdbc.properties");
            Properties prop = new Properties();
            prop.load(fis);
            String url = prop.getProperty("url");
            String driverClass = prop.getProperty("driverClass");

            //加载驱动
            Class.forName(driverClass);

            //获取连接对象
            conn = DriverManager.getConnection(url,prop);

            Date date = new Date(2015,11,30);
            Person p1 = new Person(1, "Eclipse", 18, "男", date);
            Person p2 = new Person();

            String sql = "delete from `person` where `name`=?";
            //String sql = "insert into person (`id`,`name`, `age`,`gender`,`birthday`) values (null,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, p1.getName());
//            pst.setInt(2, p1.getAge());
//            pst.setString(3, p1.getGender());
//            pst.setDate(4, p1.getBirthday());
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
                if (pst!=null)
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
