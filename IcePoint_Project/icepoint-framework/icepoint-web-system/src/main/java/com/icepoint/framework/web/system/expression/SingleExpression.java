package com.icepoint.framework.web.system.expression;

import com.icepoint.framework.web.system.expression.node.ExpressionNode;
import com.icepoint.framework.web.system.expression.node.Operator;

/**
 * @author Jiawei Zhao
 */
public class SingleExpression extends AbstractExpression {

    private final ExpressionNode leftOperand;

    private final Operator operator;

    private final ExpressionNode rightOperand;

    public SingleExpression(String expressionString, ExpressionNode leftOperand, Operator operator,
            ExpressionNode rightOperand) {

        super(expressionString);
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
    }

    public ExpressionNode getLeftOperand() {
        return this.leftOperand;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public ExpressionNode getRightOperand() {
        return this.rightOperand;
    }

}
