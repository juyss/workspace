package com.icepoint.base.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import com.icepoint.base.api.entity.MetaTab;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * 通用实体类，key与name属性与配置文件generic-resource.yml中对应
 *
 * @author Jiawei Zhao
 */
@JsonIgnoreProperties({"value", "metadata", "key", "empty"})
@EqualsAndHashCode(callSuper = true)
public class MapBasedEntity extends AbstractGenericEntity {

    @JsonValue
    private final JsonAnyProperties<GenericProperty> properties;

    public MapBasedEntity(String key, MetaTab metadata) {
        super(key, metadata);
        this.properties = new JsonAnyProperties<>();
    }

    public MapBasedEntity(String key, MetaTab metadata, Map<String, GenericProperty> properties) {
        super(key, metadata);
        this.properties = new JsonAnyProperties<>(properties);
    }

    @Override
    public GenericProperty getProperty(String fieldName) {
        GenericProperty property = properties.getProperty(fieldName);
        return property == null ? NullProperty.INSTANCE : property;
    }

    @Override
    public void setProperty(GenericProperty property) {
        properties.setProperty(property.getName(), property);
    }

    @Override
    public Collection<GenericProperty> getProperties() {
        return properties.getPropertyMap().values();
    }

    @Override
    public boolean isEmpty() {
        return properties.getPropertyMap().isEmpty();
    }

    @Override
    public boolean hasProperty(String fieldName) {
        return properties.getPropertyMap().containsKey(fieldName);
    }

    @Override
    public MapBasedEntity getValue() {
        return this;
    }

    @Override
    public Iterator<GenericProperty> iterator() {
        return properties.getPropertyMap().values().iterator();
    }

}
