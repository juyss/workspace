package com.icepoint.framework.web.system.expression.querydsl;

import com.icepoint.framework.core.util.ApplicationContextUtils;
import com.icepoint.framework.core.util.SimpleTypeUtils;
import com.icepoint.framework.web.system.expression.QueryExpressionUtils;
import com.icepoint.framework.web.system.expression.node.*;
import com.querydsl.core.types.Constant;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Predicate;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.Lazy;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
public class OperatorPredicateBuilder {

    public static final Lazy<OperatorPredicateBuilder> INSTANCE = Lazy.of(() -> {
        ConversionService service = ApplicationContextUtils.getRequiredBean("mvcConversionService", ConversionService.class);
        return new OperatorPredicateBuilder(service);
    });

    private static final EntityPathResolver ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

    private final ConversionService conversionService;

    private final QEntityPredicateBuilder delegate;

    private OperatorPredicateBuilder(ConversionService conversionService) {
        this.conversionService = conversionService;
        this.delegate = new QEntityPredicateBuilder(ENTITY_PATH_RESOLVER);
    }

    public Predicate getPredicate(EntityPath entityPath, Operator operator, Literal literal,
            ClassTypeInformation<?> type) {

        PropertyPath propertyPath = QueryExpressionUtils.getRequiredPropertyPath(
                String.join(".", entityPath.getPaths()), type
        );

        Class<?> leafType = propertyPath.getLeafType();
        Assert.isTrue(SimpleTypeUtils.isPrimitiveOrBoxed(leafType), "该path不是基础属性, 可能是关联实体");

        return doGetPredicate(propertyPath, operator, literal);
    }

    private Predicate doGetPredicate(PropertyPath propertyPath, Operator operator, Literal literal) {
        return literal.getClass() == NullLiteral.class
                ? delegate.getPredicate(propertyPath, Ops.IS_NULL, null)
                : delegate.getPredicate(propertyPath, getOperator(operator), getConstant(literal, propertyPath));
    }

    private Constant<?> getConstant(Literal literal, PropertyPath propertyPath) {

        Class<? extends Literal> literalClass = literal.getClass();
        TypedValue typedValue = literal.getLiteralValue();
        Object value = typedValue.getValue();

        Class<?> valueClass = value.getClass();
        Class<?> pathType = propertyPath.getLeafType();

        if (conversionService.canConvert(valueClass, pathType)) {
            Object result = conversionService.convert(value, pathType);
            Assert.notNull(result, "字面量解析为空");
            return ConstantImpl.create(result);
        } else if (literalClass == IntLiteral.class)
            return ConstantImpl.create(SimpleTypeUtils.parseInt(typedValue.getValueAs()));
        else if (literalClass == LongLiteral.class)
            return ConstantImpl.create(SimpleTypeUtils.parseLong(typedValue.getValueAs()));
        else if (literalClass == DoubleLiteral.class)
            return ConstantImpl.create(SimpleTypeUtils.parseDouble(typedValue.getValueAs()));
        else if (literalClass == BooleanLiteral.class)
            return ConstantImpl.create(SimpleTypeUtils.parseBoolean(typedValue.getValueAs()));
        else if (literalClass == ArrayLiteral.class) {

            Literal[] literals = typedValue.getValueAs();
            List<Object> realValue = Arrays.stream(literals)
                    .map(l -> l.getLiteralValue().getValue())
                    .collect(Collectors.toList());

            return ConstantImpl.create(realValue);
        } else {
            return ConstantImpl.create(value);
        }
    }

    private Ops getOperator(Operator operator) {

        Class<? extends Operator> clazz = operator.getClass();

        if (clazz == OpEQ.class)
            return Ops.EQ;
        else if (clazz == OpNE.class)
            return Ops.NE;
        else if (clazz == OpGT.class)
            return Ops.GT;
        else if (clazz == OpGE.class)
            return Ops.GOE;
        else if (clazz == OpLT.class)
            return Ops.LT;
        else if (clazz == OpLE.class)
            return Ops.LOE;
        else if (clazz == OpLike.class)
            return Ops.LIKE;
        else if (clazz == OpBetween.class)
            return Ops.BETWEEN;
        else if (clazz == OpIn.class)
            return Ops.IN;
        else if (clazz == OpNotIn.class)
            return Ops.NOT_IN;
        else
            throw new IllegalArgumentException("不支持的Operator: " + operator.getName());
    }

}
