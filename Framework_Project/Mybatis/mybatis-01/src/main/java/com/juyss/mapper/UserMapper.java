package com.juyss.mapper;

import com.juyss.pojo.User;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserMapper
 * @Desc:  User数据库访问对象
 * @package com.juyss.dao
 * @project Mybatis
 * @date 2020/8/25 18:34
 */
public interface UserMapper {

    /**
     * 获取用户列表
     * @return List<User> 用户列表
     */
    List<User> getUserList();

    /**
     * 根据id查询用户
     * @param id Integer 用户ID
     * @return User 查询到的用户
     */
    User getUserById(int id);

    /**
     * 添加一个用户数据
     * @param user
     * @return
     */
    int insertUser(User user);
}
