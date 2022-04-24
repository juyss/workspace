package com.icepoint.base.api.domain;

import java.lang.annotation.*;

/**
 * 注解在bean上，代表这是一个通用表中某个数据的实体类，
 * value与配置文件generic-resource.yml的key值对应
 *
 * // TODO: jiawei: 暂未编写实现其功能的代码，预计有这么一个功能
 *
 * @author Jiawei Zhao
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Generic {

    String value();
}
