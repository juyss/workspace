package com.juyss.service.impl;

import com.juyss.mapper.impl.UserMapperImpl;
import com.juyss.pojo.User;
import com.juyss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserServiceImpl
 * @Desc:
 * @package com.juyss.service.impl
 * @project Spring-Mybatis-Merge
 * @date 2020/9/10 22:47
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private UserMapperImpl userMapper;

    @Autowired
    public UserServiceImpl(UserMapperImpl userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 用户的一些操作
     */
    @Transactional
    @Override
    public void userOperation() {
        User jerry01 = new User(7, "Jerry", "123456789");
        User jerry02 = new User(7, "Jerry", "987654321");
        System.out.println(userMapper);

        userMapper.insertUser(jerry01);
//        int i = 2/0;
        userMapper.updateUser(jerry02);
        userMapper.deleteUserById(7);
    }

}
