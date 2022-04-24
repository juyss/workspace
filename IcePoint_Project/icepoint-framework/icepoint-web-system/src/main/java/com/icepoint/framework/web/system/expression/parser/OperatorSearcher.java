package com.icepoint.framework.web.system.expression.parser;

import com.icepoint.framework.web.system.expression.ExpressionContext;
import com.icepoint.framework.web.system.expression.node.Operator;


/**
 * @author Jiawei Zhao
 */
public class OperatorSearcher extends Searcher<Operator> {

    public OperatorSearcher(String expressionString, ExpressionContext context) {
        super(expressionString, context);
    }

    @Override
    protected String[] getDefinitions() {
        return getContext().getOperatorNames();
    }

    @Override
    protected Operator create(String def, int startPos, int endPos) {
        return getContext().newOperator(def, startPos, endPos);
    }
}
