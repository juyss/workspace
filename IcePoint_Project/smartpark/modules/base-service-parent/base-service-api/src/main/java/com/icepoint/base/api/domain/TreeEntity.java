package com.icepoint.base.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.beans.Transient;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * 可以构建为树状结构的实体类，在使用时不需要继承此类，只需要设置要建立树状结构的实体类为泛型即可，
 * 要求必须实现{@link ParentIdHierarchy}
 *
 * @author Jiawei Zhao
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeEntity<E extends Identifiable<?> & ParentIdHierarchy>
        extends JsonAnyProperties<Object>
        implements ChildrenHierarchy<TreeEntity<E>> {

    /*
     * 子节点列表
     */
    private List<TreeEntity<E>> children;

    @JsonIgnore
    @SuppressWarnings("unchecked")
    public E getEntity() {
        ResolvableType resolvableType = ResolvableType.forClass(this.getClass());
        Class<E> entityClz = (Class<E>) resolvableType.getGeneric(0).resolve();

        if (entityClz == null)
            throw new IllegalStateException("泛型获取异常: " + this.getClass());

        E entity = BeanUtils.instantiateClass(entityClz);
        getPropertyMap().forEach((key, value) -> {
            if (ReflectionUtils.findField(entityClz, key) != null) {
                PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(entityClz, key);
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
