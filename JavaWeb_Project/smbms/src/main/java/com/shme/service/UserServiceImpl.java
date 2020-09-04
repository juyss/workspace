package com.shme.service;

import com.mysql.cj.util.StringUtils;
import com.shme.dao.UserDAO;
import com.shme.pojo.User;
import com.shme.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserServiceImpl
 * @Desc: 用户服务层实现
 * @package com.shme.service
 * @project smbms
 * @date 2020/6/25 17:18
 */
public class UserServiceImpl implements UserService {

    /**
     * @param userCode 用户名(英文缩写)
     * @return User 数据库中查到的用户
     * @Desc 通过传入的用户名查询数据库中的信息
     */
    @Override
    public User login(String userCode) {
        SqlSession sqlSession = null;
        User user = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            UserDAO mapper = sqlSession.getMapper(UserDAO.class);
            user = mapper.getLoginUser(userCode);
            System.out.println("查到的数据:UserServiceImpl:user-->"+user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return user;
    }

    /**
     * 根据用户ID修改登录密码
     *
     * @param newPassword 新密码
     * @param id          用户ID
     * @return Boolean 是否修改成功
     */
    @Override
    public Boolean updatePassword(String newPassword, int id) {
        boolean flag = false;
        SqlSession sqlSession = null;
        try {
            flag = false;
            sqlSession = MybatisUtils.getSqlSession();
            UserDAO mapper = sqlSession.getMapper(UserDAO.class);
            int i = mapper.updatePassword(newPassword, id);
            if (i == 1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return flag;
    }

    /**
     * 获取符合条件的用户数量
     *
     * @param userName 用户名
     * @param userRole 用户角色
     * @return int 符合条件的用户个数
     */
    @Override
    public int getUserCount(String userName, int userRole) {
        int count = 0;
        if (userName!=null && !"".equals(userName)){
            userName = "%"+userName+"%";
        }else {
            userName = null;
        }
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            UserDAO mapper = sqlSession.getMapper(UserDAO.class);
            count = mapper.getUserCount(userName, userRole);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return count;
    }

    /**
     * 获取指定页码的用户列表
     *
     * @param userName        String 用户名
     * @param userRole        int 用户角色
     * @param currentPageCode int 目标页码
     * @param pageSize        int 页面显示的用户数量
     * @return List<User> 符合条件的用户集合
     */
    @Override
    public List<User> getUserList(String userName, int userRole, int currentPageCode, int pageSize) {
        //如果有用户名的限制,让用户名设为null,
        if (userName!=null && !"".equals(userName)){
            userName = "%"+userName+"%";
        }else {
            userName = null;
        }
        //根据当前页码计算起始索引
        int offset = (currentPageCode - 1) * pageSize;

        SqlSession sqlSession = null;
        List<User> userList = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            UserDAO mapper = sqlSession.getMapper(UserDAO.class);
            userList = mapper.getUserList(userName, userRole, offset, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return userList;
    }
}
