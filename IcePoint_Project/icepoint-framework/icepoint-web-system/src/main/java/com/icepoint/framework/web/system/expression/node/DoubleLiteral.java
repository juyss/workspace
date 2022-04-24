package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public class DoubleLiteral extends Literal{

    public DoubleLiteral(Object originalValue, int startPos, int endPos) {
        super(originalValue, startPos, endPos);
    }

    @Override
    protected TypedValue newTypedValue(Object originalValue) {
        return new TypedValue(originalValue, Double.class);
    }
}
