package com.icepoint.framework.plugin.pmi;

import java.util.List;

import org.pf4j.ExtensionPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 菜单、子页面、按钮插件
 * 使用插件机制，在项目中允许有特定实现
 * @author BD
 *
 */
public interface IMenuPlugin extends ExtensionPoint {
	/**
	 * 添加菜单
	 * 
	 * @param entity
	 * @return
	 */
	Menu add(Menu entity);
    
    /**
     * 编辑菜单
     *
     * @param entity 编辑的菜单
     * @return 返回编辑后的数据
     */
	Menu edit(Menu entity);
    
    /**
     * 删除菜单
     *
     * @param ids 删除的数据
     * @return 返回删除后的数据
     */
    Integer delete(List<Long> ids);
    
    /**
     * 列表查询菜单
     *
     * @param entity 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    Page<Menu> list(Menu entity, Pageable pageable);
    
    /**
     * 查询详情
     * @param id 实体ID
     * @return 实体详情
     */
    Menu findById(Long id);
}
