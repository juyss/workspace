package com.itheima.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.ModuleDao;
import com.itheima.dao.system.UserDao;
import com.itheima.domain.system.Module;
import com.itheima.domain.system.User;
import com.itheima.factory.MapperFactory;
import com.itheima.service.system.UserService;
import com.itheima.utils.MD5Util;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

/**
 * @author zxq
 * @create 2020-08-26 8:56
 */
public class UserServiceImpl implements UserService {
    @Override
    public void save(User user) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            //设置用户id
            String s = UUID.randomUUID().toString();
            user.setId(s);

            //密码必须经过加密处理MD5加密
            user.setPassword(MD5Util.md5(user.getPassword()));

            //3.调用接口中的方法
            userDao.save(user);

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //回滚事务
            TransactionUtil.rollback(sqlSession);
            //抛出异常
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void delete(User user) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            //3.调用接口中的方法
            userDao.delete(user);

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //回滚事务
            TransactionUtil.rollback(sqlSession);
            //抛出异常
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(User user) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            //3.调用接口中的方法
            userDao.update(user);

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //回滚事务
            TransactionUtil.rollback(sqlSession);
            //抛出异常
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User findById(String id) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            //3.调用接口中的方法
            return userDao.findById(id);

        } catch (Exception e) {

            //抛出异常
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<User> findAll() {

        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            //3.调用接口中的方法
            return userDao.findAll();

        } catch (Exception e) {

            //抛出异常
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {

        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            //3.调用接口中的方法
            PageHelper.startPage(page, size);
            List<User> all = userDao.findAll();
            PageInfo pageInfo = new PageInfo(all);

            return pageInfo;
        } catch (Exception e) {

            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void updateRole(String userId, String[] roleIds) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            //3.调用接口中的方法
            //3.1先去掉该用户的所有角色
            userDao.deleteRole(userId);

            //3.2再绑定新的角色
            for (String roleId : roleIds) {
                userDao.updateRole(userId, roleId);
            }

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //回滚事务
            TransactionUtil.rollback(sqlSession);
            //抛出异常
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User login(String email, String password) {

        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口代理实现类;
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            //对密码加密
            password = MD5Util.md5(password);

            //3.调用dao层的方法
            return userDao.findByEmailAndPwd(email, password);

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {

            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Module> findModuleById(String id) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);
            //3.调用Dao层操作
            return moduleDao.findModuleByUserId(id);
        }catch (Exception e){
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
