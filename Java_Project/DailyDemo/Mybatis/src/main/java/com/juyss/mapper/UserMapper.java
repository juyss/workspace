package com.juyss.mapper;

import com.juyss.pojo.User;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserMapper
 * @Desc:
 * @package com.juyss.mapper
 * @project DailyDemo
 * @date 2020/8/30 16:20
 */
public interface UserMapper {

    /**
     * 获取User表所有数据
     * @return User数据集合
     */
    List<User> getUserList();

    /**
     * 获取指定id的User
     * @param name 用户名
     * @return 查询到的User数据
     */
    User getUserByName(String name);

    /**
     * 插入一条数据
     * @param user 要插入的User对象实例
     * @return 受影响的行数
     */
    int insertUser(User user);

    /**
     * 更新一条数据
     * @param user 要更新的User对象实例
     * @return 受影响的行数
     */
    int updateUser(User user);

    /**
     * 删除一条数据
     * @param id 要删除的数据的id
     * @return 受影响的行数
     */
    int deleteUserById(Integer id);
}
