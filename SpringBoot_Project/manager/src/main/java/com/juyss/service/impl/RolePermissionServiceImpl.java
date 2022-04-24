package com.juyss.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juyss.entity.SysRolePermission;
import com.juyss.mapper.SysRolePermissionMapper;
import com.juyss.service.RolePermissionService;
import com.juyss.vo.req.RolePermissionOperationReqVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: RolePermissionServiceImpl
 * @Desc:
 * @package com.juyss.service.impl
 * @project manager
 * @date 2021/1/21 13:15
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements RolePermissionService {
    @Override
    public void addRolePermission(RolePermissionOperationReqVO vo) {
        List<SysRolePermission> list = new ArrayList<>();
        for (String permissionId : vo.getPermissionIds()) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setPermissionId(permissionId);
            sysRolePermission.setRoleId(vo.getRoleId());
            list.add(sysRolePermission);
        }
        this.remove(Wrappers.<SysRolePermission>lambdaQuery().eq(SysRolePermission::getRoleId, vo.getRoleId()));
        this.saveBatch(list);
    }

}
