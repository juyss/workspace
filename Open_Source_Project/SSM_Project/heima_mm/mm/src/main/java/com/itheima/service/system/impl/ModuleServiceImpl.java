package com.itheima.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.ModuleDao;
import com.itheima.domain.system.Module;
import com.itheima.factory.MapperFactory;
import com.itheima.service.system.ModuleService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * @author zxq
 * @create 2020-08-29 17:21
 */
public class ModuleServiceImpl implements ModuleService {
    @Override
    public void save(Module module) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);

            //3.调用接口的方法
            moduleDao.save(module);

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
    public void delete(Module module) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);

            //3.调用接口的方法
            moduleDao.delete(module);

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
    public void update(Module module) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);

            //3.调用接口的方法
            moduleDao.update(module);

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
    public Module findById(String id) {

        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);

            //3.调用接口的方法
            return moduleDao.findById(id);


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
    public List<Module> findAll() {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);

            //3.调用接口的方法
            return moduleDao.findAll();


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
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);

            //3.调用接口的方法
            PageHelper.startPage(page,size);

            List<Module> all = moduleDao.findAll();

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
    public List<Map> findAuthorDataByRoleId(String roleId) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);

            //3.调用接口的方法
            return moduleDao.findAuthorDataByRoleId(roleId);


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
