package com.icepoint.base.web.resource.util;

import com.icepoint.base.api.domain.*;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
public abstract class GenericUtils {

    public static Generic getEntityAnnotation(Class<?> entityClass) {
        Assert.notNull(entityClass, "entityClass must not be null");
        return entityClass.getAnnotation(Generic.class);
    }

    public static String getResourceKey(Class<?> entityClass) {
        Generic annotation = getEntityAnnotation(entityClass);
        Assert.notNull(annotation,
                "can not find annotation GenericEntity on class: " + entityClass.getName());
        return annotation.value();
    }


    public static boolean isEmpty(@Nullable GenericEntity entity) {
        return entity == null || entity.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public static List<GenericEntity> toEntitiesFromMaps(
            ResourceMetadata metadata, List<Map<String, Object>> entitiesMap,
            SerializeType serializeType) {

        Assert.notNull(metadata, "Resource Metadata must not be null");
        Assert.notNull(serializeType, "Serialize Type must not be null");
        if (CollectionUtils.isEmpty(entitiesMap)) {
            return Collections.emptyList();
        }

        // 构建一个字段元数据信息的Map，方便后面遍历的时候获取对应的MetaField对象
        Map<String, MetaField> fieldMap = metadata.getMetaFields().stream()
                .collect(Collectors.toMap(MetaField::getStoreNameEn, field -> field));

        return entitiesMap.stream()
                .map(entityMap -> {
                    // 这个数组专门用来排序的
                    Queue<GenericProperty>[] forSort;
                    if (SerializeType.OBJECT.equals(serializeType)) {
                        int maxIdx = metadata.getMetaFields().stream()
                                .mapToInt(MetaField::getIdx)
                                .reduce(Math::max)
                                .orElse(0);

                        forSort = new ArrayDeque[maxIdx + 1];
                    } else {
                        forSort = new ArrayDeque[0];
                    }

                    MapBasedEntity entity =
                            new MapBasedEntity(metadata.getKey(), metadata.getMetaTab(), new LinkedHashMap<>());

                    entityMap.forEach((fieldName, value) -> {
                        MetaField metaField;
                        if ((metaField = fieldMap.get(fieldName)) == null) {
                            return;
                        }

                        String valueString = convertValueToString(value);
                        switch (serializeType) {
                            case VALUE:
                                entity.setProperty(
                                        new ValueGenericProperty(metaField.getNameEn(), valueString));
                                break;
                            case OBJECT:

                                Queue<GenericProperty> propertyQueue = forSort[metaField.getIdx()];
                                if (propertyQueue == null) {
                                    propertyQueue = new ArrayDeque<>();
                                    forSort[metaField.getIdx()] = propertyQueue;
                                }

                                propertyQueue.add(new MetadataGenericProperty(metaField, valueString));
                                break;
                            default:
                                throw new UnsupportedOperationException("不支持的序列化方式类型: " + serializeType);
                        }
                    });

                    if (SerializeType.OBJECT.equals(serializeType)) {
                        for (Queue<GenericProperty> propertyQueue : forSort) {
                            if (!CollectionUtils.isEmpty(propertyQueue)) {
                                for (GenericProperty property : propertyQueue) {
                                    entity.setProperty(property);
                                }
                            }
                        }
                    }

                    return entity;
                }).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public static <T extends GenericProperty> List<GenericEntity> toEntitiesFromProperties(
            ResourceMetadata metadata, Collection<T> properties,
            SerializeType serializeType, Function<T, ?> classifier) {

        Assert.notNull(metadata, "Resource Metadata must not be null");
        Assert.notNull(serializeType, "Serialize Type must not be null");
        Assert.notNull(classifier, "Classifier must not be null");
        if (CollectionUtils.isEmpty(properties)) {
            return Collections.emptyList();
        }


        Map<String, MetaField> fieldMap = metadata.getMetaFields().stream()
                .collect(Collectors.toMap(ResourceUtils::getFieldName, field -> field));

        return properties.stream()
                .collect(Collectors.groupingBy(classifier))
                .values().stream()
                .map(propertyList -> {
                    // 这个数组专门用来排序的
                    Queue<GenericProperty>[] forSort;
                    if (SerializeType.OBJECT.equals(serializeType)) {
                        int maxIdx = metadata.getMetaFields().stream()
                                .mapToInt(MetaField::getIdx)
                                .reduce(Math::max)
                                .orElse(0);

                        forSort = new ArrayDeque[maxIdx + 1];
                    } else {
                        forSort = new ArrayDeque[0];
                    }

                    MapBasedEntity entity =
                            new MapBasedEntity(metadata.getKey(), metadata.getMetaTab(), new LinkedHashMap<>());

                    propertyList.forEach(property -> {
                        MetaField metaField;
                        if ((metaField = fieldMap.get(property.getName())) == null) {
                            return;
                        }

                        String valueString = convertValueToString(property.getValue());
                        switch (serializeType) {
                            case VALUE:
                                entity.setProperty(
                                        new ValueGenericProperty(metaField.getNameEn(), valueString));
                                break;
                            case OBJECT:

                                Queue<GenericProperty> propertyQueue = forSort[metaField.getIdx()];
                                if (propertyQueue == null) {
                                    propertyQueue = new ArrayDeque<>();
                                    forSort[metaField.getIdx()] = propertyQueue;
                                }

                                propertyQueue.add(new MetadataGenericProperty(metaField, valueString));
                                break;
                            default:
                                throw new UnsupportedOperationException("不支持的序列化方式类型: " + serializeType);
                        }
                    });

                    if (SerializeType.OBJECT.equals(serializeType)) {
                        for (Queue<GenericProperty> propertyQueue : forSort) {
                            if (!CollectionUtils.isEmpty(propertyQueue)) {
                                for (GenericProperty property : propertyQueue) {
                                    entity.setProperty(property);
                                }
                            }
                        }
                    }

                    return entity;
                }).collect(Collectors.toList());
    }

    @Nullable
    public static String convertValueToString(@Nullable Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Date) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.format((Date) value);
        }

        return value.toString();
    }

    public static GenericProperty newProperty(MetaField metaField, String value, SerializeType serializeType) {
        GenericProperty newProperty;
        switch (serializeType) {
            case VALUE:
                newProperty = new ValueGenericProperty(metaField.getNameEn(), value);
                break;
            case OBJECT:
                newProperty = new MetadataGenericProperty(metaField, value);
                break;
            default:
                throw new UnsupportedOperationException(serializeType.toString());
        }
        return newProperty;
    }

}
