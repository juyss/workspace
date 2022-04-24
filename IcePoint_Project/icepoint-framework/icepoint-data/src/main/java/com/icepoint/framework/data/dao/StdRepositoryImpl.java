package com.icepoint.framework.data.dao;

import com.icepoint.framework.core.util.ApplicationContextUtils;
import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.data.annotation.ReadTransaction;
import com.icepoint.framework.data.domain.BaseEntity;
import com.icepoint.framework.data.domain.StdEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import org.jetbrains.annotations.Contract;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.icepoint.framework.data.domain.PropertyConstants.DELETED;
import static com.icepoint.framework.data.domain.PropertyConstants.ID;

/**
 * {@link StdRepository}的实现类，JPA的大部分操作都是直接委托给Spring JPA默认的实现去执行，
 * 在此基础之上，增加了支持查询之前改变查询参数的{@link QueryInterceptor}
 * <p>
 *
 * @author Jiawei Zhao
 */
@ReadTransaction
@SuppressWarnings("unchecked")
public class StdRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable>
        implements StdRepository<T, ID>, JpaRepositoryImplementation<T, ID> {

    private static final String PREDICATE_MUST_NOT_BE_NULL = "Predicate 不能为空";

    private final SimpleJpaRepository<T, ID> simpleJpaRepository;
    private final QuerydslJpaPredicateExecutor<T> querydslJpaPredicateExecutor;
    private EscapeCharacter escapeCharacter = EscapeCharacter.DEFAULT;

    private final JpaEntityInformation<T, ?> entityInformation;

    private CompositeQueryInterceptor interceptor;

    private final EntityPathResolver entityPathResolver;

    public StdRepositoryImpl(
            JpaEntityInformation<T, ?> entityInformation,
            EntityManager entityManager) {

        this.entityInformation = entityInformation;
        this.simpleJpaRepository = new SimpleJpaRepository<>(entityInformation, entityManager);

        this.entityPathResolver = SimpleEntityPathResolver.INSTANCE;
        this.querydslJpaPredicateExecutor = new QuerydslJpaPredicateExecutor<>(
                entityInformation, entityManager, this.entityPathResolver, null);
    }

    private CompositeQueryInterceptor getInterceptor() {
        if (interceptor == null) {
            interceptor = ApplicationContextUtils.getRequiredBean(CompositeQueryInterceptor.class);
        }

        return interceptor;
    }

    // JpaRepositoryImplementation

    @Override
    public void setRepositoryMethodMetadata(CrudMethodMetadata crudMethodMetadata) {
        simpleJpaRepository.setRepositoryMethodMetadata(crudMethodMetadata);
    }

    @Override
    public void setEscapeCharacter(EscapeCharacter escapeCharacter) {
        simpleJpaRepository.setEscapeCharacter(escapeCharacter);
        this.escapeCharacter = escapeCharacter;
    }

    @Nullable
    private Specification<T> compositeDeletedSpecification(@Nullable Specification<T> spec, boolean deleted) {

        if (!isStdEntity()) {
            return spec;
        }

        Specification<T> deletedSpec = (root, query, cb) -> cb.equal(root.get(DELETED), deleted);

        return spec == null ? deletedSpec : spec.and(deletedSpec);
    }

    @Nullable
    @Contract(value = "!null, _ -> !null", pure = true)
    private Predicate compositeDeletedPredicate(@Nullable Predicate predicate, boolean deleted) {

        Class<T> entityType = entityInformation.getJavaType();
        if (!isStdEntity()) {
            return predicate;
        }

        EntityPath<?> path = entityPathResolver.createPath(entityType);
        return new BooleanBuilder(predicate).and(Expressions.booleanPath(path, DELETED).eq(deleted));
    }

    private boolean isStdEntity() {
        return StdEntity.class.isAssignableFrom(entityInformation.getJavaType());
    }


    private static void setDeleted(Object entity) {
        ((StdEntity<?, ?>) entity).setDeleted(true);
    }

    private void assertIdNotNull(T entity) {
        Assert.notNull(entity.getId(), MessageTemplates.notNull(ID));
    }

    private List<T> findAllByCompositeId(Iterable<ID> ids) {

        List<T> results = new ArrayList<>();

        for (ID id : ids) {
            findById(id).ifPresent(results::add);
        }

        return results;
    }

    // JPA Repository

    @Override
    public List<T> findAll() {
        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, null, null, null);

        if (spec != null) {
            return simpleJpaRepository.findAll(spec);
        }

        return simpleJpaRepository.findAll();
    }

    @Override
    public List<T> findAll(boolean deleted) {

        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, null, null, null);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.findAll(spec);
    }

    @Override
    public List<T> findAll(Sort sort) {

        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, null, null, sort);

        if (spec != null) {
            return simpleJpaRepository.findAll(spec);
        }

        return simpleJpaRepository.findAll(sort);
    }

    @Override
    public List<T> findAll(Sort sort, boolean deleted) {

        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, null, null, sort);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.findAll(spec, sort);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {

        if (!ids.iterator().hasNext()) {
            return Collections.emptyList();
        }

        if (entityInformation.hasCompositeId()) {
            return findAllByCompositeId(ids);
        }

        ByIdsSpecification<T> idsSpec = new ByIdsSpecification<>(entityInformation, ids);
        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, idsSpec, null, null);

        if (spec != null) {

            return simpleJpaRepository.findAll(idsSpec);

        }

        return simpleJpaRepository.findAllById(ids);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids, boolean deleted) {

        ByIdsSpecification<T> idsSpec = new ByIdsSpecification<>(entityInformation, ids);

        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, idsSpec, null, null);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.findAll(spec);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return simpleJpaRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        simpleJpaRepository.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return simpleJpaRepository.saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        for (T entity : entities) {
            assertIdNotNull(entity);
            if (isStdEntity()) {
                setDeleted(entity);
            }
        }

        if (isStdEntity()) {
            deleteAll(entities);
        } else {
            simpleJpaRepository.deleteInBatch(entities);
        }
    }

    @Override
    public void deleteAllInBatch() {
        if (isStdEntity()) {
            findAll(false).forEach(this::delete);
        } else {
            simpleJpaRepository.deleteAllInBatch();
        }
    }

    @Override
    public T getOne(ID id) {

        ByIdsSpecification<T> idSpec = new ByIdsSpecification<>(entityInformation, Collections.singleton(id));
        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, idSpec, null, null);

        if (spec != null) {
            return simpleJpaRepository.findOne(spec)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Unable to find " + entityInformation.getEntityName() + " with id " + id));
        }

        return simpleJpaRepository.getOne(id);
    }

    @Override
    public T getOne(ID id, boolean deleted) {

        ByIdsSpecification<T> idSpec = new ByIdsSpecification<>(entityInformation, Collections.singleton(id));

        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, idSpec, null, null);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.findOne(spec)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Unable to find " + entityInformation.getEntityName() + " with id " + id));
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {

        Specification<S> spec = getInterceptor().beforeQuery(
                (EntityInformation<S, ?>) entityInformation, example,
                null, null, escapeCharacter);

        return (List<S>) simpleJpaRepository.findAll((Specification<T>) spec);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, boolean deleted) {

        Specification<S> spec = getInterceptor().beforeQuery(
                (EntityInformation<S, ?>) entityInformation, example,
                null, null, escapeCharacter);

        spec = (Specification<S>) compositeDeletedSpecification((Specification<T>) spec, deleted);

        return (List<S>) simpleJpaRepository.findAll((Specification<T>) spec);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {

        Specification<S> spec = getInterceptor().beforeQuery(
                (EntityInformation<S, ?>) entityInformation, example,
                null, sort, escapeCharacter);

        return (List<S>) simpleJpaRepository.findAll((Specification<T>) spec);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort, boolean deleted) {

        Specification<S> spec = getInterceptor().beforeQuery(
                (EntityInformation<S, ?>) entityInformation, example,
                null, sort, escapeCharacter);

        spec = (Specification<S>) compositeDeletedSpecification((Specification<T>) spec, deleted);

        return (List<S>) simpleJpaRepository.findAll((Specification<T>) spec);
    }


    // Crud Repository

    @Override
    public <S extends T> S save(S entity) {
        return simpleJpaRepository.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {

        Specification<T> idSpec = (root, query, cb) -> cb.equal(root.get(entityInformation.getIdAttribute()), id);
        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, idSpec, null, null);

        if (spec != null) {
            return simpleJpaRepository.findOne(spec);
        }

        return simpleJpaRepository.findById(id);
    }

    @Override
    public Optional<T> findById(ID id, boolean deleted) {

        ByIdsSpecification<T> idSpec = new ByIdsSpecification<>(entityInformation, Collections.singleton(id));

        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, idSpec, null, null);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.findOne(spec);
    }

    @Override
    public boolean existsById(ID id) {

        ByIdsSpecification<T> idSpec = new ByIdsSpecification<>(entityInformation, Collections.singleton(id));

        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, idSpec, null, null);
        if (spec != null) {
            return simpleJpaRepository.count(spec) > 0;
        }

        return simpleJpaRepository.existsById(id);
    }

    @Override
    public boolean existsById(ID id, boolean deleted) {

        ByIdsSpecification<T> idSpec = new ByIdsSpecification<>(entityInformation, Collections.singleton(id));

        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, idSpec, null, null);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.count(spec) > 0;
    }

    @Override
    public long count() {

        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, null, null, null);

        if (spec != null) {
            return simpleJpaRepository.count(spec);
        }

        return simpleJpaRepository.count();
    }

    @Override
    public long count(boolean deleted) {

        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, null, null, null);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.count(spec);
    }

    @Override
    public void deleteById(ID id) {
        if (isStdEntity()) {
            T entity = findById(id).orElseThrow(() -> new EmptyResultDataAccessException(
                    String.format("No %s entity with id %s exists!", entityInformation.getJavaType(), id), 1));
            setDeleted(entity);
            save(entity);
        } else {
            simpleJpaRepository.deleteById(id);
        }
    }

    @Override
    public void delete(T entity) {
        assertIdNotNull(entity);
        if (isStdEntity()) {
            setDeleted(entity);
            save(entity);
        } else {
            simpleJpaRepository.delete(entity);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        for (T entity : entities) {
            assertIdNotNull(entity);
            if (isStdEntity()) {
                setDeleted(entity);
            }
        }

        if (isStdEntity()) {
            saveAll(entities);
        } else {
            simpleJpaRepository.deleteAll(entities);
        }

    }

    @Override
    public void deleteAllInId(Iterable<ID> id) {
        List<T> allById = findAllById(id, false);
        deleteAll(allById);
    }

    @Override
    public void deleteAll() {
        if (isStdEntity()) {
            List<T> all = findAll(false);
            deleteAll(all);
        } else {
            simpleJpaRepository.deleteAll();
        }
    }


    // Paging And Sorting Repository

    @Override
    public Page<T> findAll(Pageable pageable) {

        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, null, pageable, null);

        if (spec != null) {
            return simpleJpaRepository.findAll(spec, pageable);
        }

        return simpleJpaRepository.findAll(pageable);
    }

    @Override
    public Page<T> findAll(Pageable pageable, boolean deleted) {

        Specification<T> spec = getInterceptor().beforeQuery(entityInformation, null, pageable, null);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.findAll(spec, pageable);
    }

    // Example Executor

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {

        Specification<S> spec = getInterceptor().beforeQuery((
                        EntityInformation<S, ?>) entityInformation, example,
                null, null, escapeCharacter);

        if (spec != null) {
            return (Optional<S>) simpleJpaRepository.findOne((Specification<T>) spec);
        }

        return simpleJpaRepository.findOne(example);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example, boolean deleted) {

        Specification<S> spec = getInterceptor().beforeQuery(
                (EntityInformation<S, ?>) entityInformation, example,
                null, null, escapeCharacter);

        spec = (Specification<S>) compositeDeletedSpecification((Specification<T>) spec, deleted);

        return (Optional<S>) simpleJpaRepository.findOne((Specification<T>) spec);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {

        Specification<S> spec = getInterceptor().beforeQuery(
                (EntityInformation<S, ?>) entityInformation, example,
                pageable, null, escapeCharacter);

        if (spec != null) {
            return (Page<S>) simpleJpaRepository.findAll((Specification<T>) spec, pageable);
        }

        return simpleJpaRepository.findAll(example, pageable);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable, boolean deleted) {

        Specification<S> spec = getInterceptor().beforeQuery((
                        EntityInformation<S, ?>) entityInformation, example,
                pageable, null, escapeCharacter);

        spec = (Specification<S>) compositeDeletedSpecification((Specification<T>) spec, deleted);

        return (Page<S>) simpleJpaRepository.findAll((Specification<T>) spec, pageable);
    }

    @Override
    public <S extends T> long count(Example<S> example) {

        Specification<S> spec = getInterceptor().beforeQuery(
                (EntityInformation<S, ?>) entityInformation, example,
                null, null, escapeCharacter);

        if (spec != null) {
            return simpleJpaRepository.count((Specification<T>) spec);
        }

        return simpleJpaRepository.count(example);
    }

    @Override
    public <S extends T> long count(Example<S> example, boolean deleted) {

        Specification<S> spec = getInterceptor().beforeQuery((
                        EntityInformation<S, ?>) entityInformation, example,
                null, null, escapeCharacter);

        spec = (Specification<S>) compositeDeletedSpecification((Specification<T>) spec, deleted);

        return simpleJpaRepository.count((Specification<T>) spec);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {

        Specification<S> spec = getInterceptor().beforeQuery(
                (EntityInformation<S, ?>) entityInformation, example,
                null, null, escapeCharacter);

        if (spec != null) {
            return simpleJpaRepository.count((Specification<T>) spec) > 0;
        }

        return simpleJpaRepository.exists(example);
    }


    @Override
    public <S extends T> boolean exists(Example<S> example, boolean deleted) {

        Specification<S> spec = getInterceptor().beforeQuery(
                (EntityInformation<S, ?>) entityInformation, example,
                null, null, escapeCharacter);

        spec = (Specification<S>) compositeDeletedSpecification((Specification<T>) spec, deleted);

        return simpleJpaRepository.count((Specification<T>) spec) > 0;
    }

    // Specification Executor

    @Override
    public Optional<T> findOne(@Nullable Specification<T> spec) {

        spec = getInterceptor().beforeQuery(entityInformation, spec, null, null);

        return simpleJpaRepository.findOne(spec);
    }

    @Override
    public Optional<T> findOne(@Nullable Specification<T> spec, boolean deleted) {

        spec = getInterceptor().beforeQuery(entityInformation, spec, null, null);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.findOne(spec);
    }

    @Override
    public List<T> findAll(@Nullable Specification<T> spec) {

        spec = getInterceptor().beforeQuery(entityInformation, spec, null, null);

        return simpleJpaRepository.findAll(spec);
    }

    @Override
    public List<T> findAll(@Nullable Specification<T> spec, boolean deleted) {

        spec = getInterceptor().beforeQuery(entityInformation, spec, null, null);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.findAll(spec);
    }

    @Override
    public Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable) {

        spec = getInterceptor().beforeQuery(entityInformation, spec, pageable, null);

        return simpleJpaRepository.findAll(spec, pageable);
    }

    @Override
    public Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable, boolean deleted) {

        spec = getInterceptor().beforeQuery(entityInformation, spec, pageable, null);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.findAll(spec, pageable);
    }

    @Override
    public List<T> findAll(@Nullable Specification<T> spec, Sort sort) {

        spec = getInterceptor().beforeQuery(entityInformation, spec, null, sort);

        return simpleJpaRepository.findAll(spec, sort);
    }

    @Override
    public List<T> findAll(@Nullable Specification<T> spec, Sort sort, boolean deleted) {

        spec = getInterceptor().beforeQuery(entityInformation, spec, null, sort);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.findAll(spec, sort);
    }

    @Override
    public long count(@Nullable Specification<T> spec) {

        spec = getInterceptor().beforeQuery(entityInformation, spec, null, null);

        return simpleJpaRepository.count(spec);
    }

    @Override
    public long count(@Nullable Specification<T> spec, boolean deleted) {

        spec = getInterceptor().beforeQuery(entityInformation, spec, null, null);
        spec = compositeDeletedSpecification(spec, deleted);

        return simpleJpaRepository.count(spec);
    }

    // Query DSL Executor

    @Override
    public Optional<T> findOne(Predicate predicate) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, null, null);

        return querydslJpaPredicateExecutor.findOne(predicate);
    }

    @Override
    public Optional<T> findOne(Predicate predicate, boolean deleted) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, null, null);
        predicate = compositeDeletedPredicate(predicate, deleted);

        return querydslJpaPredicateExecutor.findOne(predicate); // NOSONAR
    }

    @Override
    public List<T> findAll(Predicate predicate) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, null, null);

        return querydslJpaPredicateExecutor.findAll(predicate);
    }

    @Override
    public List<T> findAll(Predicate predicate, boolean deleted) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, null, null);
        predicate = compositeDeletedPredicate(predicate, deleted);

        return querydslJpaPredicateExecutor.findAll(predicate); // NOSONAR
    }

    @Override
    public List<T> findAll(Predicate predicate, Sort sort) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, null, sort);

        return querydslJpaPredicateExecutor.findAll(predicate, sort);
    }

    @Override
    public List<T> findAll(Predicate predicate, Sort sort, boolean deleted) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, null, sort);
        predicate = compositeDeletedPredicate(predicate, deleted);

        return querydslJpaPredicateExecutor.findAll(predicate, sort); // NOSONAR
    }

    @Override
    public List<T> findAll(Predicate predicate, OrderSpecifier<?>... orders) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, null, null, orders);

        return querydslJpaPredicateExecutor.findAll(predicate, orders);
    }

    @Override
    public List<T> findAll(Predicate predicate, boolean deleted, OrderSpecifier<?>... orders) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, null, null, orders);
        predicate = compositeDeletedPredicate(predicate, deleted);

        return querydslJpaPredicateExecutor.findAll(predicate, orders); // NOSONAR
    }

    @Override
    public List<T> findAll(OrderSpecifier<?>... orders) {
        return querydslJpaPredicateExecutor.findAll(orders);
    }

    @Override
    public List<T> findAll(boolean deleted, OrderSpecifier<?>... orders) {

        Predicate predicate = compositeDeletedPredicate(null, deleted);

        if (predicate != null) {
            return querydslJpaPredicateExecutor.findAll(predicate, orders);
        }

        return querydslJpaPredicateExecutor.findAll(orders);
    }

    @Override
    public Page<T> findAll(Predicate predicate, Pageable pageable) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, pageable, null);

        return querydslJpaPredicateExecutor.findAll(predicate, pageable);
    }

    @Override
    public Page<T> findAll(Predicate predicate, Pageable pageable, boolean deleted) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, pageable, null);
        predicate = compositeDeletedPredicate(predicate, deleted);

        return querydslJpaPredicateExecutor.findAll(predicate, pageable); // NOSONAR
    }

    @Override
    public long count(Predicate predicate) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, null, null);

        return querydslJpaPredicateExecutor.count(predicate);
    }

    @Override
    public long count(Predicate predicate, boolean deleted) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, null, null);
        predicate = compositeDeletedPredicate(predicate, deleted);

        return querydslJpaPredicateExecutor.count(predicate); // NOSONAR
    }

    @Override
    public boolean exists(Predicate predicate) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, null, null);

        return querydslJpaPredicateExecutor.exists(predicate);
    }

    @Override
    public boolean exists(Predicate predicate, boolean deleted) {

        predicate = getInterceptor().beforeQuery(entityInformation, predicate, null, null);
        predicate = compositeDeletedPredicate(predicate, deleted);

        Assert.notNull(predicate, PREDICATE_MUST_NOT_BE_NULL);
        return querydslJpaPredicateExecutor.exists(predicate);
    }

    private static final class ByIdsSpecification<T> implements Specification<T> {

        private static final long serialVersionUID = 1L;

        private final transient JpaEntityInformation<T, ?> entityInformation;

        private final transient Iterable<?> ids;

        ByIdsSpecification(JpaEntityInformation<T, ?> entityInformation, Iterable<?> ids) {
            this.entityInformation = entityInformation;
            this.ids = ids;
        }

        @Override
        public javax.persistence.criteria.Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                CriteriaBuilder cb) {
            return root.get(entityInformation.getIdAttribute()).in(ids);
        }
    }
}
