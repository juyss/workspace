package com.juyss.mapper;

import com.juyss.pojo.User;
import com.juyss.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserMapperTest
 * @Desc:  UserDao测试类
 * @package com.juyss.dao
 * @project Mybatis
 * @date 2020/8/25 19:34
 */
public class UserMapperTest {

    @Test
    public void getUserListTest(){
        SqlSession sqlSession = null;
        try {
            //获取SqlSession对象
            sqlSession = MybatisUtils.getSqlSession();

            //方式一:getMapper()
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
        } finally {
            //关闭SqlSession
            if (sqlSession!=null){
                sqlSession.close();
                System.out.println("sqlSession关闭了");
            }
        }
    }

    @Test
    public void getUserByIdTest(){
        SqlSession sqlSession = null;
        try {
            //获取SqlSession对象
            sqlSession = MybatisUtils.getSqlSession();

            //方式一:getMapper()
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user01 = mapper.getUserById(1);
            User user02 = mapper.getUserById(2);
            User user03 = mapper.getUserById(3);
            System.out.println(user01);
            System.out.println(user02);
            System.out.println(user03);
        } finally {
            //关闭SqlSession
            if (sqlSession!=null){
                sqlSession.close();
                System.out.println("sqlSession关闭了");
            }
        }
    }

    @Test
    public void insertUserTest(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int update = mapper.insertUser(new User(4, "哈哈", "102850"));
            System.out.println(update);
            sqlSession.commit();
        } finally {
            if (sqlSession!=null){
                System.out.println("连接关闭");
                sqlSession.close();
            }
        }

    }
}
