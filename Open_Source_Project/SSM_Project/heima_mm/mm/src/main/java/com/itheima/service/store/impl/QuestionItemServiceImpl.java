package com.itheima.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.QuestionItemDao;
import com.itheima.domain.store.QuestionItem;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.QuestionItemService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

/**
 * @author zxq
 * @create 2020-08-30 11:21
 */
public class QuestionItemServiceImpl implements QuestionItemService {
    @Override
    public void save(QuestionItem questionItem) {

        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类对象
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            //使用UUID给公司id赋值
            String uuid = UUID.randomUUID().toString();
            questionItem.setId(uuid);

            //3.调用接口的方法
            questionItemDao.save(questionItem);

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
    public void delete(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            //3.调用接口的方法
            questionItemDao.delete(questionItem);

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
    public void update(QuestionItem questionItem) {
        SqlSession sqlSession = null;

        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获得接口实现类
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            //3.调用接口的方法
            questionItemDao.update(questionItem);

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
    public QuestionItem findById(String id) {
        SqlSession sqlSession = null;
        QuestionItem questionItem =null;
        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口对象
            QuestionItemDao questionItemDao =MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            //3.调用接口的方法
            questionItem = questionItemDao.findById(id);

        }catch (Exception e){
            //抛出异常，交给上级
            throw new RuntimeException(e);
        }

        return questionItem;
    }


    @Override
    public PageInfo findAll(String questionId, int page, int size){
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            //3.调用接口的方法
            PageHelper.startPage(page,size);
            List<QuestionItem> all = questionItemDao.findAll(questionId);
            PageInfo pageInfo=new PageInfo(all);
            return pageInfo;

        }catch (Exception e){
            //抛出异常，交给上级
            throw new RuntimeException(e);
        }


    }
}
