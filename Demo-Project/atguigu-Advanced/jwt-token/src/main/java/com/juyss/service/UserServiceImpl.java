package com.juyss.service;

import com.juyss.mapper.UserMapper;
import com.juyss.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserServiceImpl
 * @Desc:
 * @package com.juyss.service
 * @project atguigu-Advanced
 * @date 2020/12/6 18:32
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    /**
     * 根据name查询
     *
     * @param name 用户名
     * @return User
     */
    @Override
    public User getUser(String name) {
        return userMapper.selectByName(name);
    }
}
