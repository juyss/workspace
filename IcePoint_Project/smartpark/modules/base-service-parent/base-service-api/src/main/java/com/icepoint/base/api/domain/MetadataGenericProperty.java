package com.icepoint.base.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.api.supports.PropertyComparators;
import lombok.Data;
import lombok.NonNull;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.comparator.Comparators;

@Data
public class MetadataGenericProperty
        implements GenericProperty, Ordered {

    @JsonIgnore
    private final @NonNull MetaField metaField;

    private final String name;

    private String displayName;

    private Object value;

    private int order;

    public MetadataGenericProperty(MetaField metaField, @Nullable Object value) {
        Assert.notNull(metaField, "MetaField must not be null.");
        this.metaField = metaField;
        this.name = metaField.getNameEn();
        this.displayName = metaField.getName();
        this.order = metaField.getIdx();
        this.value = value;
    }

    @Override
    public int compareTo(GenericProperty o) {
        if (o instanceof Ordered) {
            return PropertyComparators.ORDERED.compare(this, o);
        } else {
            return -Comparators.nullsLow().compare(o == null ? null : o.getValue(), value);
        }
    }

}
