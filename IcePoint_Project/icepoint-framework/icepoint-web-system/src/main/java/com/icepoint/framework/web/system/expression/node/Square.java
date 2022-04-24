package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public class Square extends ParentExpressionNode {

    public Square(int startPos, int endPos, AbstractExpressionNode... children) {
        super("[", "]", startPos, endPos, children);
    }
}
