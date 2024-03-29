package com.len.mapper;

import com.len.entity.SysUser;
import com.len.pdms.model.entity.Project;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends com.len.base.BaseMapper<SysUser,String> {

    SysUser login(@Param("username") String username);

    int count();

    int add(SysUser user);

    int delById(String id);

    int checkUser(String username);

    /**
     * 更新密码
     * @param user
     * @return
     */
    int rePass(SysUser user);

    List<SysUser> getUserByRoleId(@Param("roleId")String roleId);

    // 用户租户相关操作
    int insertTenant(Map map);

    List<Map> getUserTenants(@Param("userId")String userId);

    List<Project> getProjects(@Param("tenantId")String tenantId);

    int deleteTenantUser(@Param("userId")String userId,@Param("tenantId")String tenantId);

}