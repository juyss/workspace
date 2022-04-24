package com.github.tangyi.common.security.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.BaseEntity;
import com.github.tangyi.common.security.constant.SecurityConstant;
import com.github.tangyi.common.security.ty.MainUserWithRole;
import com.github.tangyi.mapper.RbacMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired(required = false)
    private RbacMapper rbacMapper;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principle = authentication.getPrincipal();
        String username = null;
        boolean hasPermission = true;
        if (principle instanceof UserDetails) {
            username = ((UserDetails) principle).getUsername();
        } else if (principle instanceof String) {
            username = (String) principle;
        }

        List<RbacServiceImpl.Menu> menus1 = rbacMapper.getAllMenu();
        List<String> collect1 = menus1.stream().map(RbacServiceImpl.Menu::getUrl).collect(Collectors.toList());

        Set<String> allUrls = new HashSet<>(collect1);
        boolean exit = false;
        for (String url : allUrls) {
            if (StringUtils.isEmpty(url)) continue;
            if (antPathMatcher.match(url, request.getRequestURI())) {
                exit = true;
                break;
            }
        }

        if (exit && !StringUtils.isEmpty(username)) {
            List<Menu> menus;
            if (username.startsWith(MainUserWithRole.IDENTIFIER_PREFIX)) {// 来自统一认证的用户
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                Set<String> roles = authorities.stream().map(item -> item.getAuthority().toLowerCase()).collect(Collectors.toSet());
                 menus = rbacMapper.getAllMenuByRoleNames(roles);

            } else {
                menus = rbacMapper.getMenuByUsername(username);
            }

            List<String> collect = menus.stream().map(Menu::getUrl).collect(Collectors.toList());

            Set<String> urls = new HashSet<>(collect);
            exit = false;
            for (String url : urls) {
                if (StringUtils.isEmpty(url)) continue;
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    exit = true;
                    break;
                }
            }
            hasPermission = exit;
        }


        return hasPermission;
    }

    @Data
    public static class Menu extends BaseEntity<Menu> {

        /**
         * 菜单名称
         */
        @NotBlank(message = "菜单名称不能为空")
        private String name;

        /**
         * 菜单权限标识
         */
        private String permission;

        /**
         * url
         */
        private String url;

        /**
         * 重定向url
         */
        private String redirect;

        /**
         * 父菜单ID
         */
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private Long parentId;

        /**
         * 图标
         */
        private String icon;

        /**
         * 排序号
         */
        private String sort;

        /**
         * 类型
         */
        private Integer type;

        /**
         * 模块
         */
        private String component;

        /**
         * 路径
         */
        private String path;

        /**
         * 备注
         */
        private String remark;
    }
}
