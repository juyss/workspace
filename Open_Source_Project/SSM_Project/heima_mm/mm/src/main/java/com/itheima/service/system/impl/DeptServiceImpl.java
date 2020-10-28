package com.itheima.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.DeptDao;
import com.itheima.domain.system.Dept;
import com.itheima.factory.MapperFactory;
import com.itheima.service.system.DeptService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

/**
 * @author zxq
 * @create 2020-08-26 8:54
 */
public class DeptServiceImpl implements DeptService {
    @Override
    public void save(Dept dept) {
        SqlSession sqlSession=null;

        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            DeptDao deptDao = MapperFactory.getMapper(sqlSession,DeptDao.class);

            //设置id
            String s = UUID.randomUUID().toString();
            dept.setId(s);

            //3.调用接口的方法
            deptDao.save(dept);

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            //回滚事务
            TransactionUtil.rollback(sqlSession);
            //抛出异常
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Dept dept) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            DeptDao deptDao = MapperFactory.getMapper(sqlSession,DeptDao.class);

            //3.调用接口的方法
            deptDao.delete(dept);

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
    public void update(Dept dept) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            DeptDao deptDao = MapperFactory.getMapper(sqlSession,DeptDao.class);

            //3.调用接口的方法
            deptDao.update(dept);

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
    public Dept findById(String id) {
        SqlSession sqlSession = null;
        Dept dept = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            DeptDao deptDao = MapperFactory.getMapper(sqlSession,DeptDao.class);

            //3.调用接口中的方法
            dept = deptDao.findById(id);
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

        return dept;
    }

    @Override
    public List<Dept> findAll() {
        SqlSession sqlSession = null;
        List<Dept> list = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            DeptDao deptDao = MapperFactory.getMapper(sqlSession,DeptDao.class);

            //3.调用接口中的方法
            list = deptDao.findAll();
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

        return list;
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            DeptDao deptDao = MapperFactory.getMapper(sqlSession,DeptDao.class);

            //3.调用接口中的方法
            PageHelper.startPage(page, size);
            List<Dept> all = deptDao.findAll();
            PageInfo pageInfo = new PageInfo(all);
            return pageInfo;
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
}
