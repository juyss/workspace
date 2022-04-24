package com.juyss.dao.impl;

import com.juyss.dao.BaseDao;
import com.juyss.dao.UserDao;
import com.juyss.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserDaoImpl
 * @Desc:  用户数据库访问接口实现类
 * @package com.juyss.dao.impl
 * @project BookStore
 * @date 2020/8/7 19:19
 */
public class UserDaoImpl extends BaseDao implements UserDao {


    /**
     * 根据传入的用户名查询数据
     *
     * @param conn     数据库连接
     * @param username 指定的用户名
     * @return User 查询到的用户
     */
    @Override
    public User getUserByUsername(Connection conn, String username) throws SQLException {
        String sql = "select * from `t_user` where `username`=? ";
        User user = query(User.class, conn, sql, username);
        return user;
    }

    /**
     * 将用户信息保存到数据库中
     *
     * @param conn 数据库连接
     * @param user 需要保存的用户信息
     * @return int 受影响的行数
     */
    @Override
    public int insertUser(Connection conn, User user) throws SQLException {
        String sql = "insert into `t_user` (`username`,`password`,`email`) values (?,?,?)";
        int update = update(conn, sql, user.getUsername(), user.getPassword(), user.getEmail());
        return update;
    }
}
