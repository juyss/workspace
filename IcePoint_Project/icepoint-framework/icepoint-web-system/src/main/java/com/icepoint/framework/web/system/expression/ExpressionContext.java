package com.icepoint.framework.web.system.expression;

import com.icepoint.framework.web.system.expression.node.Literal;
import com.icepoint.framework.web.system.expression.node.LogicConnector;
import com.icepoint.framework.web.system.expression.node.Operator;

/**
 * @author Jiawei Zhao
 */
public interface ExpressionContext {

     String[] getOperatorNames();

     Operator newOperator(String name, int startPos, int endPos);

     String[] getLogicConnectorSigns();

     LogicConnector newLogicConnector(String sign, int startPos, int endPos);

     Literal getLiteral(Operator operator, String expressionString, int startPos, int endPos);
}
