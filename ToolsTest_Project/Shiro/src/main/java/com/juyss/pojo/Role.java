package com.juyss.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Role
 * @Desc: 角色实体类
 * @package com.juyss.pojo
 * @project Shiro
 * @date 2020/12/31 20:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    /**
     * 角色ID
     */
    private String id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色拥有的权限集合
     */
    private Set<Permission> permissions;
}
