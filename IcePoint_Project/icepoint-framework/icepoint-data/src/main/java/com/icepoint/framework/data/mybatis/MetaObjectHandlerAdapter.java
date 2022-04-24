package com.icepoint.framework.data.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.icepoint.framework.data.domain.StdEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ck
 */
@Slf4j
@Component
public class MetaObjectHandlerAdapter implements MetaObjectHandler {

    private final List<MybatisPersistListener> listeners;

    public MetaObjectHandlerAdapter(ObjectProvider<MybatisPersistListener> listeners) {
        this.listeners = listeners.orderedStream().collect(Collectors.toList());
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        Optional.ofNullable(metaObject.getOriginalObject())
                .filter(StdEntity.class::isInstance)
                .map(StdEntity.class::cast)
                .ifPresent(entity -> listeners.forEach(listener -> listener.insertFill(entity)));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Optional.ofNullable(metaObject.getOriginalObject())
                .filter(StdEntity.class::isInstance)
                .map(StdEntity.class::cast)
                .ifPresent(entity -> listeners.forEach(listener -> listener.updateFill(entity)));
    }
}



