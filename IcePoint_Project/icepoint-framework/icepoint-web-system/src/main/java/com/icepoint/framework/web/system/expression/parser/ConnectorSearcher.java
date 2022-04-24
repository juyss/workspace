package com.icepoint.framework.web.system.expression.parser;

import com.icepoint.framework.web.system.expression.ExpressionContext;
import com.icepoint.framework.web.system.expression.node.LogicConnector;

/**
 * @author Jiawei Zhao
 */
public class ConnectorSearcher extends Searcher<LogicConnector> {

    public ConnectorSearcher(String expressionString, ExpressionContext context) {
        super(expressionString, context);
    }

    @Override
    protected String[] getDefinitions() {
        return getContext().getLogicConnectorSigns();
    }

    @Override
    protected LogicConnector create(String def, int startPos, int endPos) {
        return getContext().newLogicConnector(def, startPos, endPos);
    }
}
