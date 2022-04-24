package com.juyss.service;

import com.juyss.bean.TAdmin;

import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserService
 * @Desc: 用户业务层接口
 * @package com.juyss.service
 * @project atguigu-CrowdFunding
 * @date 2020/10/11 18:10
 */
public interface UserService {

    /**
     * 根据条件查询用户
     * @param map 条件封装载体
     * @return 符合条件的用户
     */
    TAdmin getUserByMap(Map<String, Object> map);


}
