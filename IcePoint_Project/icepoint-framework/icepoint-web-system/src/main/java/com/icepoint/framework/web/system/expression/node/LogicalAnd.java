package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public class LogicalAnd extends LogicConnector {

    public LogicalAnd(int startPos, int endPos) {
        super("&&", startPos, endPos);
    }
}
