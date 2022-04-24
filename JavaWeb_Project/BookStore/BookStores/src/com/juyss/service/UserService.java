package com.juyss.service;

import com.juyss.pojo.User;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserService
 * @Desc: 用户service层接口
 * @package com.juyss.service
 * @project BookStore
 * @date 2020/8/7 21:12
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param username 传入的用户名
     * @return 根据用户名查询到的用户
     */
    User login(String username);

    /**
     * 用户注册
     *
     * @param user 注册的用户
     * @return 是否注册成功
     */
    Boolean signin(User user);
}
