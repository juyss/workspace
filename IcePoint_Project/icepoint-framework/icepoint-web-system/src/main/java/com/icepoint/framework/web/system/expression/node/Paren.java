package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public class Paren extends ParentExpressionNode {

    public Paren(int startPos, int endPos, AbstractExpressionNode... children) {
        super("(", ")", startPos, endPos, children);
    }
}
