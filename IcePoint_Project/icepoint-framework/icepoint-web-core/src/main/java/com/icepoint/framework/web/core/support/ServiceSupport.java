package com.icepoint.framework.web.core.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Lazy;


/**
 * @author Jiawei Zhao
 */
public abstract class ServiceSupport {

    private final Lazy<Logger> logger = Lazy.of(() -> LoggerFactory.getLogger(this.getClass()));

    protected Logger getLogger() {
        return logger.get();
    }
}
