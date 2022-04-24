package com.juyss.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.juyss.entity.SysUser;
import com.juyss.vo.resp.LoginRespVO;
import com.juyss.vo.resp.UserOwnRoleRespVO;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: UserService
 * @Desc:
 * @package com.juyss.service
 * @project manager
 * @date 2021/1/20 16:38
 */
public interface UserService extends IService<SysUser> {

    /**
     * 注册
     *
     * @param vo vo
     */
    void register(SysUser vo);

    /**
     * 登陆
     *
     * @param vo vo
     * @return LoginRespVO
     */
    LoginRespVO login(SysUser vo);

    /**
     * 更新用户信息
     *
     * @param vo vo
     */
    void updateUserInfo(SysUser vo);

    /**
     * 分页
     *
     * @param vo vo
     * @return IPage
     */
    IPage<SysUser> pageInfo(SysUser vo);

    /**
     * 添加用户
     *
     * @param vo vo
     */
    void addUser(SysUser vo);

    /**
     * 修改密码
     *
     * @param vo vo
     */
    void updatePwd(SysUser vo);

    /**
     * 根据userid获取绑定角色
     *
     * @param userId userId
     * @return UserOwnRoleRespVO
     */
    UserOwnRoleRespVO getUserOwnRole(String userId);

    /**
     * 修改自己信息
     *
     * @param vo vo
     */
    void updateUserInfoMy(SysUser vo);
}
