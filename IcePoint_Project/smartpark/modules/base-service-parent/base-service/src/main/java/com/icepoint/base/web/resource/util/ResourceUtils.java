package com.icepoint.base.web.resource.util;

import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.api.util.StreamUtils;
import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.web.resource.component.properties.GenericResourceProperties;
import com.icepoint.base.web.sys.entity.Dict;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 资源工具类
 *
 * @author Jiawei Zhao
 * @see ResourceMetadata
 */
@Component
public class ResourceUtils {

    private static ResourceUtils instance;

    private final GenericResourceProperties properties;

    private ResourceUtils(GenericResourceProperties properties) {
        this.properties = properties;
        ResourceUtils.instance = this;
    }

    private static ResourceUtils getInstance() {
        return instance;
    }

    public static String getFieldName(MetaField field) {
        return StringUtils.hasText(field.getNameEn())
                ? field.getNameEn() : field.getStoreNameEn();
    }

    @NonNull
    public static Integer getDocType(Dict dict) {
        Assert.notNull(dict, "dict must not be null");
        String dictCategory = getDictionaryCategory();
        Assert.isTrue(Objects.equals(dict.getCategoryEn(), dictCategory),
                String.format("传入的资源字典分类是: %s, 但实际应该是: %s, 建议检查代码与配置文件的配置是否相符",
                        dict.getCategoryEn(), dictCategory)
        );

        String value = dict.getCval();
        Assert.hasText(value, "资源字典值为空，请检查传入的参数是否正确或者数据库的配置是否正确");
        return Integer.valueOf(value);
    }

    @NonNull
    public static String getDictionaryCategory() {
        return getInstance().properties.getResource().getDictCategory();
    }

    @Nullable
    public static String getResourceKey(String name) {
        return getInstance().properties.getEntities().stream().filter(entity -> entity.getName().equals(name))
                .findAny()
                .map(GenericResourceProperties.Entity::getKey)
                .orElse(null);
    }

    @Nullable
    public static String getResourceName(String key) {
        return StreamUtils.parallelStreamIfAvailable(getInstance().properties.getEntities())
                .filter(entity -> entity.getKey().equals(key))
                .findAny()
                .map(GenericResourceProperties.Entity::getName)
                .orElse(null);
    }

    @Nullable
    public static String getResourceName(Class<?> entityClass) {
        return getResourceName(GenericUtils.getResourceKey(entityClass));
    }

}
