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
 * @ClassName: SysUserRole
 * @Desc: 用户角色
 * @package com.juyss.entity
 * @project manager
 * @date 2021/1/12 17:58
 */
@Data
public class SysUserRole implements Serializable {
    @TableId
    private String id;

    private String userId;

    private String roleId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}