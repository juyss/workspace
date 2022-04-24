package com.icepoint.framework.data.mybatis;

import com.icepoint.framework.data.domain.StdEntity;

/**
 * @author Jiawei Zhao
 */
public interface MybatisPersistListener {

    default void insertFill(StdEntity<?, ?> entity) {
    }

    default void updateFill(StdEntity<?, ?> entity) {
    }

}
