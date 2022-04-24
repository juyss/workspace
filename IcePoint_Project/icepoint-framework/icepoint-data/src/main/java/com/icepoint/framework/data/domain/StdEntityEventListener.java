package com.icepoint.framework.data.domain;

/**
 * @author Jiawei Zhao
 */
public interface StdEntityEventListener {

    default void prePersist(StdEntity<?, ?> entity) {
    }

    default void postPersist(StdEntity<?, ?> entity) {
    }

    default void preUpdate(StdEntity<?, ?> entity) {
    }

    default void postUpdate(StdEntity<?, ?> entity) {
    }

    default void preRemove(StdEntity<?, ?> entity) {
    }

    default void postRemove(StdEntity<?, ?> entity) {
    }

    default void postLoad(StdEntity<?, ?> entity) {
    }
}
