package com.icepoint.base.api.util;

import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * {@link ApplicationContext}工具类
 *
 * @author Jiawei Zhao
 */
@Component
public final class ApplicationContextUtils {

    private static ApplicationContextUtils instance;

    private final ApplicationContext context;

    private ApplicationContextUtils(ApplicationContext context) {
        this.context = context;
        ApplicationContextUtils.instance = this;
    }

    private static ApplicationContextUtils getInstance() {
        return instance;
    }

    public static ApplicationContext getContext() {
        return getInstance().context;
    }

    public static Object getBean(String name) {
        ApplicationContext context = getContext();
        return context.containsBean(name) ? context.getBean(name) : null;
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return getContext().getBean(name, requiredType);
    }

    @Nullable
    public static <T> T getBean(Class<T> requiredType) {
        return getContext().getBean(requiredType);
    }
}
