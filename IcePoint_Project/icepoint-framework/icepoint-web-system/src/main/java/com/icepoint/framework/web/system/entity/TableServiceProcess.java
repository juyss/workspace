package com.icepoint.framework.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.StdEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (SysTableServiceProcess)实体类
 *
 * @author makejava
 * @since 2021-06-17 09:47:21
 */
@TableName(value = "sys_table_service_process")
@Table(name = "sys_table_service_process")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class TableServiceProcess extends StdEntity<Long, Long> {

    /**
     * 表服务ID
     */
    @TableField("`table_service_id`")
    @Column(name = "`table_service_id`")
    private Long tableServiceId;

    /**
     * 服务名称
     */
    @TableField("`name`")
    @Column(name = "`name`")
    private String name;

    /**
     * 服务英文名称
     */
    @TableField("`name_en`")
    @Column(name = "`name_en`")
    private String nameEn;

    /**
     * xml文件路径
     */
    @TableField("`file_path`")
    @Column(name = "`file_path`")
    private String filePath;

    /**
     * 服务说明
     */
    @TableField("`description`")
    @Column(name = "`description`")
    private String description;

}
