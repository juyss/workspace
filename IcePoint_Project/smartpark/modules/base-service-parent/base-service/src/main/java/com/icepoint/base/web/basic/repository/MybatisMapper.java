package com.icepoint.base.web.basic.repository;

import com.icepoint.base.api.domain.BasicEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jiawei Zhao
 *
 * @param <E> 实体类的类型
 * @param <ID> 实体id的类型
 */
@NoRepositoryBean
public interface MybatisMapper<E extends BasicEntity<ID>, ID extends Serializable>
        extends PagingAndSortingRepository<E, ID>, JpaSpecificationExecutor<E> {

    @Override
    <S extends E> List<S> saveAll(@Param("entities") Iterable<S> entities);

    <S extends E> void saveAllMybatis(@Param("entities") Iterable<S> entities);

    @Override
    List<E> findAll();

    @Override
    List<E> findAllById(@Param("ids") Iterable<ID> ids);

    @Override
    List<E> findAll(Sort sort);

    Page<E> findAll(@Param("entity") E entity, Pageable pageable);

    Optional<E> find(@Param("entity")E entity);

    List<E> findAll(@Param("entity") E entity);
}
