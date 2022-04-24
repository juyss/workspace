package com.juyss.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: SysFilesEntity
 * @Desc: 文件上传
 * @package com.juyss.entity
 * @project manager
 * @date 2021/1/12 17:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_files")
public class SysFilesEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private String id;

    /**
     * URL地址
     */
    @TableField("url")
    private String url;

    /**
     * 创建时间
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;

    @TableField("file_name")
    private String fileName;

    @TableField("file_path")
    private String filePath;


}

