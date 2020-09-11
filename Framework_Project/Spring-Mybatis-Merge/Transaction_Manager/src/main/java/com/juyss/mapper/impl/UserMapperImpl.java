package com.juyss.mapper.impl;

import com.juyss.mapper.UserMapper;
import com.juyss.pojo.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserMapperImpl
 * @Desc: 继承SqlSessionDaoSupport,通过getSqlSession()方法获取sqlSession
 * @package com.juyss.mapper.impl
 * @project Spring-Mybatis-Merge
 * @date 2020/9/10 19:41
 */
@Repository("userMapper")
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {


    /*
     * 从 SqlSessionDaoSupport 这个类的源码中可以看出，原因是mybatis-spring-1.2.0中取消了自动注入SqlSessionFactory 和 SqlSessionTemplate
     * 所以这里手动配置自动注入其中一个属性
     */
    @Override
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

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
