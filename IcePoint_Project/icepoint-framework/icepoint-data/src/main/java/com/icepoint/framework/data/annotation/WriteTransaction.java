package com.icepoint.framework.data.annotation;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * 通用的写入的事务注解
 *
 * @author Jiawei Zhao
 */
@Transactional
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface WriteTransaction {
}
