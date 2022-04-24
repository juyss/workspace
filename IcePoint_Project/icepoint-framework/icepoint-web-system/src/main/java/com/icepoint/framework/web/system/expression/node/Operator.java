package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public abstract class Operator extends AbstractExpressionNode {

    private final String name;

    protected Operator(String name, int startPos, int endPos) {
        super(startPos, endPos);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
