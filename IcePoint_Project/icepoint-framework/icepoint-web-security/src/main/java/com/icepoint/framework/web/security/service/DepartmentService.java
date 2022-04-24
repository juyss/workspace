package com.icepoint.framework.web.security.service;

import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.security.entity.Organization;
import com.icepoint.framework.web.security.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {

	Organization add(Organization entity);
    
	Organization edit(Organization entity);
    
    Integer delete(List<Long> ids);
    
    Page<Organization> list(Organization entity, Pageable pageable);
    
    Organization findById(Long id);

    /**
     * 部门树
     * @return 树
     */
    List<TreeNode<Organization>> getTreeList(Long parentId);

    /**
     * 根据父id查询说有的子节点
     * @param parentId 父id
     * @return 子节点集合
     */
    List<Organization> queryByParentId(Long parentId);
}
