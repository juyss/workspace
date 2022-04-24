package com.icepoint.framework.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icepoint.framework.core.support.JsonAnyProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.beans.Transient;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 * 可以构建为树状结构的类，在使用时不需要继承此类，只需要设置要建立树状结构的实体类为泛型即可，
 * 要求必须实现{@link ParentIdHierarchy}
 *
 * @author Jiawei Zhao
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class TreeNode<E extends Identifiable<?> & ParentIdHierarchy<?>>
        extends JsonAnyProperties<Object>
        implements ChildrenHierarchy<TreeNode<E>> {

    /*
     * 子节点列表
     */
    private List<TreeNode<E>> children;

    private final Class<E> entityType;

    public TreeNode(Map<String, Object> properties, Class<E> entityType) {
        super(properties);
        this.entityType = entityType;
    }

    public TreeNode(Class<E> entityType) {
        super();
        this.entityType = entityType;
    }

    @JsonIgnore
    public E getEntity() {
        E entity = BeanUtils.instantiateClass(entityType);
        getPropertyMap().forEach((key, value) -> {
            if (ReflectionUtils.findField(entityType, key) != null) {
                PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(entityType, key);
                if (descriptor != null) {
                    ReflectionUtils.invokeMethod(descriptor.getWriteMethod(), entity, value);
                }
            }
        });

        return entity;
    }

    @JsonIgnore
    public void setEntity(E entity) {
        Assert.notNull(entity, "entity must not be null");

        getPropertyMap().clear();
        ReflectionUtils.doWithFields(
                entity.getClass(),
                field -> {
                    String fieldName = field.getName();
                    PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(entity.getClass(), fieldName);
                    if (descriptor != null) {
                        Object fieldValue = ReflectionUtils.invokeMethod(descriptor.getReadMethod(), entity);
                        if (fieldValue != null) {
                            setProperty(fieldName, fieldValue);
                        }
                    }
                },
                field -> !Modifier.isTransient(field.getModifiers())
                        && !Modifier.isStatic(field.getModifiers())
                        && field.getDeclaredAnnotation(Transient.class) == null
        );
    }

}
