package com.juyss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juyss.entity.SysRolePermission;
import com.juyss.vo.req.RolePermissionOperationReqVO;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: RolePermissionService
 * @Desc:
 * @package com.juyss.service
 * @project manager
 * @date 2021/1/20 16:33
 */
public interface RolePermissionService extends IService<SysRolePermission> {

    /**
     * 角色绑定权限
     *
     * @param vo vo
     */
    void addRolePermission(RolePermissionOperationReqVO vo);
}
