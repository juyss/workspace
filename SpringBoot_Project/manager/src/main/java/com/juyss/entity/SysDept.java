package com.juyss.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: SysDept
 * @Desc: 部门
 * @package com.juyss.entity
 * @project manager
 * @date 2021/1/12 17:44
 */
@Data
public class SysDept implements Serializable {
    @TableId
    private String id;

    private String deptNo;

    @NotBlank(message = "机构名称不能为空")
    private String name;

    @NotBlank(message = "父级不能为空")
    private String pid;

    @TableField(exist = false)
    private String pidName;

    private Integer status;

    private String relationCode;

    private String deptManagerId;

    private String managerName;

    private String phone;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

}