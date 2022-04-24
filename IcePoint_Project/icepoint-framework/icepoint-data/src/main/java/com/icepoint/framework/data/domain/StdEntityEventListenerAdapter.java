package com.icepoint.framework.data.domain;

import com.icepoint.framework.core.util.ApplicationContextUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
public class StdEntityEventListenerAdapter {

    private final Lazy<List<StdEntityEventListener>> listeners = Lazy.of(ApplicationContextUtils::getContext)
            .map(context -> context.getBeanProvider(StdEntityEventListener.class))
            .map(ObjectProvider::orderedStream)
            .map(stream -> stream.collect(Collectors.toList()));

    @PrePersist
    public void prePersist(StdEntity<?, ?> entity) {
        listeners.get().forEach(listener -> listener.prePersist(entity));
    }

    @PostPersist
    public void postPersist(StdEntity<?, ?> entity) {
        listeners.get().forEach(listener -> listener.postPersist(entity));
    }

    @PreUpdate
    public void preUpdate(StdEntity<?, ?> entity) {
        listeners.get().forEach(listener -> listener.preUpdate(entity));
    }

    @PostUpdate
    public void postUpdate(StdEntity<?, ?> entity) {
        listeners.get().forEach(listener -> listener.postUpdate(entity));
    }

    @PreRemove
    public void preRemove(StdEntity<?, ?> entity) {
        listeners.get().forEach(listener -> listener.preRemove(entity));
    }

    @PostRemove
    public void postRemove(StdEntity<?, ?> entity) {
        listeners.get().forEach(listener -> listener.postRemove(entity));
    }

    @PostLoad
    public void postLoad(StdEntity<?, ?> entity) {
        listeners.get().forEach(listener -> listener.postLoad(entity));
    }

}
