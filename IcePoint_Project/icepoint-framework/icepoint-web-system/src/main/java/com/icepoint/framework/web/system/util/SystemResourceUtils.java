package com.icepoint.framework.web.system.util;

import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.expression.node.*;
import com.icepoint.framework.web.system.resource.ResourceModel;
import com.icepoint.framework.web.system.resource.query.Operation;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.icepoint.framework.web.system.resource.query.Operation.*;

/**
 * @author Jiawei Zhao
 */
public class SystemResourceUtils {

    private SystemResourceUtils() {
    }

    /**
     * 获取字段元数据的交互字段名，也就是相当于前端返回数据的字段名和实体类的字段名
     *
     * @param fieldMetadata 字段元数据
     * @return 字段元数据的交互字段名
     */
    public static String getFieldName(FieldMetadata fieldMetadata) {
        return fieldMetadata.getNameEn();
    }

    public static String getColumnName(FieldMetadata fieldMetadata) {
        return fieldMetadata.getStoreNameEn();
    }

    public static FieldMetadata getRequiredUniquePrimaryKey(Iterable<FieldMetadata> fields) {
        List<FieldMetadata> results = Streams.stream(fields)
                .filter(FieldMetadata::getPrimaryKey)
                .collect(Collectors.toList());

        Assert.notEmpty(results, "找不到主键字段元数据");
        Assert.isTrue(results.size() == 1, "主键字段元数据大于1个");
        return results.get(0);
    }

    public static Optional<FieldMetadata> getUniqueDeleteMarker(Iterable<FieldMetadata> fields) {
        List<FieldMetadata> results = Streams.stream(fields)
                .filter(FieldMetadata::getDeleteMarker)
                .collect(Collectors.toList());

        Assert.isTrue(results.size() <= 1, "逻辑删除标识字段元数据大于1个");

        return Optional.of(results)

                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0));
    }

    public static Operation convert(Operator operator) {

        Class<? extends Operator> clazz = operator.getClass();

        if (clazz == OpEQ.class) {
            return EQ;
        } else if (clazz == OpNE.class) {
            return NE;
        } else if (clazz == OpGT.class) {
            return GT;
        } else if (clazz == OpGE.class) {
            return GE;
        } else if (clazz == OpLT.class) {
            return LT;
        } else if (clazz == OpLE.class) {
            return LE;
        } else if (clazz == OpIn.class) {
            return IN;
        } else if (clazz == OpNotIn.class) {
            return NOT_IN;
        } else if (clazz == OpBetween.class) {
            return BETWEEN;
        } else if (clazz == OpLike.class) {
            return LIKE;
        } else {
            throw new IllegalArgumentException("不支持的条件类型");
        }
    }

    public static void validateRequiredProperties(ResourceModel model, boolean withoutId) {

        List<FieldMetadata> fields = model.getLookup().getMetadata().getFields();

        fields.forEach(field -> {

            if (withoutId && Boolean.TRUE.equals(field.getPrimaryKey())) {
                return;
            }

            Object value = model.getProperty(getFieldName(field));

            if (Boolean.FALSE.equals(field.getOptional()) && value == null) {
                throw new IllegalArgumentException("'" + field.getName() + "'不能为空");
            }

            // TODO: 增加类型验证
            // TODO: 增加其他约束验证
        });
    }

    public static boolean isBigValueField(FieldMetadata fieldMetadata) {
        return "TEXT".equalsIgnoreCase(fieldMetadata.getNativeType());
    }
}
