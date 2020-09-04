package com.juyss.test;

import com.juyss.dao.UserDao;
import com.juyss.dao.impl.UserDaoImpl;
import com.juyss.pojo.User;
import com.juyss.util.MyDbutils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserDaoImplTest
 * @Desc: UserDaoImpl测试类, 测试方法功能是否正常
 * @package com.juyss.test
 * @project BookStore
 * @date 2020/8/7 20:39
 */
public class UserDaoImplTest {

    @Test
    public void getUserByUsername() {
        Connection conn = null;
        User user = null;
        try {
            conn = MyDbutils.getConnection();
            UserDao userDao = new UserDaoImpl();
            user = userDao.getUserByUsername(conn, "zhangsan");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyDbutils.closeConnection(conn);
            System.out.println(user);
        }
    }

    @Test
    public void insertUser() {
        Connection conn = null;
        int update = 0;
        try {
            User user = new User(null, "zhangsan", "111111", "zhangsan@163.com");
            conn = MyDbutils.getConnection();
            UserDao userDao = new UserDaoImpl();
            update = userDao.insertUser(conn, user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyDbutils.closeConnection(conn);
            System.out.println(update);
        }
    }
}