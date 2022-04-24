package com.icepoint.base.web.basic.service;

import com.icepoint.base.api.domain.BasicEntity;
import com.icepoint.base.web.basic.repository.MybatisMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 *
 * @param <M> Mapper类型
 * @param <E> 实体类类型
 * @param <ID> 实体ID的类型
 *
 * @author Jiawei Zhao
 */
public abstract class AntdPageService<M extends MybatisMapper<E, ID>, E extends BasicEntity<ID>, ID extends Serializable>
        extends AutowiredCrudService<M, E, ID> {

    @Override
    public <S extends E> Page<S> page(Example<S> example, Pageable pageable) {
//        return new AntdPageInfo<>(super.page(entity, pageable), pageable);
        return null;
    }

}
