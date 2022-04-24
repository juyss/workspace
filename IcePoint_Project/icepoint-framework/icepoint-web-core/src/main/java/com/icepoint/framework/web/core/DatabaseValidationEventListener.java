package com.icepoint.framework.web.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
@Slf4j
@Component
public class DatabaseValidationEventListener {

    private final List<ApplicationInitializer> validators;

    public DatabaseValidationEventListener(ObjectProvider<ApplicationInitializer> validators) {
        this.validators = validators.orderedStream().collect(Collectors.toList());
    }

    @EventListener(ContextRefreshedEvent.class)
    public void validate() {
        try {
            for (ApplicationInitializer validator : validators) {
                validator.initialize();
            }
        } catch (DatabaseValidationFailedException e) {
            log.error("数据校验失败", e);
            System.exit(0);
        }
    }
}
