package com.itheima.service.store.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.CompanyDao;
import com.itheima.domain.store.Company;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.CompanyService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

/**
 * @author zxq
 * @create 2020-08-26 8:47
 */
public class CompanyServiceImpl implements CompanyService {


    @Override
    public void save(Company company) {

        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类对象
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            //使用UUID给公司id赋值
            String uuid = UUID.randomUUID().toString();
            company.setId(uuid);

            //3.调用接口的方法
            companyDao.save(company);

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
    public void delete(Company company) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            //3.调用接口的方法
            companyDao.delete(company);

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
    public void update(Company company) {
        SqlSession sqlSession = null;

        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获得接口实现类
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            //3.调用接口的方法
            companyDao.update(company);

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
    public Company findById(String id) {
        SqlSession sqlSession = null;
        Company company =null;
        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口对象
            CompanyDao companyDao =MapperFactory.getMapper(sqlSession,CompanyDao.class);

            //3.调用接口的方法
            company = companyDao.findById(id);

        }catch (Exception e){
            //抛出异常，交给上级
            throw new RuntimeException(e);
        }

        return company;
    }

    @Override
    public List<Company> findAll() {
        List<Company> list=null;
        SqlSession sqlSession = null;
        try{
            //1.获取SqLSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            //3.调用接口的方法
            list = companyDao.findAll();


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
                CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

                //3.调用接口的方法
                PageHelper.startPage(page,size);
                List<Company> all = companyDao.findAll();
                PageInfo pageInfo=new PageInfo(all);
                return pageInfo;

            }catch (Exception e){
                //抛出异常，交给上级
                throw new RuntimeException(e);
            }


    }
}
