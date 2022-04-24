package com.icepoint.framework.web.system.expression;

import com.icepoint.framework.core.util.NullablePair;
import com.icepoint.framework.web.system.expression.node.LogicConnector;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
public class ChainExpression extends AbstractExpression implements Iterable<NullablePair<Expression, LogicConnector>> {

    private final List<NullablePair<Expression, LogicConnector>> expressions;

    public ChainExpression(String expressionString) {
        super(expressionString);
        this.expressions = new LinkedList<>();
    }

    @Override
    public Iterator<NullablePair<Expression, LogicConnector>> iterator() {
        return expressions.iterator();
    }

    public void add(Expression expression, LogicConnector connector) {
        this.expressions.add(NullablePair.of(expression, connector));
    }
}
