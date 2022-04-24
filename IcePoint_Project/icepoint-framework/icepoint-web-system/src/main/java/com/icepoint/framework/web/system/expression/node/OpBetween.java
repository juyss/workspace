package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public class OpBetween extends Operator {

    public OpBetween(int startPos, int endPos) {
        super("between", startPos, endPos);
    }
}
