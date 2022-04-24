package com.juyss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juyss.entity.SysUserRole;
import com.juyss.mapper.SysUserRoleMapper;
import com.juyss.service.UserRoleService;
import com.juyss.vo.req.UserRoleOperationReqVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: UserRoleServiceImpl
 * @Desc:
 * @package com.juyss.service.impl
 * @project manager
 * @date 2021/1/21 13:41
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements UserRoleService {
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List getRoleIdsByUserId(String userId) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = Wrappers.<SysUserRole>lambdaQuery().select(SysUserRole::getRoleId).eq(SysUserRole::getUserId, userId);
        return sysUserRoleMapper.selectObjs(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUserRoleInfo(UserRoleOperationReqVO vo) {
        if (vo.getRoleIds() == null || vo.getRoleIds().isEmpty()) {
            return;
        }
        List<SysUserRole> list = new ArrayList<>();
        for (String roleId : vo.getRoleIds()) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(vo.getUserId());
            sysUserRole.setRoleId(roleId);
            list.add(sysUserRole);
        }
        sysUserRoleMapper.delete(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, vo.getUserId()));
        //批量插入
        this.saveBatch(list);
    }

    @Override
    public List getUserIdsByRoleId(String roleId) {
        return sysUserRoleMapper.selectObjs(Wrappers.<SysUserRole>lambdaQuery().select(SysUserRole::getUserId).eq(SysUserRole::getRoleId, roleId));
    }
}