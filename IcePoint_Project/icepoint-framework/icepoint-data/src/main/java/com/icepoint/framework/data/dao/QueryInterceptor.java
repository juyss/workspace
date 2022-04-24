package com.icepoint.framework.data.dao;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.lang.Nullable;

/**
 * @author Jiawei Zhao
 */
public interface QueryInterceptor {

    @Nullable
    default <T> Specification<T> before(
            EntityInformation<T, ?> entityInformation,
            @Nullable Specification<T> spec,
            @Nullable Pageable pageable,
            @Nullable Sort sort) {

        return spec;
    }

    default Predicate before(
            EntityInformation<?, ?> entityInformation,
            Predicate predicate,
            @Nullable Pageable pageable,
            @Nullable Sort sort,
            @Nullable OrderSpecifier<?>... orders) {

        return predicate;
    }
}
