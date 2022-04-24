package com.icepoint.base.config.web;

import java.lang.annotation.*;

/**
 * 多个{@link ExcludedHandler}
 *
 * @author Jiawei Zhao
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcludedHandlers {

    ExcludedHandler[] value();
}
