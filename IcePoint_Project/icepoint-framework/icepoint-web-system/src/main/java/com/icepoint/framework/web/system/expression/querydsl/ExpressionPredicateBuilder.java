package com.icepoint.framework.web.system.expression.querydsl;

import com.icepoint.framework.web.system.expression.Expression;
import com.icepoint.framework.web.system.expression.ExpressionContext;
import com.querydsl.core.types.Predicate;
import org.springframework.data.util.ClassTypeInformation;

/**
 * 将表达式{@link Expression}解析为Querydsl的{@link Predicate}
 *
 * @author Jiawei Zhao
 */
public interface ExpressionPredicateBuilder<E extends Expression> {

    Predicate getPredicate(ClassTypeInformation<?> type, E expression, ExpressionContext context);
}
