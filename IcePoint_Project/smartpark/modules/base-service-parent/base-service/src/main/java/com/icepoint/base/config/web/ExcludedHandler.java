package com.icepoint.base.config.web;

import java.lang.annotation.*;

/**
 * 允许屏蔽Controller中的某个Handler
 *
 * @author Jiawei Zhao
 */
@Repeatable(ExcludedHandlers.class)
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcludedHandler {

    String methodName();

    Class<?>[] parameterTypes() default {};
}
