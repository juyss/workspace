package com.icepoint.framework.web.system.expression.querydsl;

import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.web.system.expression.ExpressionContext;
import com.icepoint.framework.web.system.expression.SingleExpression;
import com.icepoint.framework.web.system.expression.node.EntityPath;
import com.icepoint.framework.web.system.expression.node.ExpressionNode;
import com.icepoint.framework.web.system.expression.node.Literal;
import com.icepoint.framework.web.system.expression.node.Operator;
import com.querydsl.core.types.Predicate;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.util.Assert;

/**
 * @author Jiawei Zhao
 */
public class SingleExpressionPredicateBuilder extends AbstractExpressionPredicateBuilder<SingleExpression> {

    public SingleExpressionPredicateBuilder(ExpressionPredicateBuilderAdapter adapter) {
        super(adapter);
    }

    @Override
    public Predicate getPredicate(ClassTypeInformation<?> type, SingleExpression expression,
            ExpressionContext context) {

        ExpressionNode leftOperand = expression.getLeftOperand();
        ExpressionNode rightOperand = expression.getRightOperand();
        Operator operator = expression.getOperator();

        Assert.isInstanceOf(EntityPath.class, leftOperand, "左侧必须是实体路径");
        Assert.isInstanceOf(Literal.class, rightOperand, "右侧必须是字面量");
        Assert.notNull(operator, MessageTemplates.notNull("条件"));

        return getAdapter().operator().getPredicate((EntityPath) leftOperand, operator, (Literal) rightOperand, type);
    }
}
