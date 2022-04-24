package com.icepoint.framework.autoconfigure.web.system;

import com.icepoint.framework.web.system.rest.MultiplyPathsRepositoryRestHandlerMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.data.rest.webmvc.RepositoryRestHandlerMapping;
import org.springframework.data.rest.webmvc.support.DelegatingHandlerMapping;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
public class RestMappingConfiguration {

    private final ApplicationContext applicationContext;

    @Autowired
    public RestMappingConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings("deprecation")
    @EventListener(ApplicationStartedEvent.class)
    public void configureDelegatingHandlerMappingEvent() {

        DelegatingHandlerMapping delegatingMapping = applicationContext.getBean(DelegatingHandlerMapping.class);

        List<HandlerMapping> delegates = delegatingMapping.getDelegates();

        for (int i = 0; i < delegates.size(); i++) {
            HandlerMapping mapping = delegates.get(i);

            if (mapping instanceof RepositoryRestHandlerMapping) {

                MultiplyPathsRepositoryRestHandlerMapping multiplyPaths =
                        new MultiplyPathsRepositoryRestHandlerMapping((RepositoryRestHandlerMapping) mapping);

                delegates.set(i, multiplyPaths);
            }
        }
    }

}
