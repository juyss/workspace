package com.icepoint.framework.web.system.expression.querydsl;

import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.web.system.expression.ChainExpression;
import com.icepoint.framework.web.system.expression.Expression;
import com.icepoint.framework.web.system.expression.SingleExpression;
import org.springframework.util.Assert;

/**
 * 根据不同的情况适配不同的{@link ExpressionPredicateBuilder}
 *
 * @author Jiawei Zhao
 */
public class ExpressionPredicateBuilderAdapter {

    public static final ExpressionPredicateBuilderAdapter INSTANCE = new ExpressionPredicateBuilderAdapter();

    public OperatorPredicateBuilder operator() {
        return OperatorPredicateBuilder.INSTANCE.get();
    }

    public <E extends Expression> ExpressionPredicateBuilder<E> get(Class<E> expressionType) {

        Assert.notNull(expressionType, MessageTemplates.notNull("expressionType"));

        if (expressionType == SingleExpression.class) {
            return CastUtils.cast(new SingleExpressionPredicateBuilder(this));
        } else if (expressionType == ChainExpression.class) {
            return CastUtils.cast(new ChainExpressionPredicateBuilder(this));
        }else {
            throw new IllegalArgumentException("不适配的表达式类型: " + expressionType);
        }
    }

    public <E extends Expression> ExpressionPredicateBuilder<E> get(E expression) {

        Assert.notNull(expression, MessageTemplates.notNull("expression"));

        return CastUtils.cast(get(expression.getClass()));
    }
}
