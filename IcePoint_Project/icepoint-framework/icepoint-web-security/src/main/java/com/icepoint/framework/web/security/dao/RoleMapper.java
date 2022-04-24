package com.icepoint.framework.web.security.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.security.entity.Role;
import com.icepoint.framework.web.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface RoleMapper extends RepositoryMapper<Role,Long> {
    /**
     * 查询角色下所有的用户
     * @param roleId 角色id
     * @return 角色下所有的用户
     */
    List<User> findUserByRole(@Param("roleId") Long roleId);

    /**
     * 新增角色和用户的关联
     * @param userId
     * @param roleId
     * @return
     */
    Integer insertUserIds(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 删除角色的关联用户
     * @param roleId
     * @return
     */
    Integer deleteRoleById(@Param("roleId") Long roleId);
}
