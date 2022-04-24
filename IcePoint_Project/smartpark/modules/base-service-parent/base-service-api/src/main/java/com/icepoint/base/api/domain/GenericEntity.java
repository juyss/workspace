package com.icepoint.base.api.domain;

import com.icepoint.base.api.entity.MetaTab;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Collection;

public interface GenericEntity
        extends Serializable, GenericProperty,
        Iterable<GenericProperty>, MetadataHolder<MetaTab> {

    String getKey();

    GenericProperty getProperty(String fieldName);

    @Nullable
    default Object getPropertyValue(String fieldName) {
        return getProperty(fieldName).getValue();
    }

    void setProperty(GenericProperty property);

    Collection<GenericProperty> getProperties();

    boolean isEmpty();

    boolean hasProperty(String fieldName);

    @Override
    default int compareTo(GenericProperty o) {
        return 0;
    }
}
