package com.icepoint.framework.data.domain;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * 通过parentId来构建父子结构层次结构的接口
 *
 * @author Jiawei Zhao
 */
public interface ParentIdHierarchy<ID extends Serializable> {

    @Nullable
    ID getParentId();

}
