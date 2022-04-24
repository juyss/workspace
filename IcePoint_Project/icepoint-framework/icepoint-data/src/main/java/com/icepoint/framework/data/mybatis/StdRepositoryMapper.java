package com.icepoint.framework.data.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.data.domain.StdEntity;
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
 * @deprecated 未支持
 */
@Deprecated
public interface StdRepositoryMapper<T extends StdEntity<ID, ID>, ID extends Serializable>
        extends RepositoryMapper<T, ID> {

    String DELETED = "_deleted";

    List<T> findAll(@Param(DELETED) boolean deleted);

    List<T> findAll(Sort sort, @Param(DELETED) boolean deleted);

    Page<T> findAll(Pageable pageable, @Param(DELETED) boolean deleted);

    List<T> findAllByMap(@Param(COLUMN_MAP) Map<String, Object> columnMap, @Param(DELETED) boolean deleted);

    List<T> findAllById(Iterable<ID> ids, @Param(DELETED) boolean deleted);

    Optional<T> findById(ID id, @Param(DELETED) boolean deleted);

    long count(@Param(DELETED) boolean deleted);

    boolean existsById(ID id, @Param(DELETED) boolean deleted);

    Optional<T> findOne(@Param(WRAPPER) QueryWrapper<T> wrapper, @Param(DELETED) boolean deleted);

    List<T> findAll(@Param(WRAPPER) QueryWrapper<T> wrapper, @Param(DELETED) boolean deleted);

    List<T> findAll(@Param(WRAPPER) QueryWrapper<T> wrapper, Sort sort, @Param(DELETED) boolean deleted);

    Page<T> findAll(@Param(WRAPPER) QueryWrapper<T> wrapper, Pageable pageable, @Param(DELETED) boolean deleted);

    long count(@Param(WRAPPER) QueryWrapper<T> wrapper, @Param(DELETED) boolean deleted);

    boolean exists(@Param(WRAPPER) QueryWrapper<T> wrapper, @Param(DELETED) boolean deleted);

}
