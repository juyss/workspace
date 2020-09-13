package com.juyss.mapper.impl;

import com.juyss.mapper.UserMapper;
import com.juyss.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserMapperImpl
 * @Desc:
 * @package com.juyss.mapper.impl
 * @project Spring-Mybatis-Merge
 * @date 2020/9/10 19:41
 */
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {
    /**
     * 获取User表所有数据
     *
     * @return User数据集合
     */
    @Override
    public List<User> getUserList() {
        return getSqlSession().getMapper(UserMapper.class).getUserList();
    }

    /**
     * 获取指定id的User
     *
     * @param id 要获取的User
     * @return 查询到的User数据
     */
    @Override
    public User getUserById(Integer id) {
        return getSqlSession().getMapper(UserMapper.class).getUserById(id);
    }

    /**
     * 插入一条数据
     *
     * @param user 要插入的User对象实例
     * @return 受影响的行数
     */
    @Override
    public int insertUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).insertUser(user);
    }

    /**
     * 更新一条数据
     *
     * @param user 要更新的User对象实例
     * @return 受影响的行数
     */
    @Override
    public int updateUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).updateUser(user);
    }

    /**
     * 删除一条数据
     *
     * @param id 要删除的数据的id
     * @return 受影响的行数
     */
    @Override
    public int deleteUserById(Integer id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUserById(id);
    }
}
