package com.juyss.mapper;

import com.juyss.pojo.User;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserMapper
 * @Desc:
 * @package com.juyss.mapper
 * @project Spring-Mybatis-Merge
 * @date 2020/9/10 18:42
 */
public interface UserMapper {
    /**
     * 获取User表所有数据
     * @return User数据集合
     */
    List<User> getUserList();

    /**
     * 获取指定id的User
     * @param id 要获取的User
     * @return 查询到的User数据
     */
    User getUserById(Integer id);

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
