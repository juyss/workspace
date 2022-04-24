package com.icepoint.framework.web.security.service;

import com.icepoint.framework.web.security.entity.Role;
import com.icepoint.framework.web.security.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
public interface RoleService {

    boolean hasRole(HttpServletRequest request, Authentication authentication);
    
    Role add(Role entity);
    
    Role edit(Role entity);
    
    Integer delete(List<Long> ids);
    
    Page<Role> list(Role entity, Pageable pageable);
    
    Role findById(Long id);

    /**
     * 根据角色id 查询所有的用户
     * @param roleId 角色id
     * @return 所有的用户
     */
    List<User> findUserByRole(Long roleId);

    /**
     * 为角色新增
     * @param userIds
     * @param roleId
     * @return
     */
    Boolean updateUser(List<Long> userIds, Long roleId);
}
