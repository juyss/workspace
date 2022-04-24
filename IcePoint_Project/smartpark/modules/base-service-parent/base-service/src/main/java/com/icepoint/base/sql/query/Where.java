package com.icepoint.base.sql.query;

import java.util.Collection;

public interface Where {

    Collection<Condition> getConditions();

}