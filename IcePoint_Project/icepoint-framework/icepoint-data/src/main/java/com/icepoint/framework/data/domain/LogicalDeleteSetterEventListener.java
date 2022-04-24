package com.icepoint.framework.data.domain;

import org.springframework.stereotype.Component;

/**
 * @author Jiawei Zhao
 */
@Component
public class LogicalDeleteSetterEventListener implements StdEntityEventListener {

    @Override
    public void prePersist(StdEntity<?, ?> entity) {
        if (entity.getDeleted() == null) {
            entity.setDeleted(false);
        }
    }
}
