package com.icepoint.framework.web.file.entity;

import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * 文件类型
 *
 * @author Jiawei Zhao
 */
@Table(name = "sys_file_type")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class FileType extends LongStdEntity {

    /**
     * 实体名称(一般是表名, 也可以根据应用自定义其含义)
     */
    @Column(name = "`entity_name`")
    private String entityName;

    /**
     * 文件类型名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 文件最大大小(字节)
     */
    @Column(name = "`max_size`")
    private Long maxSize;

    /**
     * 接受的文件类型(后缀名), 多个用,分隔
     */
    @NotEmpty
    @Column(name = "`acceptable_types`")
    private String acceptableTypes;

    /**
     * 包含的文件类型id，也就是可以把多种类型包含进来
     */
    @Column(name = "`included_ids`")
    private String includedIds;
}
