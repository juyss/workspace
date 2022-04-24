package com.icepoint.base.api.domain;

import org.springframework.lang.Nullable;

/**
 * 通过parentId来构建父子结构层次结构的接口
 *
 * @author Jiawei Zhao
 */
public interface ParentIdHierarchy {

    @Nullable
    Long getParentId();

}
