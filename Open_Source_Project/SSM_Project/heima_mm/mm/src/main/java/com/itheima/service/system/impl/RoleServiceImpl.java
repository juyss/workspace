package com.itheima.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.RoleDao;
import com.itheima.domain.system.Role;
import com.itheima.factory.MapperFactory;
import com.itheima.service.system.RoleService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author zxq
 * @create 2020-08-29 15:34
 */
public class RoleServiceImpl implements RoleService {
    @Override
    public void save(Role role) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);

            //3.调用接口的方法
            roleDao.save(role);

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //回滚事务
            TransactionUtil.rollback(sqlSession);
            //抛出异常
            throw new RuntimeException(e);

        } finally {
            //关闭sqlSession
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void delete(Role role) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);

            //3.调用接口的方法
            roleDao.delete(role);

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //回滚事务
            TransactionUtil.rollback(sqlSession);
            //抛出异常
            throw new RuntimeException(e);

        } finally {
            //关闭sqlSession
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Role role) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);

            //3.调用接口的方法
            roleDao.update(role);

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //回滚事务
            TransactionUtil.rollback(sqlSession);
            //抛出异常
            throw new RuntimeException(e);

        } finally {
            //关闭sqlSession
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Role findById(String id) {

        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);

            //3.调用接口的方法
            return roleDao.findById(id);


        } catch (Exception e) {
            //抛出异常
            throw new RuntimeException(e);

        } finally {
            //关闭sqlSession
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Role> findAll() {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);

            //3.调用接口的方法
            return roleDao.findAll();


        } catch (Exception e) {
            //抛出异常
            throw new RuntimeException(e);

        } finally {
            //关闭sqlSession
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
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);

            //3.调用接口的方法
            PageHelper.startPage(page,size);

            List<Role> all = roleDao.findAll();

            PageInfo pageInfo = new PageInfo(all);

            return  pageInfo;

        } catch (Exception e) {
            //抛出异常
            throw new RuntimeException(e);

        } finally {
            //关闭sqlSession
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateRoleModule(String roleId, String moduleIds) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);

            //3.调用接口的方法
            //3.1取消角色的现有模块
            roleDao.deleteRoleModule(roleId);

            //3.2建立新的关系
            String[] moduleArray = moduleIds.split(",");
            for (String moduleId : moduleArray) {
                roleDao.saveRoleModule(roleId,moduleId);
            }

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //抛出异常
            throw new RuntimeException(e);

        } finally {
            //关闭sqlSession
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Role> findAllRoleByUserId(String userId) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);

            //3.调用接口的方法
            return roleDao.findAllRoleByUserId(userId);


        } catch (Exception e) {
            //抛出异常
            throw new RuntimeException(e);

        } finally {
            //关闭sqlSession
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
