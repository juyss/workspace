package com.juyss.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.juyss.vo.resp.DeptRespNodeVO;
import com.juyss.vo.resp.PermissionRespNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: SysRole
 * @Desc: 角色
 * @package com.juyss.entity
 * @project manager
 * @date 2021/1/12 17:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SysRole extends BaseEntity implements Serializable {
    @TableId
    private String id;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String description;

    private Integer status;

    private Integer dataScope;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @TableField(exist = false)
    private List<PermissionRespNode> permissionRespNodes;
    @TableField(exist = false)
    private List<DeptRespNodeVO> deptRespNodes;

    @TableField(exist = false)
    private String startTime;

    @TableField(exist = false)
    private String endTime;

    @TableField(exist = false)
    private List<String> permissions;

    @TableField(exist = false)
    private List<String> depts;

}