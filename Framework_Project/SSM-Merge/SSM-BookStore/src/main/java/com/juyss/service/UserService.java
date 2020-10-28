package com.juyss.service;

import com.juyss.pojo.User;

import java.sql.SQLException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserService
 * @Desc: 用户service接口
 * @package com.juyss.service
 * @project SSM-Merge
 * @date 2020/9/19 17:56
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param username 传入的用户名
     * @return 根据用户名查询到的用户
     */
    User login(String username) throws SQLException;

    /**
     * 用户注册
     *
     * @param user 注册的用户
     * @return 是否注册成功
     */
    boolean signin(User user) throws SQLException;

}
