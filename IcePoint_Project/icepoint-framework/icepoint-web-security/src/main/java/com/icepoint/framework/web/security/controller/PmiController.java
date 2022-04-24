package com.icepoint.framework.web.security.controller;

import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.core.response.CollectionResponse;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.security.entity.Organization;
import com.icepoint.framework.web.security.entity.Role;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.security.service.DepartmentService;
import com.icepoint.framework.web.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 这块要考虑和其他系统集成时候如何处理
 * 1、插件机制，具体项目可以自己实现插件接口，有特定实现机制
 * 2、内部有一套自己的用户权限机制，提供增量数据请求接口，数据变化触发事件机制，做数据层面的同步，实现对接
 * @author BD
 *
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/pmi")
public class PmiController {
    private final RoleService roleService;
    private final DepartmentService departmentService;
	
    //////////////////////////////////////////////////////////////
    // 角色管理
    //////////////////////////////////////////////////////////////
    @PostMapping("addRole")
    public Response<Role> addRole(@RequestBody Role entity) {
        return ResponseUtils.one(roleService.add(entity));
    }
    
    /**
     * 编辑作角色
     *
     * @param entity 编辑的角色
     * @return 返回编辑后的数据
     */
    @PatchMapping("editRole")
    public Response<Role> editRole(@RequestBody Role entity) {
        return ResponseUtils.one(roleService.edit(entity));
    }
    
    /**
     * 删除角色
     *
     * @param ids 删除的数据ID数组
     * @return 返回删除后的数据
     */
    @DeleteMapping("deleteRoles")
    public Response<Integer> deleteRoles(@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.any(roleService.delete(ids));
    }
    
    /**
     * 列表查询角色
     *
     * @param entity 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    @GetMapping("/role/list")
    public PageResponse<Role> listRole(Role entity, Pageable pageable) {
        return ResponseUtils.page(roleService.list(entity, pageable));
    }
    
    @GetMapping("/role/{id}")
    public Response<Role> findRoleById(@PathVariable("id") Long id) {
        return ResponseUtils.any(roleService.findById(id));
    }
    
    //////////////////////////////////////////////////////////////
    // 部门管理
    //////////////////////////////////////////////////////////////
    @PostMapping("addDept")
    public Response<Organization> addDept(@RequestBody Organization entity) {
        return ResponseUtils.one(departmentService.add(entity));
    }

    /**
     * 编辑部门
     *
     * @param entity 编辑的部门
     * @return 返回编辑后的数据
     */
    @PatchMapping("editDept")
    public Response<Organization> editDept(@RequestBody Organization entity) {
        return ResponseUtils.one(departmentService.edit(entity));
    }
    
    /**
     * 删除角色
     *
     * @param ids 删除的数据
     * @return 返回删除后的数据
     */
    @DeleteMapping("deleteDepts")
    public Response<Integer> deleteDepts(@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.any(departmentService.delete(ids));
    }
    
    /**
     * 列表查询部门
     *
     * @param template 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    @GetMapping("/dept/list")
    public PageResponse<Organization> listDept(Organization entity, Pageable pageable) {
        return ResponseUtils.page(departmentService.list(entity, pageable));
    }
    
    @GetMapping("/dept/{id}")
    public Response<Organization> findDetpById(@PathVariable("id") Long id) {
        return ResponseUtils.any(departmentService.findById(id));
    }

    @GetMapping("findUserByRole/{roleId}")
    public Response<List<User>> findUserByRole(@PathVariable("roleId") Long roleId){
        return  ResponseUtils.any(roleService.findUserByRole(roleId));
    }

    /**
     * 部门树
     * @param parentId 根节点
     * @return
     */
    @GetMapping("getTreeList/{parentId}")
    public CollectionResponse<TreeNode<Organization>> getTreeList(@PathVariable("parentId") Long parentId){
        return ResponseUtils.many(departmentService.getTreeList(parentId));
    }

    @GetMapping("queryByParentId/{parentId}")
    public  CollectionResponse<Organization> queryByParentId(@PathVariable Long parentId){
        return ResponseUtils.many(departmentService.queryByParentId(parentId));
    }

    /**
     *为角色新增角色
     */
    @PostMapping("addUser")
    public Response<Boolean> addUser(@RequestParam("userId") List<Long> userIds,@RequestParam("roleId") Long roleId ){
        return ResponseUtils.one(roleService.updateUser(userIds,roleId));
    }

}
