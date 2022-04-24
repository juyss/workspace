package com.icepoint.framework.data.annotation;

import org.springframework.core.annotation.AliasFor;

/**
 * 标记实体字段是加密字段
 *
 * @author Jiawei Zhao
 */
public @interface EncryptProperty {

    @AliasFor("algorithm")
    EncryptType value() default EncryptType.MD5;

    @AliasFor("value")
    EncryptType algorithm() default EncryptType.MD5;
}
