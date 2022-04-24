package com.icepoint.base.api.domain;

import org.springframework.core.Ordered;

/**
 * 拥有可排序索引的父子层级结构
 *
 * @author Jiawei Zhao
 */
public interface OrderedParentIdHierarchy extends ParentIdHierarchy, Ordered {
}
