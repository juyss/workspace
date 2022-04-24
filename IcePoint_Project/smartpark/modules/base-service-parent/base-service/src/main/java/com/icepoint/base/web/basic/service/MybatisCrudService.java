package com.icepoint.base.web.basic.service;

import com.icepoint.base.api.util.BeanUtil;
import com.icepoint.base.api.util.HibernateValidateUtils;
import com.icepoint.base.api.domain.BasicEntity;
import com.icepoint.base.web.basic.repository.MybatisMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * 基于{@link MybatisMapper}的Service
 *
 * @param <M> Mapper类型
 * @param <E> 实体类类型
 * @param <ID> 实体ID的类型
 *
 * @author Jiawei Zhao
 */
public interface MybatisCrudService<M extends MybatisMapper<E, ID>, E extends BasicEntity<ID>, ID extends Serializable>
        extends CrudService<E, ID>, RepositoryProvider<M> {

    @Override
    default ID add(E entity) {
        Assert.notNull(entity, "entity不能为null");
        HibernateValidateUtils.validateOrThrow(entity);
        return entity.getId();
    }

    @Override
    default List<E> addAll(List<E> entities) {
        Assert.noNullElements(entities, "entity不能为null");
        HibernateValidateUtils.validateOrThrow(entities.toArray());
        getRepository().saveAllMybatis(entities);
        return entities;
    }

    @Override
    default void update(E entity) {
        Assert.notNull(entity.getId(), "记录Id不能为空");

        M repository = getRepository();
        E databaseEntity = repository.findById(entity.getId()).orElseThrow(() -> new EmptyResultDataAccessException(
                        String.format("记录id: %s 不存在", entity.getId()), 1));

        HibernateValidateUtils.validateOrThrow(entity);
        BeanUtil.copyPropertiesIgnoreNull(entity, databaseEntity);
        Assert.state(getRepository().save(databaseEntity).getId() == null, "更新失败");
    }

    @Override
    default void delete(ID id) {
        Assert.notNull(id, "记录Id不能为空");

        M repository = getRepository();
        E databaseEntity = repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(
                String.format("记录id: %s 不存在", id), 1));
        repository.deleteById(databaseEntity.getId());
    }

    @Override
    default E get(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Nullable
    @Override
    default <S extends E> S get(Example<S> example) {
        return (S) getRepository().find(example.getProbe()).orElse(null);
    }

    @Override
    default List<E> list() {
        return getRepository().findAll();
    }

    @Override
    default <S extends E> List<S> list(Example<S> example) {
        return (List<S>) getRepository().findAll(example.getProbe());
    }

    @Override
    default <S extends E> Page<S> page(Example<S> example, Pageable pageable) {
        return (Page<S>) getRepository().findAll(example.getProbe(), pageable);
    }
}
