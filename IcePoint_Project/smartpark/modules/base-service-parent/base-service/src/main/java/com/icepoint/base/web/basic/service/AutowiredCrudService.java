package com.icepoint.base.web.basic.service;

import com.icepoint.base.api.domain.BasicEntity;
import com.icepoint.base.web.basic.repository.MybatisMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 *
 * @param <M> Mapper类型
 * @param <E> 实体类类型
 * @param <ID> 实体ID的类型
 *
 * @author Jiawei Zhao
 */
public abstract class AutowiredCrudService
        <M extends MybatisMapper<E, ID>, E extends BasicEntity<ID>, ID extends Serializable>
        implements MybatisCrudService<M, E, ID> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected M mapper;

    @Override
    public M getRepository() {
        return mapper;
    }
}
