package com.icepoint.framework.web.system.expression.parser;

import com.icepoint.framework.web.system.expression.*;
import com.icepoint.framework.web.system.expression.node.EntityPath;
import com.icepoint.framework.web.system.expression.node.Literal;
import com.icepoint.framework.web.system.expression.node.LogicConnector;
import com.icepoint.framework.web.system.expression.node.Operator;
import org.springframework.expression.ParseException;


/**
 * @author Jiawei Zhao
 */
public class ExpressionParser {

    public static final ExpressionParser INSTANCE = new ExpressionParser();

    private static final String EXPRESSION_SEPARATOR_PREFIX = "(";
    private static final String EXPRESSION_SEPARATOR_SUFFIX = ")";

    private ExpressionParser() {
    }

    public Expression parseExpression(final String expressionString, ExpressionContext context) throws ParseException {

        OperatorSearcher operatorSearcher = new OperatorSearcher(expressionString, context);
        ConnectorSearcher connectorSearcher = new ConnectorSearcher(expressionString, context);
        int parsePos = 0;

        ChainExpression expression = new ChainExpression(expressionString);

        while (parsePos < expressionString.trim().length()) {
            parsePos = doParseExpression(expressionString, context,
                    operatorSearcher, connectorSearcher, parsePos, expression);
        }

        return expression;
    }

    private int doParseExpression(String expressionString, ExpressionContext context,
            OperatorSearcher operatorSearcher, ConnectorSearcher connectorSearcher, int parsePos,
            ChainExpression expression) {

        String trimmed = expressionString.trim();
        final int fromIndex = parsePos;

        if (trimmed.startsWith(EXPRESSION_SEPARATOR_PREFIX)) {
            // TODO
        } else {

            LogicConnector connector = connectorSearcher.first(parsePos);
            Operator operator = operatorSearcher.first(parsePos);

            parsePos = connector == null ? expressionString.length() : connector.getEndPosition();
            int expressionEndPos = connector == null ? expressionString.length() : connector.getStartPosition();

            if (operator == null) {

                EntityPath path = getEntityPath(expressionString, fromIndex, expressionEndPos);
                expression.add(new EntityPathOnlyExpression(expressionString, path), connector);

            } else {

                EntityPath path = getEntityPath(expressionString, fromIndex, operator.getStartPosition());

                Literal literal = context.getLiteral(
                        operator,
                        expressionString.substring(operator.getEndPosition(), expressionEndPos),
                        operator.getEndPosition(),
                        expressionEndPos);

                expression.add(new SingleExpression(expressionString, path, operator, literal), connector);
            }
        }

        return parsePos;
    }

    private EntityPath getEntityPath(final String expressionString, int fromIndex, int toIndex) {
        return new EntityPath(expressionString.substring(fromIndex, toIndex), fromIndex, toIndex);
    }
}
