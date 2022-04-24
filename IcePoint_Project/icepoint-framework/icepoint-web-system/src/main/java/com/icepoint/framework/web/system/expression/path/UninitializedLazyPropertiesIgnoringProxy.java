package com.icepoint.framework.web.system.expression.path;

import com.icepoint.framework.core.util.BeanUtils;
import com.icepoint.framework.core.util.FieldUtils;
import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.data.util.PersistenceUtils;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.ProxyUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;

/**
 * 忽略全部没有被加载的Lazy属性并赋值null
 *
 * @author Jiawei Zhao
 */
public class UninitializedLazyPropertiesIgnoringProxy implements InvocationHandler {

    private final ClassTypeInformation<?> rootType;
    private final Object target;
    private final String[] lazyFieldNames;
    private final ProxyAdapter adapter;
    private final PersistentEntities persistentEntities;

    public UninitializedLazyPropertiesIgnoringProxy(Object target, ProxyAdapter adapter,
            ClassTypeInformation<?> rootType, PersistentEntities persistentEntities) {
        this.rootType = rootType;
        this.target = target;
        Class<?> entityType = ProxyUtils.getUserClass(target);
        this.persistentEntities = persistentEntities;
        this.lazyFieldNames = Streams.stream(FieldUtils.findFields(entityType))
                .filter(PersistenceUtils::isLazyAssociationField)
                .map(Field::getName)
                .toArray(String[]::new);
        this.adapter = adapter;
    }

    @Nullable
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (ReflectionUtils.isObjectMethod(method)) {
            return doInvoke(target, method, args);
        }

        PropertyDescriptor pd = BeanUtils.findPropertyForMethod(method);
        if (pd == null) {
            return doInvoke(target, method, args);
        }

        boolean isLazyField = Streams.stream(lazyFieldNames)
                .anyMatch(pd.getName()::equals);

        if (!isLazyField) {
            return doInvoke(target, method, args);
        }

        Object result = doInvoke(target, method, args);
        if (result instanceof HibernateProxy) {
            HibernateProxy p = (HibernateProxy) result;
            if (p.getHibernateLazyInitializer().isUninitialized()) {
                return null;
            }
        }

        return result;
    }

    @Nullable
    private Object doInvoke(@Nullable Object target, Method method, Object[] args) {
        Object result = ReflectionUtils.invokeMethod(method, target, args);
        return result == null
                ? null
                : adapter.proxy(rootType.getType(), result, Collections.emptyList(), null, null, persistentEntities);
    }
}
