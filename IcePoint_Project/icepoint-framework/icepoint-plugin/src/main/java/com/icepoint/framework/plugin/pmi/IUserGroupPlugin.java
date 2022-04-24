package com.icepoint.framework.plugin.pmi;

import java.util.List;

import org.pf4j.ExtensionPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 用户组插件
 * 使用插件机制，在项目中允许有特定实现
 * @author BD
 *
 */
public interface IUserGroupPlugin extends ExtensionPoint {
	/**
	 * 添加用户组
	 * 
	 * @param entity
	 * @return
	 */
	UserGroup add(UserGroup entity);
    
    /**
     * 编辑用户组
     *
     * @param entity 编辑的用户组
     * @return 返回编辑后的数据
     */
	UserGroup edit(UserGroup entity);
    
    /**
     * 删除用户组
     *
     * @param ids 删除的数据
     * @return 返回删除后的数据
     */
    Integer delete(List<Long> ids);
    
    /**
     * 列表查询用户组
     *
     * @param entity 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    Page<UserGroup> list(UserGroup entity, Pageable pageable);
    
    /**
     * 查询详情
     * @param id 实体ID
     * @return 实体详情
     */
    UserGroup findById(Long id);
    
    /**
     * 为用户组添加角色
     * 
     * @param id 用户组ID
     * @param entitys 要添加的角色
     * @return 该用户组的所有角色
     */
    List<Role> addRoles(Long id,List<Role> entitys);
    
    /**
     * 用户组删除角色
     * 
     * @param id 用户组ID
     * @param ids 要删除的角色ID数组
     * @return 该用户组的所有角色
     */
    List<Role> deleteRoles(List<Long> ids);
    
    /**
     * 获取用户组所有角色
     * 
     * @param id 用户组ID
     * @return 该用户组的所有角色
     */
    List<Role> listRoles(Long id);
    
    /**
     * 为用户组添加用户
     * 
     * @param id 用户组ID
     * @param entitys 要添加的用户
     * @return 添加是否成功
     */
    Boolean addUsers(Long id,List<User> entitys);
    
    /**
     * 用户组删除用户
     * 
     * @param id 用户组ID
     * @param ids 要删除的用户ID数组
     * @return 删除是否成功
     */
    Boolean deleteUsers(Long id,List<Long> ids);
    
    /**
     * 获取用户组所有用户
     * 
     * @param id 用户组ID
     * @return 该用户组的所有用户
     */
    Page<User> listUsers(Long id, Pageable pageable);
}
