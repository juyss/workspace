package com.icepoint.base.web.resource.component.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.Parameter;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class Match
        extends UnsupportedSpecification
        implements Specification<Object>, Parameter<Match> {

    private @NonNull Map<String, FieldOperation> fieldOps;

    private MatchOption option = new MatchOption();

    @Nullable
    public FieldOperation getOp(String fieldName) {
        return fieldOps.get(fieldName);
    }

    public Collection<FieldOperation> getOps() {
        return Collections.unmodifiableCollection(fieldOps.values());
    }

    @Override
    public String getName() {
        return "$match";
    }

    @Nullable
    @Override
    public Integer getPosition() {
        return null;
    }

    @Override
    public Class<Match> getParameterType() {
        return Match.class;
    }
}
