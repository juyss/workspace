package com.icepoint.framework.plugin.pmi;

import java.util.List;

import org.pf4j.ExtensionPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 部门组织机构插件
 * 使用插件机制，在项目中允许有特定实现
 * @author BD
 *
 */
public interface IDeptPlugin extends ExtensionPoint {
	/**
	 * 添加部门
	 * 
	 * @param entity
	 * @return
	 */
    Dept add(Dept entity);
    
    /**
     * 编辑部门
     *
     * @param entity 编辑的部门
     * @return 返回编辑后的数据
     */
    Dept edit(Dept entity);
    
    /**
     * 删除部门
     *
     * @param ids 删除的数据
     * @return 返回删除后的数据
     */
    Integer delete(List<Long> ids);
    
    /**
     * 列表查询部门
     *
     * @param entity 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    Page<Dept> list(Dept entity, Pageable pageable);
    
    /**
     * 查询详情
     * @param id 实体ID
     * @return 实体详情
     */
    Dept findById(Long id);
}
