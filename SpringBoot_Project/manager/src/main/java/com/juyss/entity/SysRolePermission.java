package com.juyss.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: SysRolePermission
 * @Desc: 角色权限
 * @package com.juyss.entity
 * @project manager
 * @date 2021/1/12 17:57
 */
@Data
public class SysRolePermission implements Serializable {
    @TableId
    private String id;

    private String roleId;

    private String permissionId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}