package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public class Curly extends ParentExpressionNode {

    public Curly(int startPos, int endPos, AbstractExpressionNode... children) {
        super("{", "}", startPos, endPos, children);
    }
}
