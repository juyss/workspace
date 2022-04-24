package com.icepoint.base.web.basic.service;

import com.icepoint.base.api.domain.BasicEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface CrudService<E extends BasicEntity<ID>, ID extends Serializable> extends Service<E, ID> {

    /**
     * 新增一条数据
     *
     * @param entity 新增的实体对象
     * @return 新增数据的ID
     */
    @Transactional
    ID add(E entity);

    @Transactional
    List<E> addAll(List<E> entities);

    @Transactional
    void update(E entity);

    @Transactional
    void delete(ID id);

    @Nullable
    E get(ID id);

    @Nullable
    <S extends E> S get(Example<S> example);

    List<E> list();

    /**
     * 查询列表
     *
     * @param example 查询条件对象
     * @return 查询结果列表，原则上不允许返回null，但可以返回空列表
     */
    <S extends E> List<S> list(Example<S> example);

    <S extends E>  Page<S> page(Example<S> example, Pageable pageable);
}
