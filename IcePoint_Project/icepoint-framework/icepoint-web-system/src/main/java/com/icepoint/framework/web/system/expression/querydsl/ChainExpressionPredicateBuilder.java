package com.icepoint.framework.web.system.expression.querydsl;

import com.icepoint.framework.core.util.NullablePair;
import com.icepoint.framework.web.system.expression.ChainExpression;
import com.icepoint.framework.web.system.expression.Expression;
import com.icepoint.framework.web.system.expression.ExpressionContext;
import com.icepoint.framework.web.system.expression.node.LogicConnector;
import com.icepoint.framework.web.system.expression.node.LogicalAnd;
import com.icepoint.framework.web.system.expression.node.LogicalOr;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.util.ClassTypeInformation;

/**
 * @author Jiawei Zhao
 */
public class ChainExpressionPredicateBuilder extends AbstractExpressionPredicateBuilder<ChainExpression> {


    public ChainExpressionPredicateBuilder(ExpressionPredicateBuilderAdapter adapter) {
        super(adapter);
    }

    @Override
    public Predicate getPredicate(ClassTypeInformation<?> type, ChainExpression expression, ExpressionContext context) {

        BooleanBuilder builder = null;
        LogicConnector connector = null;

        for (NullablePair<Expression, LogicConnector> pair : expression) {

            Expression expr = pair.getFirst();

            Predicate predicate = getAdapter().get(expr).getPredicate(type, expr, context);

            if (builder == null) {
                builder = new BooleanBuilder(predicate);
                connector = pair.getSecond();
                continue;
            }

            if (connector == null) {
                throw new IllegalArgumentException("运算符为空");
            } else if (connector.getClass() == LogicalAnd.class) {
                builder.and(predicate);
            } else if (connector.getClass() == LogicalOr.class) {
                builder.or(predicate);
            } else {
                throw new IllegalArgumentException("不支持的运算符类型: " + connector.getSign());
            }
        }

        return builder == null ? new BooleanBuilder() : builder;
    }

}
