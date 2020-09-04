package com.juyss.service.impl;

import com.juyss.dao.UserDao;
import com.juyss.dao.impl.UserDaoImpl;
import com.juyss.pojo.User;
import com.juyss.service.UserService;
import com.juyss.util.MyDbutils;

import java.sql.Connection;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserServiceImpl
 * @Desc:
 * @package com.juyss.service.impl
 * @project BookStore
 * @date 2020/8/8 9:37
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    /**
     * 用户登录
     *
     * @param username 传入的用户名
     * @return 根据用户名查询到的用户
     */
    @Override
    public User login(String username) {
        Connection conn = null;
        User user = null;
        try {
            conn = MyDbutils.getConnection();
            user = userDao.getUserByUsername(conn, username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyDbutils.closeConnection(conn);
            return user;
        }
    }

    /**
     * 用户注册
     *
     * @param user 注册的用户
     * @return 是否注册成功
     */
    @Override
    public Boolean signin(User user) {
        Boolean flag = false;
        Connection conn = null;
        try {
            conn = MyDbutils.getConnection();
            int i = userDao.insertUser(conn, user);
            if (i == 1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyDbutils.closeConnection(conn);
            return flag;
        }

    }
}
