package com.juyss.service;

import com.juyss.pojo.User;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserService
 * @Desc:
 * @package com.juyss.service
 * @project atguigu-Advanced
 * @date 2020/12/6 18:32
 */
public interface UserService {

    /**
     * 根据name查询
     * @param name 用户名
     * @return User
     */
    User getUser(String name);
}
