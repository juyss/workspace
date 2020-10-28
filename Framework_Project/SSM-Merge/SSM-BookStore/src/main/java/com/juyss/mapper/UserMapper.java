package com.juyss.mapper;

import com.juyss.pojo.User;

import java.sql.SQLException;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserMapper
 * @Desc: 提供与用户相关的数据库操作接口
 * @package com.juyss.mapper
 * @project SSM-Merge
 * @date 2020/9/17 20:04
 */
public interface UserMapper {

    /**
     * 根据传入的用户名查询数据
     *
     * @param username 指定的用户名
     * @return User 查询到的用户
     */
    User getUserByUsername(String username) throws SQLException;

    /**
     * 将用户信息保存到数据库中
     *
     * @param user 需要保存的用户信息
     * @return int 受影响的行数
     */
    int insertUser(User user) throws SQLException;

}
