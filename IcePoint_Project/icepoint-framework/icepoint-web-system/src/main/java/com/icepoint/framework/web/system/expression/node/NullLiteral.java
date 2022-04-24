package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public class NullLiteral extends Literal {

    public NullLiteral(int startPos, int endPos) {
        super(null, startPos, endPos);
    }

    @Override
    protected TypedValue newTypedValue(Object originalValue) {
        return new TypedValue(null, null);
    }
}
