package com.icepoint.base.sql;

import java.util.Collection;

public interface Select extends From {

    From getFrom();

    Collection<Field> getFields();
}
