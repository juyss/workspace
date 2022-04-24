package com.icepoint.framework.web.system.support;

import com.icepoint.framework.core.util.NullablePair;
import com.icepoint.framework.web.system.expression.ChainExpression;
import com.icepoint.framework.web.system.expression.Expression;
import com.icepoint.framework.web.system.expression.ExpressionContext;
import com.icepoint.framework.web.system.expression.SingleExpression;
import com.icepoint.framework.web.system.expression.node.*;
import com.icepoint.framework.web.system.expression.parser.ExpressionParser;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceModelMetadata;
import com.icepoint.framework.web.system.resource.ViewType;
import com.icepoint.framework.web.system.resource.builder.DefaultLookupBuilder;
import com.icepoint.framework.web.system.resource.query.*;
import com.icepoint.framework.web.system.util.SystemResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
public class LookupHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public static final String PARAMETER_NAME = "_query";

    public static final String VIEW_TYPE_NAME = "_view";

    private final ResourceModelMetadataHandlerMethodArgumentResolver resourceMetadataResolver;

    private final ExpressionParser expressionParser;

    private final ExpressionContext expressionContext;

    @Autowired
    public LookupHandlerMethodArgumentResolver(
            ResourceModelMetadataHandlerMethodArgumentResolver resourceMetadataResolver,
            ExpressionContext expressionContext) {
        this(resourceMetadataResolver, ExpressionParser.INSTANCE, expressionContext);
    }

    public LookupHandlerMethodArgumentResolver(
            ResourceModelMetadataHandlerMethodArgumentResolver resourceMetadataResolver,
            ExpressionParser expressionParser,
            ExpressionContext expressionContext) {
        this.resourceMetadataResolver = resourceMetadataResolver;
        this.expressionParser = expressionParser;
        this.expressionContext = expressionContext;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Lookup.class == parameter.getParameterType();
    }

    @NonNull
    @Override
    public Lookup resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

        String queryParamStr = webRequest.getParameter(PARAMETER_NAME);
        String viewTypeStr = webRequest.getParameter(VIEW_TYPE_NAME);

        ViewType viewType = StringUtils.hasText(viewTypeStr)
                ? ViewType.valueOf(viewTypeStr.toUpperCase())
                : ViewType.NO_VIEW;

        ResourceModelMetadata metadata = resourceMetadataResolver
                .resolveArgument(parameter, mavContainer, webRequest, binderFactory);

        DefaultLookupBuilder builder = DefaultLookupBuilder.of(metadata, viewType);

        if (StringUtils.hasText(queryParamStr)) {
            builder.query(parseQuery(queryParamStr));
        }

        return builder.build();
    }

    private Query parseQuery(String queryParamStr) {
        Expression expression = expressionParser.parseExpression(queryParamStr, expressionContext);

        List<Parameter> parameters = new ArrayList<>();

        if (expression instanceof SingleExpression) {
            parameters.add(parseSingleExpression((SingleExpression) expression, LogicCondition.AND));

        } else if (expression instanceof ChainExpression) {
            for (NullablePair<Expression, LogicConnector> pair : (ChainExpression) expression) {

                Expression expr = pair.getFirst();
                Assert.isTrue(expr instanceof SingleExpression, "暂不支持复杂的表达式");

                LogicConnector connector = pair.getSecond();

                LogicCondition condition;
                if (connector == null || connector instanceof LogicalAnd) {
                    condition = LogicCondition.AND;
                } else if (connector instanceof LogicalOr) {
                    condition = LogicCondition.OR;
                } else {
                    throw new UnsupportedOperationException("不支持的条件类型: " + connector.getClass());
                }

                parameters.add(parseSingleExpression((SingleExpression) expr, condition));
            }
        } else {
            return Query.EMPTY;
        }

        return new DefaultQuery(parameters);
    }

    private Parameter parseSingleExpression(SingleExpression expression, LogicCondition condition) {

        ExpressionNode leftOperand = expression.getLeftOperand();
        ExpressionNode rightOperand = expression.getRightOperand();
        Operator operator = expression.getOperator();
        String expressionString = expression.getExpressionString();

        Operation operation = SystemResourceUtils.convert(operator);
        String name = expressionString.substring(leftOperand.getStartPosition(), leftOperand.getEndPosition());
        String value = expressionString.substring(rightOperand.getStartPosition(), rightOperand.getEndPosition());

        value = "null".equals(value) ? null : value;

        return new Parameter(value == null ? null : value.getClass(), name.trim(), value, operation, condition);
    }
}
