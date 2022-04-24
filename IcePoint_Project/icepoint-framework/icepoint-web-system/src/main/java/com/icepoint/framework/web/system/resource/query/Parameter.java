package com.icepoint.framework.web.system.resource.query;

import javax.annotation.Nullable;

/**
 * IN和NOT_IN的值指定要数组类型
 *
 * @author Jiawei Zhao
 */
public class Parameter {

    private final Class<?> type;

    private final String name;

    private final Object value;

    private final Operation operation;

    private final LogicCondition condition;

    public Parameter(@Nullable Class<?> type, String name, @Nullable Object value, Operation operation,
            LogicCondition condition) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.operation = operation;
        this.condition = condition;
    }

    @Nullable
    public Class<?> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public Object getValue() {
        return value;
    }

    public Operation getOperation() {
        return operation;
    }

    public LogicCondition getCondition() {
        return condition;
    }
}