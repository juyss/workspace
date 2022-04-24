package com.icepoint.framework.web.system.expression;

import com.icepoint.framework.core.util.MessageTemplates;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * @author Jiawei Zhao
 */
public class QueryExpressionUtils {

    private QueryExpressionUtils() {}

    public static boolean hasPropertyPath(String path, ClassTypeInformation<?> type) {
        return getPropertyPath(path, type) != null;
    }

    @Nullable
    public static PropertyPath getPropertyPath(String path, ClassTypeInformation<?> type) {
        try {
            return PropertyPath.from(path, type);
        } catch (PropertyReferenceException e) {
            return null;
        }
    }

    public static PropertyPath getRequiredPropertyPath(String path, ClassTypeInformation<?> type) {
        PropertyPath propertyPath = getPropertyPath(path, type);
        Assert.notNull(propertyPath, "不存在该实体路径: " + path);
        return propertyPath;
    }

    public static <T> ClassTypeInformation<T> getType(Class<T> domainType) {
        Assert.notNull(domainType, MessageTemplates.notNull("domainType"));
        return ClassTypeInformation.from(domainType);
    }
}
