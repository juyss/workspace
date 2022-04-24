package com.icepoint.framework.data.dao;

import com.icepoint.framework.data.annotation.ReadTransaction;
import com.icepoint.framework.data.domain.BaseEntity;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 应用标准访问层顶级接口，为了避免脏读等事务问题，默认所有方法只读，如果想要改变事务行为，必须在上层服务进行事务的再定义
 *
 * 所有查询接口多加一个额外的参数: deleted
 * 当deleted为true时，只会查询逻辑删除字段为true的数据，也就是被删除的数据
 * 当deleted为false时，只会查询逻辑珊瑚字段为false的数据，也就是没被删除的数据
 *
 * 继承此接口的实体类型T必须是StdEntity的子类，不然调用部分方法的时候会报错
 *
 * @param <T>
 * @param <ID>
 */
@ReadTransaction
@NoRepositoryBean
public interface StdRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends BaseRepository<T, ID> {

    // JPA Repository

    List<T> findAll(boolean deleted);

    List<T> findAll(Sort sort, boolean deleted);

    Page<T> findAll(Pageable pageable, boolean deleted);

    List<T> findAllById(Iterable<ID> ids, boolean deleted);

    Optional<T> findById(ID id, boolean deleted);

    T getOne(ID id, boolean deleted);

    long count(boolean deleted);

    boolean existsById(ID id, boolean deleted);

    // Example

    <S extends T> Optional<S> findOne(Example<S> example, boolean deleted);

    <S extends T> List<S> findAll(Example<S> example, boolean deleted);

    <S extends T> List<S> findAll(Example<S> example, Sort sort, boolean deleted);

    <S extends T> Page<S> findAll(Example<S> example, Pageable pageable, boolean deleted);

    <S extends T> long count(Example<S> example, boolean deleted);

    <S extends T> boolean exists(Example<S> example, boolean deleted);

    // Specification

    Optional<T> findOne(@Nullable Specification<T> spec, boolean deleted);

    List<T> findAll(@Nullable Specification<T> spec, boolean deleted);

    List<T> findAll(@Nullable Specification<T> spec, Sort sort, boolean deleted);

    Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable, boolean deleted);

    long count(@Nullable Specification<T> spec, boolean deleted);

    // QueryDSL

    @Override
    List<T> findAll(Predicate predicate);

    @Override
    List<T> findAll(Predicate predicate, Sort sort);

    @Override
    List<T> findAll(Predicate predicate, OrderSpecifier<?>... orders);

    @Override
    List<T> findAll(OrderSpecifier<?>... orders);

    Optional<T> findOne(Predicate predicate, boolean deleted);

    List<T> findAll(Predicate predicate, boolean deleted);

    List<T> findAll(Predicate predicate, Sort sort, boolean deleted);

    List<T> findAll(Predicate predicate, boolean deleted, OrderSpecifier<?>... orders);

    List<T> findAll(boolean deleted, OrderSpecifier<?>... orders);

    Page<T> findAll(Predicate predicate, Pageable pageable, boolean deleted);

    long count(Predicate predicate, boolean deleted);

    boolean exists(Predicate predicate, boolean deleted);
}
