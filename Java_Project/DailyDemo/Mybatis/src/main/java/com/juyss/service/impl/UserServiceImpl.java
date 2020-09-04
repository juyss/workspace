package com.juyss.service.impl;

import com.juyss.mapper.UserMapper;
import com.juyss.pojo.User;
import com.juyss.service.UserService;
import com.juyss.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserServiceImpl
 * @Desc:
 * @package com.juyss.service.impl
 * @project DailyDemo
 * @date 2020/8/30 16:22
 */
public class UserServiceImpl implements UserService {

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    @Override
    public User login(String username) {
        SqlSession sqlSession = null;
        User user = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.getUserByName(username);
            System.out.println("UserService:查到的用户-->"+user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return user;
    }
}
