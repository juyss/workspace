package com.icepoint.framework.web.system.resource.builder;

import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.web.system.resource.query.*;

import java.util.LinkedList;
import java.util.List;

import static com.icepoint.framework.web.system.resource.query.Operation.*;

/**
 * @author Jiawei Zhao
 */
public class DefaultQueryBuilder implements QueryBuilder {

    private final List<Parameter> parameters;

    private DefaultQueryBuilder() {
        parameters = new LinkedList<>();
    }

    public static DefaultQueryBuilder of() {
        return new DefaultQueryBuilder();
    }

    public DefaultQueryBuilder andEq(String name, Object value) {
        return andParameter(name, value, EQ);
    }

    public DefaultQueryBuilder orEq(String name, Object value) {
        return orParameter(name, value, EQ);
    }

    public DefaultQueryBuilder andNe(String name, Object value) {
        return andParameter(name, value, NE);
    }

    public DefaultQueryBuilder orNe(String name, Object value) {
        return orParameter(name, value, NE);
    }

    public DefaultQueryBuilder andGt(String name, Object value) {
        return andParameter(name, value, GT);
    }

    public DefaultQueryBuilder orGt(String name, Object value) {
        return orParameter(name, value, GT);
    }

    public DefaultQueryBuilder andGe(String name, Object value) {
        return andParameter(name, value, GE);
    }

    public DefaultQueryBuilder orGe(String name, Object value) {
        return orParameter(name, value, GE);
    }

    public DefaultQueryBuilder andLt(String name, Object value) {
        return andParameter(name, value, LT);
    }

    public DefaultQueryBuilder orLt(String name, Object value) {
        return orParameter(name, value, LT);
    }

    public DefaultQueryBuilder andLe(String name, Object value) {
        return andParameter(name, value, LE);
    }

    public DefaultQueryBuilder orLe(String name, Object value) {
        return orParameter(name, value, LE);
    }

    public DefaultQueryBuilder andIn(String name, Object[] value) {
        return andParameter(name, value, IN);
    }

    public DefaultQueryBuilder andIn(String name, Iterable<Object> value) {
        return andIn(name, Streams.stream(value).toArray());
    }

    public DefaultQueryBuilder orIn(String name, Object[] value) {
        return orParameter(name, value, IN);
    }

    public DefaultQueryBuilder orIn(String name, Iterable<Object> value) {
        return orIn(name, Streams.stream(value).toArray());
    }

    public DefaultQueryBuilder andNotIn(String name, Object value) {
        return andParameter(name, value, NOT_IN);
    }

    public DefaultQueryBuilder orNotIn(String name, Object value) {
        return orParameter(name, value, NOT_IN);
    }

    public DefaultQueryBuilder andBetween(String name, Object start, Object end) {
        return andParameter(name, new Object[]{ start, end }, BETWEEN);
    }

    public DefaultQueryBuilder orBetween(String name, Object start, Object end) {
        return orParameter(name, new Object[]{ start, end }, BETWEEN);
    }

    public DefaultQueryBuilder andLike(String name, Object value) {
        return andParameter(name, value, LIKE);
    }

    public DefaultQueryBuilder orLike(String name, Object value) {
        return orParameter(name, value, LIKE);
    }

    private DefaultQueryBuilder andParameter(String name, Object value, Operation op) {
        return addParameter(name, value, op, LogicCondition.AND);
    }

    private DefaultQueryBuilder orParameter(String name, Object value, Operation op) {
        return addParameter(name, value, op, LogicCondition.OR);
    }

    private DefaultQueryBuilder addParameter(String name, Object value, Operation op, LogicCondition condition) {
        this.parameters.add(new Parameter(
                value == null ? null : value.getClass(),
                name, value, op, condition));
        return this;
    }

    @Override
    public Query build() {
        return new DefaultQuery(parameters);
    }
}
