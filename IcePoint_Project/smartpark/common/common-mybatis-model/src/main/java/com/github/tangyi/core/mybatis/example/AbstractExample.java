package com.github.tangyi.core.mybatis.example;

import tk.mybatis.mapper.entity.Example;

/**
 * Example
 *
 * @author hedongzhou
 * @since 2019/05/19
 */
public abstract class AbstractExample<T> extends Example {

    public AbstractExample(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public Class<T> getEntityClass() {
        return (Class<T>) super.getEntityClass();
    }

}
