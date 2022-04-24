package com.icepoint.framework.data.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 标记实体类字段是字典字段
 *
 * @author Jiawei Zhao
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface DictionaryProperty {

    @AliasFor("category")
    String value() default "";

    @AliasFor("value")
    String category() default "";

}
