package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public class IntLiteral extends Literal {

    public IntLiteral(Object originalValue, int startPos, int endPos) {
        super(originalValue, startPos, endPos);
    }

    @Override
    protected TypedValue newTypedValue(Object originalValue) {
        return new TypedValue(originalValue, Integer.class);
    }
}
