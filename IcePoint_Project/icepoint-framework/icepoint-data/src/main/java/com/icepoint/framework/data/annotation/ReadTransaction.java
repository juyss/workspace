package com.icepoint.framework.data.annotation;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * 通用的只读事务配置
 *
 * @author Jiawei Zhao
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ReadTransaction {
}
