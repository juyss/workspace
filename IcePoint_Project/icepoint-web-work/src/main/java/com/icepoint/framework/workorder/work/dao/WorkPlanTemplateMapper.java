package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.workorder.work.entity.WorkPlanTemplate;
import com.icepoint.framework.workorder.work.entity.WorkPlanTemplateDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface WorkPlanTemplateMapper extends RepositoryMapper<WorkPlanTemplate,Long> {
    /**
     * 作业计划内容模板表添加数据
     *
     * @param workPlanTemplate 模板对象
     * @return 添加条数
     */
    Long addTemplate(WorkPlanTemplate workPlanTemplate);

    /**
     * 作业计划内容模板表更新数据
     *
     * @param workPlanTemplate 模板对象
     * @return 更新条数
     */
    Integer updateTemplate(WorkPlanTemplate workPlanTemplate);

    /**
     * 常规批量添加作业计划内容模板详情表
     *
     * @param workPlanTemplateDetailList
     * @return 添加条数
     */
    Integer addTemplateDetail(@Param("templates") List<WorkPlanTemplateDetail> workPlanTemplateDetailList);

    /**
     * 作业计划内容模板详情表根据模板ID删除详情数据
     *
     * @param templateId
     * @return 删除条数
     */
    Integer deletePlnTemplateDetail(@Param("templateId") Long templateId);

    /**
     * 根据模板ID删除作业计划内容模板表及关联详情表数据
     *
     * @param id 需要操作的记录ID
     * @return 操作条数
     */
    Integer deleteTemplate(@Param("id") Long id);

    /**
     * 根据模板ID批量删除作业计划内容模板表及关联详情表数据
     *
     * @param ids 需要操作的记录ID
     * @return 操作条数
     */
    Integer deleteTemplates(@Param("id") List<Long> ids);


    /**
     * 根据记录ID数组删除作业计划内容模板详情表数据
     *
     * @param ids
     * @return
     */
    Integer deleteTemplateDetail(@Param("ids") List<Long> ids);



}
