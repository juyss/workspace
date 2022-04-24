package com.icepoint.base.api.domain;

import lombok.RequiredArgsConstructor;

/**
 * 通用属性的类型，指的不是Java的类型，而是{@link MapBasedEntity}中的属性组装模式
 *
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
public enum SerializeType {
    OBJECT, VALUE
}
