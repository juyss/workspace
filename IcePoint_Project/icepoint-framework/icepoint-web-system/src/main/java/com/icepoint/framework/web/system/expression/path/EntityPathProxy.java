package com.icepoint.framework.web.system.expression.path;

import com.icepoint.framework.core.util.FieldUtils;
import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.data.util.PersistenceUtils;
import com.icepoint.framework.web.system.expression.node.EntityPath;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.Lazy;
import org.springframework.data.util.ProxyUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 通过代理的方式，动态管理Hibernate的实体代理类的数据懒加载功能，根据传入的paths来决定是否让Hibernate进行代理
 *
 * @author Jiawei Zhao
 */
public class EntityPathProxy implements InvocationHandler {

    public static final String ROOT_TYPE = EntityPathProxy.class + ".ROOT_TYPE";

    private final List<EntityPath> originPaths;

    private final List<EntityPath> includingPaths;

    private final boolean unproxyAll;

    private final boolean isRoot;

    private final PropertyPath propertyPathFromRoot;

    private final ProxyAdapter adapter;

    private final Object entity;

    private final ClassTypeInformation<?> entityUserType;

    private final PropertyDescriptor[] skipPds;

    private final Lazy<PropertyDescriptor[]> includingPds;

    private final Lazy<String[]> lazyAssociationProperties;

    private final ClassTypeInformation<?> rootType;

    private final PersistentEntityResourceAssembler assembler;

    private final PersistentEntities entities;

    public EntityPathProxy(@Nullable List<EntityPath> includes, Object entity, Class<?> rootType,
            @Nullable PropertyPath propertyPathFromRoot, ProxyAdapter adapter,
            @Nullable PersistentEntityResourceAssembler assembler,
            PersistentEntities entities) {

        Assert.notNull(entity, MessageTemplates.notNull("entity"));
        Assert.notNull(adapter, MessageTemplates.notNull("adapter"));
        Assert.notNull(rootType, MessageTemplates.notNull("rootType"));

        this.assembler = assembler;
        this.entity = entity;
        this.entityUserType = ClassTypeInformation.from(ProxyUtils.getUserClass(this.entity.getClass()));
        this.rootType = ClassTypeInformation.from(rootType);
        this.adapter = adapter;
        this.propertyPathFromRoot = propertyPathFromRoot;
        this.isRoot = this.propertyPathFromRoot == null;
        this.entities = entities;

        this.skipPds = BeanUtils.getPropertyDescriptors(HibernateProxy.class);
        this.includingPaths = this.isRoot ? includes : filterMatches(includes);
        this.originPaths = CollectionUtils.isEmpty(includes) ? Collections.emptyList() : includes;
        this.unproxyAll = CollectionUtils.isEmpty(includingPaths);

        this.includingPds = Lazy.of(() -> BeanUtils.getPropertyDescriptors(this.entityUserType.getType()))
                .or(() -> new PropertyDescriptor[0]);

        this.lazyAssociationProperties = Lazy.of(() -> Arrays
                .stream(FieldUtils.findFields(this.entityUserType.getType(), PersistenceUtils::isLazyAssociationField))
                .map(Field::getName)
                .toArray(String[]::new));
    }

    @Nullable
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        PropertyDescriptor pd = BeanUtils.findPropertyForMethod(method);
        if (pd == null || skip(pd)) {
            return doInvoke(entity, method, args, null);
        }

        String propertyName = pd.getName();
        boolean lazyAssociationProperty = isLazyAssociationProperty(pd);

        // 如果不是懒加载关联实体属性, 不做处理, 由原对象执行方法
        if (!lazyAssociationProperty) {
            return doInvoke(entity, method, args, pd);
        }

        // 全部懒加载的关联实体属性一律返回null
        if (unproxyAll) {
            return null;
        }

        // 如果是匹配的path, 由原对象执行
        for (EntityPath entityPath : includingPaths) {
            if (matches(entityPath, propertyName)) {
                return doInvoke(entity, method, args, pd);
            }
        }

        // 既是懒加载关联实体属性又没有匹配的path，代表是不需要查询的关联实体，直接返回null
        return null;

    }

    @Nullable
    private Object doInvoke(@Nullable Object target, Method method, Object[] args,
            @Nullable PropertyDescriptor pd) throws Throwable {

        try {

            Object result = ReflectionUtils.invokeMethod(method, target, args);

            // TODO: Hateoas标准实现
//            if (result != null) {
//
//                if (entities.getPersistentEntity(clazz).isPresent()) {
//                    result = assembler.toFullResource(result);
//                }
//
//            }

            return result == null
                    ? null
                    : adapter.proxy(rootType.getType(), result, originPaths, getPropertyPath(pd), assembler, entities);

        } catch (RuntimeException e) {

            rethrowException(e);
            return null;
        }
    }

    private List<EntityPath> filterMatches(@Nullable List<EntityPath> paths) {

        if (CollectionUtils.isEmpty(paths)) {
            return Collections.emptyList();
        }

        if (isRoot) {
            return paths;
        }

        return paths.stream()
                .filter(path -> {
                    String joinedPaths = String.join(".", path.getPaths());
                    String dotPath = propertyPathFromRoot.toDotPath();
                    return joinedPaths.startsWith(dotPath) && joinedPaths.length() > dotPath.length();
                })
                .collect(Collectors.toList());
    }

    private boolean matches(EntityPath entityPath, String propertyName) {

        String[] paths = entityPath.getPaths();
        Assert.notEmpty(paths, "实体路径为空");

        if (isRoot || propertyPathFromRoot == null) {
            return Objects.equals(paths[0], propertyName);
        }

        String[] propertyPaths = propertyPathFromRoot.toDotPath().split("\\.");
        Assert.isTrue(paths.length > propertyPaths.length, "impossible");

        for (int i = 0; i < paths.length; i++) {

            if (i == propertyPaths.length) {
                return Objects.equals(paths[i], propertyName);
            }

            if (!Objects.equals(paths[i], propertyPaths[i])) {
                return false;
            }
        }

        return false;
    }

    private void rethrowException(RuntimeException e) throws Throwable {

        Throwable cause = e.getCause();
        if (cause == null) {
            throw e;
        }

        if (cause instanceof IllegalStateException) {
            cause = cause.getCause();
            if (cause == null) {
                throw e;
            }
        }

        if (cause instanceof InvocationTargetException) {
            throw ((InvocationTargetException) cause).getTargetException();
        }

        throw e;
    }

    private boolean skip(PropertyDescriptor pd) {

        for (PropertyDescriptor includingPd : includingPds.get()) {

            if (includingPd.getName().equals(pd.getName()) && skipPds.length != 0) {

                for (PropertyDescriptor skipPd : skipPds) {
                    if (skipPd.getName().equals(pd.getName())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isLazyAssociationProperty(PropertyDescriptor pd) {
        return ArrayUtils.contains(lazyAssociationProperties.get(), pd.getName());
    }

    @Nullable
    private PropertyPath getPropertyPath(@Nullable PropertyDescriptor pd) {

        if (pd == null) {
            return null;
        }

        String propertyName = pd.getName();

        if (isRoot || propertyPathFromRoot == null) {
            return PropertyPath.from(propertyName, rootType);
        } else {

            // TODO: 可能会导致path匹配有问题
            return PropertyPath.from(propertyName, entityUserType);
        }
    }
}
