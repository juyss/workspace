package com.icepoint.base.api.domain;

import org.springframework.lang.Nullable;

import java.io.Serializable;

public interface GenericProperty extends Serializable, Nameable, Comparable<GenericProperty> {

    @Nullable
    Object getValue();
}
