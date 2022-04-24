package com.juyss.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: SysLog
 * @Desc: 系统日志
 * @package com.juyss.entity
 * @project manager
 * @date 2021/1/12 17:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysLog extends BaseEntity implements Serializable {
    @TableId
    private String id;

    private String userId;

    private String username;

    private String operation;

    private Integer time;

    private String method;

    private String params;

    private String ip;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private String startTime;

    @TableField(exist = false)
    private String endTime;

}
