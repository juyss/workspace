package com.juyss.service.impl;

import com.juyss.mapper.UserMapper;
import com.juyss.pojo.User;
import com.juyss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserServiceImpl
 * @Desc: UserService接口实现类
 * @package com.juyss.service.impl
 * @project SSM-Merge
 * @date 2020/9/19 18:26
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 用户登录
     *
     * @param username 传入的用户名
     * @return 根据用户名查询到的用户
     */
    @Override
    public User login(String username) throws SQLException {
        return userMapper.getUserByUsername(username);
    }

    /**
     * 用户注册
     *
     * @param user 注册的用户
     * @return 是否注册成功
     */
    @Override
    public boolean signin(User user) throws SQLException {
        boolean flag = false;
        int i = userMapper.insertUser(user);
        if (i == 1) {
            flag=true;
        }
        return flag;
    }
}
