package com.icepoint.framework.core.support;

import java.util.function.Supplier;

/**
 * @author Jiawei Zhao
 */
public class Logger {

    private final org.slf4j.Logger logger;

    private Logger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    public static Logger of(org.slf4j.Logger logger) {
        return new Logger(logger);
    }

    interface MessageSupplier extends Supplier<String> {
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void info(MessageSupplier msgSupplier) {
        if (logger.isInfoEnabled()) {
            info(msgSupplier.get());
        }
    }

}
