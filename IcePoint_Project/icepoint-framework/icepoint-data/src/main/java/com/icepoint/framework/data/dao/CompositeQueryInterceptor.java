package com.icepoint.framework.data.dao;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
@Order
@Component
public class CompositeQueryInterceptor {

    private final List<QueryInterceptor> queryInterceptors;

    public CompositeQueryInterceptor(ObjectProvider<QueryInterceptor> queryInterceptors) {
        this.queryInterceptors = queryInterceptors.orderedStream().collect(Collectors.toList());
    }

    @Nullable
    public <T> Specification<T> beforeQuery(
            EntityInformation<T, ?> entityInformation,
            @Nullable Specification<T> spec,
            @Nullable Pageable pageable,
            @Nullable Sort sort) {

        if (CollectionUtils.isEmpty(queryInterceptors))
            return spec;

        for (QueryInterceptor interceptor : queryInterceptors) {
            spec = interceptor.before(entityInformation, spec, pageable, sort);
        }

        return spec;
    }

    @Nullable
    public <T> Specification<T> beforeQuery(
            EntityInformation<T, ?> entityInformation,
            Example<T> example,
            @Nullable Pageable pageable,
            @Nullable Sort sort,
            EscapeCharacter escapeCharacter) {

        Specification<T> spec = (root, query, cb) -> QueryByExamplePredicateBuilder.getPredicate(root, cb, example, escapeCharacter);
        if (CollectionUtils.isEmpty(queryInterceptors)) {
            return spec;
        }

        for (QueryInterceptor interceptor : queryInterceptors) {
            spec = interceptor.before(entityInformation, spec, pageable, sort);
        }

        return spec;
    }

    public Predicate beforeQuery(
            EntityInformation<?, ?> entityInformation,
            Predicate predicate,
            @Nullable Pageable pageable,
            @Nullable Sort sort,
            @Nullable OrderSpecifier<?>... orders) {

        if (CollectionUtils.isEmpty(queryInterceptors))
            return predicate;

        for (QueryInterceptor interceptor : queryInterceptors) {
            predicate = interceptor.before(entityInformation, predicate, pageable, sort, orders);
        }

        return predicate;
    }
}
