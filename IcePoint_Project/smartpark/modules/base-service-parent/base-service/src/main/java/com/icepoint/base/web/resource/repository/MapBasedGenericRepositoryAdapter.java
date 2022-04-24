package com.icepoint.base.web.resource.repository;

import com.icepoint.base.web.resource.component.metadata.ResourceType;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于Map来进行存取的资源数据仓储适配器
 *
 * @author Jiawei Zhao
 * @see ResourceType
 * @see GenericRepository
 */
@Primary
@Component
public class MapBasedGenericRepositoryAdapter implements GenericRepositoryAdapter {

    /**
     * 存储所有{@link GenericRepository}的bean的map，key是存储{@link GenericRepository}所对应的{@link ResourceType}
     */
    private final Map<ResourceType, GenericRepository> repositoriesByType = new ConcurrentHashMap<>();

    /**
     * 获取所有类型为{@link GenericRepository}的bean，以{@link ResourceType}为索引存入{@link #repositoriesByType}中
     *
     * @param provider {@link GenericRepository}的ObjectProvider
     */
    @Autowired
    public MapBasedGenericRepositoryAdapter(ObjectProvider<GenericRepository> provider) {
        provider.stream().forEach(repository -> repositoriesByType.merge(
                repository.getResourceType(),
                repository,
                (oldValue, newValue) -> {
                    throw new IllegalArgumentException(
                            String.format("重复的类型为%s的GenericRepository: (%s), (%s)",
                                    oldValue.getResourceType(), oldValue.getClass(), newValue.getClass()));
                }
        ));
    }

    /**
     * 根据传入的资源类型，尝试从{@link #repositoriesByType}中获取{@link GenericRepository }，
     * 如果获取失败则抛出异常
     *
     * @param resourceType 资源类型，不会返回null
     * @return 根据资源类型获取ResourceRepository
     * @throws IllegalArgumentException 当获取失败时抛出
     */
    @Override
    public GenericRepository get(ResourceType resourceType) {
        Assert.notNull(resourceType, "ResourceType不能为null");
        GenericRepository repository = repositoriesByType.get(resourceType);
        if (repository == null)
            throw new IllegalArgumentException("获取失败，资源类型" + resourceType + "相匹配的GenericRepository不存在");
        return repository;
    }

    @Override
    public GenericRepository get(String beanName) {
        throw new UnsupportedOperationException("暂不支持用beanName查找，待实现");
    }
}
