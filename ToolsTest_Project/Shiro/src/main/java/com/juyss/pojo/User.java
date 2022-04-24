package com.juyss.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: User
 * @Desc: 用户实体类
 * @package com.juyss.pojo
 * @project Shiro
 * @date 2020/12/31 20:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色集合
     */
    private Set<Role> roles;

}
