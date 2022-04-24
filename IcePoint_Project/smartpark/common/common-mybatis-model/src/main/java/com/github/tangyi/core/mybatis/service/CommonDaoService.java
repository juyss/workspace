package com.github.tangyi.core.mybatis.service;

import com.github.tangyi.common.core.persistence.BaseEntity;
import com.github.tangyi.core.mybatis.example.AbstractExample;

import java.util.List;

/**
 * 数据库基础操作
 *
 * @author hedongzhou
 * @since 2018/11/24
 */
public interface CommonDaoService {

    /**
     * 新增
     *
     * @param model
     * @param <T>
     * @return
     */
    <T extends BaseEntity> int insert(T model);

    /**
     * 批量新增，通过循环方式一条条新增，而非真正意义上的批量
     *
     * @param models
     * @param <T>
     * @return
     */
    <T extends BaseEntity> int insertList(List<T> models);

    /**
     * 更新
     *
     * @param model
     * @param <T>
     * @return
     */
    <T extends BaseEntity> int update(T model);

    /**
     * 批量更新，通过循环方式一条条新增
     *
     * @param models
     * @param <T>
     * @return
     */
    <T extends BaseEntity> int updateList(List<T> models);

    /**
     * 更新
     *
     * @param model
     * @param example
     * @param <T>
     * @return
     */
    <T extends BaseEntity> int updateByExample(T model,
                                              AbstractExample<T> example);

    /**
     * 让数据有效
     *
     * @param model
     * @param <T>
     * @return
     */
    <T extends BaseEntity> int valid(T model);

    /**
     * 删除
     *
     * @param model
     * @param <T>
     * @return
     */
    <T extends BaseEntity> int delete(T model);

    /**
     * 删除
     *
     * @param modelClass
     * @param key
     * @param <T>
     * @return
     */
    <T extends BaseEntity> int delete(Class<T> modelClass,
                                     Object key);

    /**
     * 根据主键获取
     *
     * @param modelClass
     * @param key
     * @param <T>
     * @return
     */
    <T extends BaseEntity> T get(Class<T> modelClass,
                                Object key);

    /**
     * 查询
     *
     * @param model
     * @param <T>
     * @return
     */
    <T extends BaseEntity> List<T> select(T model);

    /**
     * 查询
     *
     * @param example
     * @param <T>
     * @return
     */
    <T extends BaseEntity> List<T> selectByExample(AbstractExample<T> example);

    /**
     * 查询一条
     *
     * @param model
     * @param <T>
     * @return
     */
    <T extends BaseEntity> T selectOne(T model);

    /**
     * 查询一条
     *
     * @param example
     * @param <T>
     * @return
     */
    <T extends BaseEntity> T selectOneByExample(AbstractExample<T> example);

    /**
     * 合计
     *
     * @param model
     * @param <T>
     * @return
     */
    <T extends BaseEntity> int selectCount(T model);

    /**
     * 合计
     *
     * @param example
     * @param <T>
     * @return
     */
    <T extends BaseEntity> int selectCountByExample(AbstractExample<T> example);

}
