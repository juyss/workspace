package com.icepoint.framework.web.system.resource;

import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.SimpleTypeUtils;
import com.icepoint.framework.data.domain.PropertyConstants;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public abstract class AbstractResourceModel implements ResourceModel {

    private final Lookup lookup;

    protected AbstractResourceModel(Lookup lookup) {
        this.lookup = lookup;
    }

    @Override
    public void setProperties(Map<String, Object> properties) {
        if (!CollectionUtils.isEmpty(properties)) {
            properties.forEach(this::setProperty);
        }
    }

    @Nullable
    @Override
    public <T> T getProperty(String name, Class<T> type) {

        Object value = getProperty(name);

        if (value == null) {
            return null;
        } else {

            Class<?> valueClass = value.getClass();

            if (type.isAssignableFrom(valueClass)) {
                return CastUtils.cast(value);
            } else if (SimpleTypeUtils.isSimpleType(type)) {
                return SimpleTypeUtils.parse(value, type);
            } else {
                Assert.isTrue(type.isInstance(value), () -> String.format(
                        "从通用Model对象获取属性时, 要求的类型不是简单类型并且与实际类型不相符, 要求的类型: %s, 实际的类型: %s",
                        type, valueClass));
                return CastUtils.cast(value);
            }
        }
    }

    public void setId(Long id) {
        setProperty(PropertyConstants.ID, id);
    }

    @Nullable
    @Override
    public Long getId() {
        return getProperty(PropertyConstants.ID, Long.class);
    }

    @Override
    public Lookup getLookup() {
        return lookup;
    }
}
