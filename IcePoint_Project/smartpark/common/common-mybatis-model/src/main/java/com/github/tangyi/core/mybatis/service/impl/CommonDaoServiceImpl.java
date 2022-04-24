package com.github.tangyi.core.mybatis.service.impl;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.mybatis.example.AbstractExample;
import com.github.tangyi.core.mybatis.service.CommonDaoService;
import com.github.tangyi.core.mybatis.mapper.CommonDaoHelper;

import java.util.List;

/**
 * 数据库基础操作
 *
 * @author hedongzhou
 * @since 2018/11/24
 */
public class CommonDaoServiceImpl implements CommonDaoService {

    /**
     * 新增
     *
     * @param model
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> int insert(T model) {
        return CommonDaoHelper.getMapper(model).insertSelective(model);
    }

    /**
     * 批量新增，通过循环方式一条条新增，而非真正意义上的批量
     *
     * @param models
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> int insertList(List<T> models) {
        if (CheckUtils.isEmpty(models)) {
            return 0;
        }

        int i = 0;
        for (T model : models) {
            i += insert(model);
        }
        return i;
    }

    /**
     * 更新
     *
     * @param model
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> int update(T model) {
        return CommonDaoHelper.getMapper(model).updateByPrimaryKeySelective(model);
    }

    /**
     * 批量更新，通过循环方式一条条新增
     *
     * @param models
     * @return
     */
    @Override
    public <T extends BaseEntity> int updateList(List<T> models) {
        if (CheckUtils.isEmpty(models)) {
            return 0;
        }

        int i = 0;
        for (T model : models) {
            i += update(model);
        }
        return i;
    }

    /**
     * 更新
     *
     * @param model
     * @param example
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> int updateByExample(T model,
                                                     AbstractExample<T> example) {
        return CommonDaoHelper.getMapper(model).updateByExampleSelective(model, example);
    }

    /**
     * 让数据有效
     *
     * @param model
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> int valid(T model) {
        model.setDelFlag(0);
        return update(model);
    }

    /**
     * 删除
     *
     * @param model
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> int delete(T model) {
        model.setDelFlag(1);
        return update(model);
    }

    /**
     * 删除
     *
     * @param modelClass
     * @param key
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> int delete(Class<T> modelClass,
                                            Object key) {
        return delete(get(modelClass, key));
    }

    /**
     * 根据主键获取
     *
     * @param modelClass
     * @param key
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> T get(Class<T> modelClass,
                                       Object key) {
        return CommonDaoHelper.getMapper(modelClass).selectByPrimaryKey(key);
    }

    /**
     * 查询
     *
     * @param model
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> List<T> select(T model) {
        return CommonDaoHelper.getMapper(model).select(model);
    }

    /**
     * 查询
     *
     * @param example
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> List<T> selectByExample(AbstractExample<T> example) {
        return CommonDaoHelper.getMapper(example.getEntityClass()).selectByExample(example);
    }

    /**
     * 查询一条
     *
     * @param model
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> T selectOne(T model) {
        return CommonDaoHelper.getMapper(model).selectOne(model);
    }

    /**
     * 查询一条
     *
     * @param example
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> T selectOneByExample(AbstractExample<T> example) {
        return CommonDaoHelper.getMapper(example.getEntityClass()).selectOneByExample(example);
    }

    /**
     * 合计
     *
     * @param model
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> int selectCount(T model) {
        return CommonDaoHelper.getMapper(model).selectCount(model);
    }

    /**
     * 合计
     *
     * @param example
     * @param <T>
     * @return
     */
    @Override
    public <T extends BaseEntity> int selectCountByExample(AbstractExample<T> example) {
        return CommonDaoHelper.getMapper(example.getEntityClass()).selectCountByExample(example);
    }

}
