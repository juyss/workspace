package com.juyss.service;

import com.juyss.pojo.User;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: LoginService
 * @Desc: 业务层
 * @package com.juyss.service
 * @project Shiro
 * @date 2020/12/31 20:43
 */
public interface LoginService {

    /**
     * 根据用户名获取用户
     * @param getMapByName 用户名
     * @return 查询到的用户
     */
    User getUserByName(String getMapByName);
}
