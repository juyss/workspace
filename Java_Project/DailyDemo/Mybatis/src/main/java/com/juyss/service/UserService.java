package com.juyss.service;

import com.juyss.pojo.User;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserService
 * @Desc:
 * @package com.juyss.service
 * @project DailyDemo
 * @date 2020/8/30 16:21
 */
public interface UserService {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User login(String username);
}
