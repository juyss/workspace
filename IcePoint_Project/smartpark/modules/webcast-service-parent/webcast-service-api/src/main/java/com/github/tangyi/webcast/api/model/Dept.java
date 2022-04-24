package com.github.tangyi.webcast.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 部门
 *
 * @author tangyi
 * @date 2018/8/26 22:25
 */
@Data
public class Dept extends BaseEntity<Dept> {

    /**
     * type 通讯录类型： 园区yuanqu，企业qiye，社区shequ
     */
    protected String type;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    private String deptName;

    /**
     * 部门描述
     */
    private String deptDesc;

    /**
     * 部门负责人
     */
    private String deptLeader;

    /**
     * 父部门ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 华为中台数据的id
     */
    private String ztId;

    /**
     * 排序
     */
    private Integer level;

    /**
     * 统一信用代码
     */
    private String orgCode;

}
