package com.icepoint.framework.web.system.expression;

import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.web.system.expression.node.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;

/**
 * @author Jiawei Zhao
 */
@Component
public class DefaultExpressionContext implements ExpressionContext {

    private static final Operator[] OPERATORS;
    private static final LogicConnector[] CONNECTORS;
    private static final String SEPARATOR = ",";

    static {
        OPERATORS = new Operator[]{
                new OpEQ(0, 0),
                new OpNE(0, 0),
                new OpGT(0, 0),
                new OpGE(0, 0),
                new OpLT(0, 0),
                new OpLE(0, 0),
                new OpLike(0, 0),
                new OpBetween(0, 0),
                new OpIn(0, 0),
                new OpNotIn(0, 0)
        };

        CONNECTORS = new LogicConnector[]{
                new LogicalAnd(0, 0),
                new LogicalOr(0, 0)
        };
    }

    @Override
    public String[] getOperatorNames() {
        return Arrays.stream(OPERATORS)
                .map(Operator::getName)
                .toArray(String[]::new);
    }

    @Override
    public Operator newOperator(String name, int startPos, int endPos) {

        Assert.hasText(name, "Operator名称不能为空");

        switch (name) {
            case "eq":
                return new OpEQ(startPos, endPos);
            case "ne":
                return new OpNE(startPos, endPos);
            case "gt":
                return new OpGT(startPos, endPos);
            case "ge":
                return new OpGE(startPos, endPos);
            case "lt":
                return new OpLT(startPos, endPos);
            case "le":
                return new OpLE(startPos, endPos);
            case "like":
                return new OpLike(startPos, endPos);
            case "between":
                return new OpBetween(startPos, endPos);
            case "in":
                return new OpIn(startPos, endPos);
            case "notin":
                return new OpNotIn(startPos, endPos);
            default:
                throw new UnsupportedOperationException("不支持的Operator类型: " + name);
        }
    }

    @Override
    public String[] getLogicConnectorSigns() {
        return Arrays.stream(CONNECTORS)
                .map(LogicConnector::getSign)
                .toArray(String[]::new);
    }

    @Override
    public LogicConnector newLogicConnector(String sign, int startPos, int endPos) {

        Assert.hasText(sign, "连接符不能为空");

        switch (sign) {
            case "&&":
                return new LogicalAnd(startPos, endPos);
            case "||":
                return new LogicalOr(startPos, endPos);
            default:
                throw new UnsupportedOperationException("不支持的连接符类型: " + sign);
        }
    }

    @Override
    public Literal getLiteral(Operator operator, String expressionString, int startPos, int endPos) {

        Assert.notNull(operator, MessageTemplates.notNull("Operator"));

        String trimmed = expressionString.trim();
        switch (operator.getName()) {
            case "eq":
            case "ne":
            case "gt":
            case "ge":
            case "lt":
            case "le":
            case "like":
                return "null".equals(trimmed)
                        ? new NullLiteral(startPos, endPos)
                        : new StringLiteral(trimmed, startPos, endPos);
            case "between":

                String[] split = trimmed.split(SEPARATOR);
                Assert.isTrue(split.length == 2, "between的参数必须是2个");
                Literal[] literals = new Literal[split.length];

                return getArrayLiteral(split, literals, startPos, endPos);
            case "in":
            case "notin":

                split = trimmed.split(SEPARATOR);
                literals = new Literal[split.length];

                return getArrayLiteral(split, literals, startPos, endPos);
            default:
                throw new UnsupportedOperationException("不支持的Operator类型: " + operator.getName());
        }
    }

    private ArrayLiteral getArrayLiteral(String[] split, Literal[] literals, int startPos, int endPos) {

        int s = startPos;
        int e;

        for (int i = 0, splitLength = split.length; i < splitLength; i++) {

            String l = split[i];
            e = s + l.length();

            literals[i] = new StringLiteral(l.trim(), s, e);

            s = e + SEPARATOR.length();
        }

        return new ArrayLiteral(literals, startPos, endPos);
    }
}
