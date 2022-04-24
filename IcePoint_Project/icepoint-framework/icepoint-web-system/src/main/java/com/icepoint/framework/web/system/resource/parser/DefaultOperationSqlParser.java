package com.icepoint.framework.web.system.resource.parser;

import com.icepoint.framework.web.system.resource.query.Operation;
import org.springframework.util.Assert;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jiawei Zhao
 */
public class DefaultOperationSqlParser extends SqlParserSupport implements OperationSqlParser {

    @Override
    public String parse(String property, Operation op, Object value) {
        property = property.trim();
        switch (op) {
            case EQ:
                if (value == null) {
                    property += " IS NULL";
                } else {
                    property += String.format(getOperationFormatter(op), getMostSpecificString(value));
                }
                break;
            case NE:
                if (value == null) {
                    property += "IS NOT NULL";
                } else {
                    property += String.format(getOperationFormatter(op), getMostSpecificString(value));
                }
                break;
            case GT:
            case GE:
            case LT:
            case LE:
            case IN:
            case NOT_IN:
                Assert.notNull(value, "该查询条件不允许查询null值, 查询条件: " + op);
                property += String.format(getOperationFormatter(op), getMostSpecificString(value));
                break;
            case LIKE:
                property += String.format(getOperationFormatter(op), value);
                break;
            case BETWEEN:
                Assert.notNull(value, "该查询条件不允许查询null值, 查询条件: " + op);
                Object[] betweens = (Object[]) value;
                Assert.isTrue(betweens.length == 2,
                        "btw参数数量应该是要2个，但传入了" + betweens.length + "个");
                property += String.format(getOperationFormatter(op),
                        getMostSpecificString(betweens[0]), getMostSpecificString(betweens[1]));
                break;
        }
        return property;
    }

    private String getOperationFormatter(Operation operation) {
        switch (operation) {
            case EQ:
                return "= %s";
            case NE:
                return "!= %s";
            case GT:
                return "> %s";
            case GE:
                return ">= %s";
            case LT:
                return "< %s";
            case LE:
                return "<= %s";
            case IN:
                return "IN(%s)";
            case NOT_IN:
                return "NOT IN(%s)";
            case LIKE:
                return "LIKE '%s'";
            case BETWEEN:
                return "BETWEEN %s AND %s";
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
    }

    private String getMostSpecificString(Object obj) {
        if (obj instanceof String) {
            return quotes((String) obj);
        } else if (obj instanceof Number) {
            return obj.toString();
        } else if (obj instanceof Object[]) {
            return Stream.of((Object[]) obj)
                    .map(this::getMostSpecificString)
                    .collect(Collectors.joining(", "));
        } else {
            throw new UnsupportedOperationException();
        }
    }

}
