package com.icepoint.framework.web.system.resource.query;

import com.icepoint.framework.core.util.CollectionUtils;
import com.icepoint.framework.core.util.Streams;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Jiawei Zhao
 */
public class DefaultQuery implements Query {

    private List<Parameter> parameters;

    public DefaultQuery(Iterable<Parameter> parameters) {
        this.parameters = Streams.streamable(parameters).toList();
    }

    @Override
    public void accept(QueryVisitor visitor) {
        visitor.visit(this);
    }

    public void addParameter(String name, Operation operation, @Nullable Object value, LogicCondition condition) {
        addParameter(new Parameter(
                value == null ? null : value.getClass(),
                name,
                value,
                operation,
                condition));
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public List<Parameter> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Parameter> getParameters(String name) {
        return Streams.streamable(parameters)
                .filter(parameter -> Objects.equals(name, parameter.getName()))
                .toList();
    }

    @Override
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(getParameters());
    }
}
