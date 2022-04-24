package com.icepoint.framework.restdoc.annotation;

import java.lang.annotation.*;

/**
 * @author Jiawei Zhao
 */
@Inherited
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface Param {

    String name();

    Class<?> type();

    String description() default "";

    boolean required() default true;
}
