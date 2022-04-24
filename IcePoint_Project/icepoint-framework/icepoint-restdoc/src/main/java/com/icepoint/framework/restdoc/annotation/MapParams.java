package com.icepoint.framework.restdoc.annotation;

import java.lang.annotation.*;

/**
 * @author Jiawei Zhao
 */
@Inherited
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface MapParams {

    MapParam[] value();
}
