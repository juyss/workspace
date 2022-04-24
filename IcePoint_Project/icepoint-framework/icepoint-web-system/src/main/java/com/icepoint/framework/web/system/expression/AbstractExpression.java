package com.icepoint.framework.web.system.expression;

/**
 * @author Jiawei Zhao
 */
public abstract class AbstractExpression implements Expression {

    private final String expressionString;

    protected AbstractExpression(String expressionString) {
        this.expressionString = expressionString;
    }

    @Override
    public String getExpressionString() {
        return this.expressionString;
    }
}
