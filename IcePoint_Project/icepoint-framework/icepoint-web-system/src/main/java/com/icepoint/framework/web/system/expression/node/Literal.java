package com.icepoint.framework.web.system.expression.node;

/**
 * 字面值
 *
 * @author Jiawei Zhao
 */
public abstract class Literal extends AbstractExpressionNode {

    private final TypedValue value;

    private final Object originalValue;

    protected Literal(Object originalValue, int startPos, int endPos) {
        super(startPos, endPos);
        this.value = newTypedValue(originalValue);
        this.originalValue = originalValue;
    }

    public TypedValue getLiteralValue() {
        return this.value;
    }

    public Object getOriginalValue() {
        return this.originalValue;
    }

    @Override
    public final ExpressionNode[] getChildren() {
        return NO_CHILDREN;
    }

    protected abstract TypedValue newTypedValue(Object originalValue);
}
