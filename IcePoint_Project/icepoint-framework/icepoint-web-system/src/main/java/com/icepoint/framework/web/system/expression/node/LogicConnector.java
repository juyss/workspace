package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public abstract class LogicConnector extends AbstractExpressionNode {

    private final String sign;

    protected LogicConnector(String sign, int startPos, int endPos) {
        super(startPos, endPos);
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }


}
