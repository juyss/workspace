package com.juyss.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Permission
 * @Desc: 权限实体类
 * @package com.juyss.pojo
 * @project Shiro
 * @date 2020/12/31 20:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    /**
     * 权限ID
     */
    private String id;

    /**
     * 权限名称
     */
    private String permissionName;


}
