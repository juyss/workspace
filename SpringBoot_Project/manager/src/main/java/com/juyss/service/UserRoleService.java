package com.juyss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juyss.entity.SysUserRole;
import com.juyss.vo.req.UserRoleOperationReqVO;

import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: UserRoleService
 * @Desc:
 * @package com.juyss.service
 * @project manager
 * @date 2021/1/20 16:38
 */
public interface UserRoleService extends IService<SysUserRole> {

    /**
     * 根据userId获取绑定的角色id
     *
     * @param userId userId
     * @return List
     */
    List<String> getRoleIdsByUserId(String userId);

    /**
     * 用户绑定角色
     *
     * @param vo vo
     */
    void addUserRoleInfo(UserRoleOperationReqVO vo);

    /**
     * 根据角色id获取绑定的人
     *
     * @param roleId roleId
     * @return List
     */
    List<String> getUserIdsByRoleId(String roleId);
}
