package com.juyss.service.impl;

import com.juyss.entity.SysDept;
import com.juyss.entity.SysUser;
import com.juyss.service.DeptService;
import com.juyss.service.HomeService;
import com.juyss.service.PermissionService;
import com.juyss.service.UserService;
import com.juyss.vo.resp.HomeRespVO;
import com.juyss.vo.resp.PermissionRespNode;
import com.juyss.vo.resp.UserInfoRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: HomeServiceImpl
 * @Desc:
 * @package com.juyss.service.impl
 * @project manager
 * @date 2021/1/21 13:02
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private UserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PermissionService permissionService;

    @Override
    public HomeRespVO getHomeInfo(String userId) {


        SysUser sysUser = userService.getById(userId);
        UserInfoRespVO vo = new UserInfoRespVO();

        if (sysUser != null) {
            BeanUtils.copyProperties(sysUser, vo);
            SysDept sysDept = deptService.getById(sysUser.getDeptId());
            if (sysDept != null) {
                vo.setDeptId(sysDept.getId());
                vo.setDeptName(sysDept.getName());
            }
        }

        List<PermissionRespNode> menus = permissionService.permissionTreeList(userId);

        HomeRespVO respVO = new HomeRespVO();
        respVO.setMenus(menus);
        respVO.setUserInfo(vo);

        return respVO;
    }
}
