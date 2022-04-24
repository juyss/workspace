package com.icepoint.base.api.domain;

public class NullProperty implements GenericProperty {

    public static final NullProperty INSTANCE = new NullProperty();

    private NullProperty() {}

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public String getName() {
        return "null";
    }

    @Override
    public int compareTo(GenericProperty o) {
        return 0;
    }
}
