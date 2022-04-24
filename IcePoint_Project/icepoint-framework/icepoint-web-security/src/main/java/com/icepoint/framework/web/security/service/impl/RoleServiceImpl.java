package com.icepoint.framework.web.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.data.annotation.ReadTransaction;
import com.icepoint.framework.web.core.util.MessageAssert;
import com.icepoint.framework.web.security.dao.RoleMapper;
import com.icepoint.framework.web.security.dao.RoleRepository;
import com.icepoint.framework.web.security.entity.Role;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.security.service.RoleService;
import com.icepoint.framework.web.security.util.SecurityMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private static final String REQUEST_MAP_CACHE = "ROLE_SERVICE::REQUEST_MAP";

    private final RoleRepository roleRepository;

    private final CacheManager cacheManager;

    private final RoleMapper mapper;

    @SuppressWarnings("unchecked")
    @ReadTransaction
    @Override
    public boolean hasRole(HttpServletRequest request, Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        MessageAssert.notNull(user, SecurityMessage.NOT_LOGIN.getCode());

        if (user.getAdmin() != null && user.getAdmin()) {
            return true;
        }

        Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
        Cache cache = cacheManager.getCache(REQUEST_MAP_CACHE);
        if (cache == null
                || (requestMap = (Map<RequestMatcher, Collection<ConfigAttribute>>) cache.get("map")) == null) {

            requestMap = loadRequestMap();
        }

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                Collection<ConfigAttribute> attributes = entry.getValue();
                Set<Role> roles = user.getRoles();
                if (CollectionUtils.isEmpty(roles)) {
                    return false;
                }
                for (ConfigAttribute attribute : attributes) {
                    boolean hasRole = roles.stream().anyMatch(role -> attribute.getAttribute().equals(role.getCode()));
                    if (hasRole) {
                        return true;
                    }
                }

                return false;
            }
        }

        return true;
    }

    private Map<RequestMatcher, Collection<ConfigAttribute>> loadRequestMap() {
        Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
        requestMap = new LinkedHashMap<>();

//        List<Permission> permissions = permissionRepository.findAll(false);

        new ArrayList<>().forEach(permission -> {
            List<Role> roles = new ArrayList<>();
//                    permission.getRoles();
            if (CollectionUtils.isEmpty(roles)) {
                return;
            }

            String[] attrs = roles.stream()
                    .map(Role::getCode)
                    .toArray(String[]::new);

            List<ConfigAttribute> configAttributes = SecurityConfig.createList(attrs);
            // FIXME
            AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher("permission.getPattern()");

            requestMap.put(requestMatcher, configAttributes);
        });
        return requestMap;
    }

    @Override
    public Role add(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public Role edit(Role entity) {
        return roleRepository.saveAndFlush(entity);
    }

    @Override
    public Integer delete(List<Long> ids) {
        roleRepository.deleteAllInId(ids);
        return ids.size();
    }

    @Override
    public Page<Role> list(Role entity, Pageable pageable) {
        if(ObjectUtils.isEmpty(entity)){
           return mapper.findAll(pageable);
        }
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = MapUtils.objectToLineMap(entity);
        queryWrapper.allEq(map);
        return mapper.findAll(queryWrapper, pageable);
    }

    @Override
    public Role findById(Long id) {
        // TODO Auto-generated method stub
        return roleRepository.getOne(id);
    }

    @Override
    public List<User> findUserByRole(Long roleId) {
        return  mapper.findUserByRole(roleId);
    }

    @Override
    public Boolean updateUser(List<Long> userIds, Long roleId) {
        if(ObjectUtils.isEmpty(userIds)){
            return false;
        }
        //删除原有关联的数据
        mapper.deleteRoleById(roleId);
        //新增数据
        userIds.forEach(item->{
            mapper.insertUserIds(item,roleId);
        });
        return true;
    }
}
