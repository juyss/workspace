package com.icepoint.framework.web.system.expression.querydsl;

import com.icepoint.framework.core.util.CastUtils;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.CollectionPathBase;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.function.Supplier;

/**
 * @author Jiawei Zhao
 */
public class QEntityPredicateBuilder {

    private static final String CANNOT_RESOLVE_PATH = "\"无法解析实体路径: %s";

    private final EntityPathResolver resolver;

    public QEntityPredicateBuilder() {
        this(SimpleEntityPathResolver.INSTANCE);
    }

    public QEntityPredicateBuilder(EntityPathResolver resolver) {
        this.resolver = resolver;
    }

    public Predicate getPredicate(PropertyPath propertyPath, Ops ops, @Nullable Constant<?> constant) {

        Path<?> root = resolver.createPath(propertyPath.getOwningType().getType());

        String[] paths = propertyPath.toDotPath().split("\\.");

        Path<?> result = root;
        for (String path : paths) {
            result = getLeafPath(result, path);
        }

        return constant == null
                ? ExpressionUtils.predicate(ops, result)
                : ExpressionUtils.predicate(ops, result, constant);
    }

    private Path<?> getLeafPath(Path<?> root, String property) {

        Path<?> path = getProperty(root, property);
        if (path instanceof CollectionPathBase) {
            return CastUtils.cast(((CollectionPathBase<?, ?, ?>) path).any());
        } else {
            return path;
        }
    }

    private Path<?> getProperty(Object obj, String fieldName) {

        Field field = ReflectionUtils.findField(obj.getClass(), fieldName);

        Supplier<String> messageSuppler = () -> String.format(CANNOT_RESOLVE_PATH, fieldName);

        Assert.notNull(field, messageSuppler);
        Assert.isAssignable(Path.class, field.getType(), messageSuppler);

        Object property = ReflectionUtils.getField(field, obj);
        Assert.notNull(property, messageSuppler);

        return CastUtils.cast(property);
    }
}
