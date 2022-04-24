package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public class LogicalOr extends LogicConnector {

    public LogicalOr(int startPos, int endPos) {
        super("||", startPos, endPos);
    }
}
