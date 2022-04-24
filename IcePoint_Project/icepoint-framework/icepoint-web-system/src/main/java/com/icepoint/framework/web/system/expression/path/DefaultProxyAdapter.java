package com.icepoint.framework.web.system.expression.path;

import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.data.util.PersistenceUtils;
import com.icepoint.framework.web.system.expression.node.EntityPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.ProxyUtils;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.lang.Nullable;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
@Slf4j
public class DefaultProxyAdapter implements ProxyAdapter {

    @Override
    public Object proxy(Class<?> rootType, Object obj, List<EntityPath> paths, @Nullable PropertyPath propertyPath,
            @Nullable PersistentEntityResourceAssembler assembler, PersistentEntities entities) {

        Class<?> clazz = ProxyUtils.getUserClass(obj.getClass());

        // 简单类型的对象不做代理，Map类型也暂时不支持
        if (BeanUtils.isSimpleProperty(clazz) || Map.class.isAssignableFrom(clazz)) {
            return obj;
        }

        // 不是实体类则去除懒加载属性
        boolean hasNoArgsConstructor = ReflectionUtils.findConstructor(clazz).isPresent();
        if (!PersistenceUtils.isPersistentEntity(clazz) && hasNoArgsConstructor) {
            return Enhancer.create(
                    clazz,
                    new UninitializedLazyPropertiesIgnoringProxy(obj, this, ClassTypeInformation.from(rootType), entities));
        }

        if (!hasNoArgsConstructor) {
            return obj;
        }

        // 如果是集合类型，遍历其中的元素，逐个进行代理适配
        Collection<?> collection;
        if (Collection.class.isAssignableFrom(clazz) && !(collection = (Collection<?>) obj).isEmpty()) {

            List<?> list = collection.stream()
                    .map(it -> proxy(rootType, obj, paths, propertyPath, assembler, entities))
                    .collect(Collectors.toList());

            collection.clear();
            collection.addAll(CastUtils.cast(list));

            return collection;
        }

        // final类无法进行代理，忽略
        if (Modifier.isFinal(clazz.getModifiers())) {

            if (log.isDebugEnabled()) {
                String pathsParameterName = EntityPathProxyResponseProcessor.PATHS;
                log.debug("该类型[{}]是final类，无法进行代理，{}参数将无法处理，如果不是关联实体或者不是{}参数相关的，可以忽略此日志",
                        clazz, pathsParameterName, pathsParameterName);
            }

            return obj;
        }

        return Enhancer.create(
                clazz,
                new EntityPathProxy(paths, obj, rootType, propertyPath, this, assembler, entities)
        );
    }
}
