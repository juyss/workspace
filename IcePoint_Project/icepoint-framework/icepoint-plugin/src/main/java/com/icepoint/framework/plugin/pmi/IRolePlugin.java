package com.icepoint.framework.plugin.pmi;

import java.util.List;

import org.pf4j.ExtensionPoint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;



/**
 * 角色插件
 * 使用插件机制，在项目中允许有特定实现
 * @author BD
 *
 */
public interface IRolePlugin extends ExtensionPoint {
	/**
	 * 添加角色
	 * 
	 * @param entity
	 * @return
	 */
    Role add(Role entity);
    
    /**
     * 编辑角色
     *
     * @param entity 编辑的角色
     * @return 返回编辑后的数据
     */
    Role edit(Role entity);
    
    /**
     * 删除角色
     *
     * @param ids 删除的数据
     * @return 返回删除后的数据
     */
    Integer delete(List<Long> ids);
    
    /**
     * 列表查询角色
     *
     * @param entity 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    Page<Role> list(Role entity, Pageable pageable);
    
    /**
     * 查询详情
     * @param id 实体ID
     * @return 实体详情
     */
    Role findById(Long id);

    /**
     * 为角色添加菜单权限
     * 
     * @param id 角色id
     * @param entitys 要添加的菜单
     * @return 添加是否成功
     */
    Boolean addMenus(Long id,List<Menu> entitys);
    
    /**
     * 角色删除菜单权限
     * 
     * @param id 角色id
     * @param ids 要删除的菜单ID数组
     * @return 删除是否成功
     */
    Boolean deleteMenus(Long id,List<Long> ids);
    
    /**
     * 获取角色所有菜单
     * 
     * @param id 角色id
     * @return 该 角色的所有菜单
     */
    List<Menu> listMenus(Long id);
    
    /**
     * 为角色添加组织机构权限
     * 
     * @param id 角色id
     * @param entitys 要添加的组织机构
     * @return 添加是否成功
     */
    Boolean addDepts(Long id,List<Dept> entitys);
    
    /**
     * 角色删除组织机构权限
     * 
     * @param id 角色id
     * @param ids 要删除的组织机构ID数组
     * @return 删除是否成功
     */
    Boolean deleteDepts(Long id,List<Long> ids);
    
    /**
     * 获取角色所有组织机构权限
     * 
     * @param id 角色id
     * @return 该 角色的所有组织机构权限
     */
    List<Dept> listDepts(Long id);
    
    //数据权限
}
