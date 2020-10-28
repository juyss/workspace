package com.itheima.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.CatalogDao;
import com.itheima.domain.store.Catalog;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.CatalogService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zxq
 * @create 2020-08-28 18:23
 */
public class CatalogServiceImpl implements CatalogService {
    @Override
    public void save(Catalog catalog) {

        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类对象
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //使用UUID给公司id赋值
            String uuid = UUID.randomUUID().toString();
            catalog.setId(uuid);

            //!!加上创建时间
            catalog.setCreateTime(new Date());

            //3.调用接口的方法
            catalogDao.save(catalog);

            //4.提交事务
            TransactionUtil.commit(sqlSession);


        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Catalog catalog) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession,CatalogDao.class);

            //3.调用接口的方法
            catalogDao.delete(catalog);

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            TransactionUtil.rollback(sqlSession);
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
    public void update(Catalog catalog) {
        SqlSession sqlSession = null;

        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获得接口实现类
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession,CatalogDao.class);

            //3.调用接口的方法
            catalogDao.update(catalog);

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            //回滚事务
            TransactionUtil.rollback(sqlSession);
            //抛出异常，交给上级
            throw new RuntimeException(e);
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Catalog findById(String id) {
        SqlSession sqlSession = null;
        Catalog catalog =null;
        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口对象
            CatalogDao catalogDao =MapperFactory.getMapper(sqlSession,CatalogDao.class);

            //3.调用接口的方法
            catalog = catalogDao.findById(id);

        }catch (Exception e){
            //抛出异常，交给上级
            throw new RuntimeException(e);
        }

        return catalog;
    }

    @Override
    public List<Catalog> findAll() {
        List<Catalog> list=null;
        SqlSession sqlSession = null;
        try{
            //1.获取SqLSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession,CatalogDao.class);

            //3.调用接口的方法
            list= catalogDao.findAll();


        }catch (Exception e){
            //抛出异常，交给上级
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession,CatalogDao.class);

            //3.调用接口的方法
            PageHelper.startPage(page,size);
            List<Catalog> all = catalogDao.findAll();
            PageInfo pageInfo=new PageInfo(all);
            return pageInfo;

        }catch (Exception e){
            //抛出异常，交给上级
            throw new RuntimeException(e);
        }


    }
}
