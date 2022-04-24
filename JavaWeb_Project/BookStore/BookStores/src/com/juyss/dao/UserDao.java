package com.juyss.dao;

import com.juyss.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserDao
 * @Desc: 用户数据库访问接口
 * @package com.juyss.dao
 * @project BookStore
 * @date 2020/8/7 19:05
 */
public interface UserDao {

    /**
     * 根据传入的用户名查询数据
     *
     * @param conn 数据库连接
     * @param username 指定的用户名
     * @return User 查询到的用户
     */
    User getUserByUsername(Connection conn, String username) throws SQLException;

    /**
     * 将用户信息保存到数据库中
     *
     * @param conn 数据库连接
     * @param user 需要保存的用户信息
     * @return int 受影响的行数
     */
    int insertUser(Connection conn,User user) throws SQLException;
}
