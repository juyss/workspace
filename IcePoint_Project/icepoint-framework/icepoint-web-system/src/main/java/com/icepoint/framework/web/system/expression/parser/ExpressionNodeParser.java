package com.icepoint.framework.web.system.expression.parser;

import com.icepoint.framework.web.system.expression.node.ExpressionNode;
import org.springframework.expression.ParseException;

/**
 * @author Jiawei Zhao
 */
public interface ExpressionNodeParser<N extends ExpressionNode> {

    N parse(String expression) throws ParseException;
}
