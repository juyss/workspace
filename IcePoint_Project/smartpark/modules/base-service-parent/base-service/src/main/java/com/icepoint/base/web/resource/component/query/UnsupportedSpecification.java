package com.icepoint.base.web.resource.component.query;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UnsupportedSpecification implements Specification<Object> {


    @Override
    public Specification<Object> and(@Nullable Specification<Object> other) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public Specification<Object> or(@Nullable Specification<Object> other) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public Predicate toPredicate(Root<Object> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
