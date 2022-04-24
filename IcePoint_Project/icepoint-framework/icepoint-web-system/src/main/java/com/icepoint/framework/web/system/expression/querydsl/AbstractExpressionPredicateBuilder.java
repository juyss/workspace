package com.icepoint.framework.web.system.expression.querydsl;

import com.icepoint.framework.web.system.expression.Expression;

/**
 * @author Jiawei Zhao
 */
public abstract class AbstractExpressionPredicateBuilder<E extends Expression>
        implements ExpressionPredicateBuilder<E> {

    private final ExpressionPredicateBuilderAdapter adapter;

    protected AbstractExpressionPredicateBuilder(ExpressionPredicateBuilderAdapter adapter) {
        this.adapter = adapter;
    }

    protected ExpressionPredicateBuilderAdapter getAdapter() {
        return adapter;
    }
}
