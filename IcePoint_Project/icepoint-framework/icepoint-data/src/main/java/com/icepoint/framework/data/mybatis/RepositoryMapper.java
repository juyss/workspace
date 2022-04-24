package com.icepoint.framework.data.mybatis;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.icepoint.framework.data.annotation.ReadTransaction;
import com.icepoint.framework.data.domain.BaseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
@ReadTransaction
public interface RepositoryMapper<T extends BaseEntity<ID>, ID extends Serializable>
        extends Mapper<T>, Constants {

    // Simple Query methods

    List<T> findAll();

    List<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    List<T> findAllByMap(@Param(COLUMN_MAP) Map<String, Object> columnMap);

    List<T> findAllById(Iterable<ID> ids);

    Optional<T> findById(ID id);

    long count();

    boolean existsById(ID id);

    // Wrapper methods

    Optional<T> findOne(@Param(WRAPPER) Wrapper<T> wrapper);

    List<T> findAll(@Param(WRAPPER) Wrapper<T> wrapper);

    List<T> findAll(@Param(WRAPPER) Wrapper<T> wrapper, Sort sort);

    Page<T> findAll(@Param(WRAPPER) Wrapper<T> wrapper, Pageable pageable);

    long count(@Param(WRAPPER) Wrapper<T> wrapper);

    boolean exists(@Param(WRAPPER) Wrapper<T> wrapper);

    // Save and update methods

    int insert(T entity);

    int update(@Param(ENTITY) T entity);

    int update(@Param(ENTITY) T entity, @Param(WRAPPER) Wrapper<T> updateWrapper);

    // Delete methods

    int delete(T entity);

    int deleteById(ID id);

    int deleteInBatchIds(@Param(COLLECTION) Iterable<ID> ids);

    int deleteByMap(@Param(COLUMN_MAP) Map<String, Object> columnMap);
}
