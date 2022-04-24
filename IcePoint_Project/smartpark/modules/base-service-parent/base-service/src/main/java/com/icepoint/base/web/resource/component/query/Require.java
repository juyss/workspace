package com.icepoint.base.web.resource.component.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.Parameter;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class Require
        extends UnsupportedSpecification
        implements Specification<Object>, Parameter<Require> {

    private final List<String> keys;

    @Override
    public String getName() {
        return "$require";
    }

    @Nullable
    @Override
    public Integer getPosition() {
        return null;
    }

    @Override
    public Class<Require> getParameterType() {
        return Require.class;
    }
}
