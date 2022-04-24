package com.icepoint.base.api.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 继承这个类，可以使得properties中的属性，在转化为Json对象后，不再包含在properties这个属性中，而是成为此对象本身的属性 <br/>
 * 使得继承此类的对象可以动态的增加字段属性 <br/>
 * 通过这两个方法设置与获取动态属性: {@link #setProperty} | {@link #getProperty}
 *
 * @param <V> 属性的类型
 * @author Jiawei Zhao
 */

public class JsonAnyProperties<V> {

    @JsonIgnore
    private transient Map<String, V> properties;

    public JsonAnyProperties() {
        this.properties = new HashMap<>();
    }

    public JsonAnyProperties(Map<String, V> properties) {
        this.properties = properties;
    }

    @Nullable
    public V getProperty(String fieldName) {
        return properties.get(fieldName);
    }

    @JsonAnySetter
    public void setProperty(String fieldName, V value) {
        Assert.hasText(fieldName, "fieldName must has text");
        properties.put(fieldName, value);
    }

    @JsonAnyGetter
    protected Map<String, V> getPropertyMap() {
        return properties;
    }

    public Map<String, V> toMap() {
        return new LinkedHashMap<>(properties);
    }
}
