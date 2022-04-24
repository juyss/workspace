package com.icepoint.framework.web.system.expression.node;

/**
 * @author Jiawei Zhao
 */
public class ArrayLiteral extends Literal {

    public static final String SEPARATOR = ",";

    public ArrayLiteral(Object originalValue, int startPos, int endPos) {
        super(originalValue, startPos, endPos);
    }

    @Override
    protected TypedValue newTypedValue(Object originalValue) {
        return new TypedValue(originalValue, Literal[].class);
    }
}
