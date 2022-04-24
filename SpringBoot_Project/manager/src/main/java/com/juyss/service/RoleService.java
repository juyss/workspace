package com.juyss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juyss.entity.SysRole;

import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: RoleService
 * @Desc:
 * @package com.juyss.service
 * @project manager
 * @date 2021/1/20 16:34
 */
public interface RoleService extends IService<SysRole> {

    /**
     * 添加角色
     *
     * @param vo SysRole
     */
    void addRole(SysRole vo);

    /**
     * 更新角色
     *
     * @param vo SysRole
     */
    void updateRole(SysRole vo);

    /**
     * 根据id获取角色详情
     *
     * @param id id
     * @return SysRole
     */
    SysRole detailInfo(String id);

    /**
     * 根据id删除
     *
     * @param id id
     */
    void deletedRole(String id);

    /**
     * 根据userId获取绑定的角色
     *
     * @param userId userId
     * @return List
     */
    List<SysRole> getRoleInfoByUserId(String userId);

    /**
     * 根据userId获取绑定的角色名
     *
     * @param userId userId
     * @return List
     */
    List<String> getRoleNames(String userId);
}
