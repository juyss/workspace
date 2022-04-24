package com.icepoint.base.web.resource.component.query;

import com.github.tangyi.common.core.utils.Assert;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.Parameter;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface QueryParameter extends Specification<Object> {

    @Nullable
    <P extends Specification<Object> & Parameter<P>> P getParameter(Class<P> parameterType);

    boolean isEmpty();

    default <P extends Specification<Object> & Parameter<P>> P getRequiredParameter(Class<P> parameterType) {
        P parameter = getParameter(parameterType);
        Assert.notNull(parameter, "parameter " + parameterType.getSimpleName() + " is not present.");
        return parameter;
    }

    @Override
    default Specification<Object> and(@Nullable Specification<Object> other) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Specification<Object> or(@Nullable Specification<Object> other) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Predicate toPredicate(Root<Object> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        throw new UnsupportedOperationException();
    }
}