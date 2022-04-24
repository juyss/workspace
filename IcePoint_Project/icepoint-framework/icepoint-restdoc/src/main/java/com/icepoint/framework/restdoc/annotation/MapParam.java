package com.icepoint.framework.restdoc.annotation;

import java.lang.annotation.*;

/**
 * @author Jiawei Zhao
 */
@Repeatable(MapParams.class)
@Inherited
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface MapParam {

    Param[] params();

}
