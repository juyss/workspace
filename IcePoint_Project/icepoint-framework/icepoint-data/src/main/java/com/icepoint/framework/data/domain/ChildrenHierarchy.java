package com.icepoint.framework.data.domain;

import java.util.List;

/**
 * 通过子列表来建立父子层级结构的接口
 *
 * @param <T> 子列表元素类型
 * @author Jiawei Zhao
 */
public interface ChildrenHierarchy<T> {

    List<T> getChildren();

    void setChildren(List<T> children);
}
