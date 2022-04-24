package com.icepoint.base.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import com.icepoint.base.api.entity.MetaTab;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties({"key", "name"})
public class ListJoinEntity extends AbstractGenericEntity {

    @JsonValue
    private final List<GenericEntity> value;

    public ListJoinEntity(String key, MetaTab metadata, List<GenericEntity> entities) {
        super(key, metadata);
        this.value = entities;
    }

    @Override
    public GenericEntity getProperty(String fieldName) {
        return value.get(Integer.parseInt(fieldName));
    }

    @Override
    public void setProperty(GenericProperty property) {
        value.add((GenericEntity) property);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Collection<GenericProperty> getProperties() {
        return (Collection) value;
    }

    @Override
    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public boolean hasProperty(String fieldName) {
        return value.stream().anyMatch(p -> Objects.equals(fieldName, p.getName()));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Iterator<GenericProperty> iterator() {
        return ((Collection) value).iterator();
    }

    @Override
    public Object getValue() {
        return value;
    }
}
