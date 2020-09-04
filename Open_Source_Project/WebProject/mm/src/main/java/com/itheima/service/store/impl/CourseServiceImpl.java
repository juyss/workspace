package com.itheima.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.CourseDao;
import com.itheima.domain.store.Course;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.CourseService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zxq
 * @create 2020-08-28 17:37
 */
public class CourseServiceImpl implements CourseService {

    @Override
    public void save(Course course) {

        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类对象
            CourseDao courseDao = MapperFactory.getMapper(sqlSession, CourseDao.class);
            //使用UUID给公司id赋值
            String uuid = UUID.randomUUID().toString();
            course.setId(uuid);

            //！！添加创建的时间
            course.setCreateTime(new Date());

            //3.调用接口的方法
            courseDao.save(course);

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
    public void delete(Course course) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            CourseDao courseDao = MapperFactory.getMapper(sqlSession,CourseDao.class);

            //3.调用接口的方法
            courseDao.delete(course);

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
    public void update(Course course) {
        SqlSession sqlSession = null;

        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获得接口实现类
            CourseDao courseDao = MapperFactory.getMapper(sqlSession,CourseDao.class);

            //3.调用接口的方法
            courseDao.update(course);

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
    public Course findById(String id) {
        SqlSession sqlSession = null;
        Course course =null;
        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口对象
            CourseDao courseDao =MapperFactory.getMapper(sqlSession,CourseDao.class);

            //3.调用接口的方法
            course = courseDao.findById(id);

        }catch (Exception e){
            //抛出异常，交给上级
            throw new RuntimeException(e);
        }

        return course;
    }

    @Override
    public List<Course> findAll() {
        List<Course> list=null;
        SqlSession sqlSession = null;
        try{
            //1.获取SqLSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            CourseDao courseDao = MapperFactory.getMapper(sqlSession,CourseDao.class);

            //3.调用接口的方法
            list = courseDao.findAll();


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
            CourseDao courseDao = MapperFactory.getMapper(sqlSession,CourseDao.class);

            //3.调用接口的方法
            PageHelper.startPage(page,size);
            List<Course> all = courseDao.findAll();
            PageInfo pageInfo=new PageInfo(all);
            return pageInfo;

        }catch (Exception e){
            //抛出异常，交给上级
            throw new RuntimeException(e);
        }


    }
}
