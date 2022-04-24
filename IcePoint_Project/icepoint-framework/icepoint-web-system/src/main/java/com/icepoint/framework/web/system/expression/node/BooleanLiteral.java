package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public class BooleanLiteral extends Literal {

    private static final TypedValue TRUE = new TypedValue(true, Boolean.class);

    private static final TypedValue FALSE = new TypedValue(false, Boolean.class);

    public BooleanLiteral(Object originalValue, int startPos, int endPos) {
        super(originalValue, startPos, endPos);
    }

    @Override
    protected TypedValue newTypedValue(Object originalValue) {
        return (boolean) originalValue ? TRUE : FALSE;
    }
}
