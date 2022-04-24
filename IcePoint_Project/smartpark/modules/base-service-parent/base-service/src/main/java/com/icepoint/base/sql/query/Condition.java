package com.icepoint.base.sql.query;

public interface Condition<V> {

    Relation getRelation();

    V getValue();
}
