package com.icepoint.base.web.resource.component.query;

import com.github.tangyi.common.core.utils.Assert;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.Parameter;

@Data
@RequiredArgsConstructor
public class GenericQueryParameter implements QueryParameter {

    private Match match;

    private Require require;

    @SuppressWarnings("unchecked")
    @Override
    public <P extends Specification<Object> & Parameter<P>> P getParameter(Class<P> parameterType) {
        Assert.notNull(parameterType, "Parameter type must not be null");

        if (Match.class.isAssignableFrom(parameterType)) {
            return getIfPossible((P) match, parameterType);
        }
        if (Require.class.isAssignableFrom(parameterType)) {
            return getIfPossible((P) require, parameterType);
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return match == null && require == null;
    }

    @Nullable
    private  <P extends Specification<Object> & Parameter<P>> P getIfPossible(
            @Nullable P instance, Class<P> parameterType) {

        if (instance == null)
            return null;
        if (parameterType.isAssignableFrom(instance.getParameterType())) {
            return instance;
        }
        return null;
    }

}
