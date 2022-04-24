package com.icepoint.framework.workorder.work.service;

import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.workorder.work.entity.FieldMetadataWithObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.workorder.work.entity.WorkTaskClock;

import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 */
public interface WorkTaskService {

    /**
     * 修改作业计划内容模板
     *
     * @param id         主键
     * @param type       作业类型
     * @param code       模板代码
     * @param name       模板名称
     * @param note       备注
     * @param platformId 平台
     * @param ownerId    所有者
     * @param appId      应用编号
     * @param deleted    是否删除
     * @param ...        作业计划内容模板其他扩展属性
     * @param details    作业计划内容模板详情
     * @param --         workObjAssetDefId 作业对象资产定义ID
     * @param --         workObj 作业对象
     * @param --         workObjName 作业对象名
     * @param --         workRootObjAssetDefId 作业对象根节点资产定义ID
     * @param --         workRootObj 作业对象根节点
     * @param --         workRootObjName 作业对象根节点名
     * @param --         startTime 开始时间
     * @param --         endTime 结束时间
     * @param --         sendTime 下发时间
     * @param --         content 作业内容
     * @param --         cost 作业耗时
     * @param --         note 备注
     * @param --         ... 作业计划内容模板详情扩展属性
     * @return 作业计划内容模板
     */
    Map<String, Object> addTemplate(Map<String, Object> template);

    /**
     * 批量删除作业计划内容模板详情
     *
     * @param ids 作业计划内容模板详情主键数组
     * @return 删除条数
     * @author
     */
    Integer deleteTemplateDetail(List<Long> ids);

    /**
     * 修改作业计划内容模板
     *
     * @param id         主键
     * @param type       作业类型
     * @param code       模板代码
     * @param name       模板名称
     * @param note       备注
     * @param platformId 平台
     * @param ownerId    所有者
     * @param appId      应用编号
     * @param deleted    是否删除
     * @param ...        作业计划内容模板其他扩展属性
     * @param details    作业计划内容模板详情
     * @param --         workObjAssetDefId 作业对象资产定义ID
     * @param --         workObj 作业对象
     * @param --         workObjName 作业对象名
     * @param --         workRootObjAssetDefId 作业对象根节点资产定义ID
     * @param --         workRootObj 作业对象根节点
     * @param --         workRootObjName 作业对象根节点名
     * @param --         startTime 开始时间
     * @param --         endTime 结束时间
     * @param --         sendTime 下发时间
     * @param --         content 作业内容
     * @param --         cost 作业耗时
     * @param --         note 备注
     * @param --         ... 作业计划内容模板详情扩展属性
     * @return 作业计划内容模板
     */
    Map<String, Object> updateTemplate(Map<String, Object> template);

    /**
     * 删除作业计划内容模板
     *
     * @param ids 作业计划内容模板主键数组
     * @return 删除的条数
     * @author
     */
    Integer deleteTemplate(List<Long> ids);

    /**
     * 列表查询作业计划内容模板
     *
     * @param template 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    Page<Map<String, Object>> templateList(Map<String, Object> template, Pageable pageable);

    /**
     * 详情查询作业计划内容模板
     *
     * @param id 作业计划内容模板主键
     * @return 作业计划内容模板
     */
    Map<String, Object> getTemplateById(Long id);
    
    /**
     * 上传要被导入的模板详情文件
     * 后端检查后返回结果，前端获取结果，将其添加到模板对象中，然后调用模板的增修功能实现
     * @param file Excle文件
     * @return 数据检查结果
     *         返回上传Excle文件中的每一条数据，以及该数据说明，确定该数据导入是否有问题
     *         检查各业务自己做，需要判断是否可以根据元数据直接判断
     */
    List<Map<String, Object>> uploadTemplateDetail(MultipartFile file);
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  作业计划
    //////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 新增作业计划
     *
     * @param workPlan 新增的数据
     * @return 返回新增后的数据
     */
    Map<String, Object> addPlan(Map<String, Object> workPlan);

    /**
     * 编辑作业计划
     *
     * @param workPlan 编辑的数据
     * @return 返回编辑后的数据
     */
    Map<String, Object> updatePlan(Map<String, Object> workPlan);

    Integer deletePlan(List<Long> ids);

    /**
     * 列表查询作业计划
     *
     * @param template 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    Page<Map<String, Object>> listPlan(Map<String, Object> template, Pageable pageable);
    
    /**
     * 查询作业计划详情
     * 
     * @param id 作业计划ID
     * @return
     */
    Map<String, Object> findPlanById(Long id);
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  PC-作业任务
    //////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 新增作业任务
     *
     * @param workTask 新增的作业任务
     * @return 返回新增后的作业任务
     */
    Map<String, Object> addTask(Map<String, Object> workTask);

    /**
     * 编辑作业任务
     *
     * @param workTask 编辑的作业任务
     * @return 返回编辑后的作业任务
     */
    Map<String, Object> updateTask(Map<String, Object> workTask);

    /**
     * 批量删除作业任务
     * @param ids id集合
     * @return
     */
    Integer deleteTask(List<Long> ids);

    /**
     * 列表查询作业任务
     *
     * @param template 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    Page<Map<String, Object>> listTask(Map<String, Object> template, Pageable pageable);
    
    /**
     * 查询作业任务详情
     * 
     * @param id 作业任务ID
     * @return
     */
   Map<String, Object>  findTaskById(Long id);

    /**
     * 催办
     * @param workId 任务id
     * @return
     */
    Boolean urgeOrder(Long workId);

    /**
     * 获取作业任务打卡分页列表
     * 
     * @param filter 打卡查询条件
     * @param pageable 分页参数
     * @return 打卡分页列表
     */
    Page<WorkTaskClock> findTaskClock(WorkTaskClock filter, Pageable pageable);
    	
    /**
	 * 作业任务打卡
	 * 
	 * @param entity 打卡信息 {@linkplain com.icepoint.framework.workorder.work.entity.WorkTaskClock WorkTaskClock}
	 * @return 打卡结果 {@linkplain com.icepoint.framework.workorder.work.entity.WorkTaskClock WorkTaskClock}
	 */
    WorkTaskClock taskClock(WorkTaskClock entity);
    
    /**
     * 作业任务关联问题列表
     *
     * @param filter 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    Page<Map<String, Object>> listDefect(Map<String, Object> filter, Pageable pageable);
    
    /**
     * 查询作业任务关联问题列表
     * 
     * @param type 问题类型
     *        1-上报问题
     *        2-工单
     * @param id 作业任务关联问题ID
     * @return
     */
    Map<String, Object> findDefectById(Integer type,Long id);
    
    /**
     * 根据资产定义编码获取字段元数据信息
     * @author Juyss
     * @param code 资产定义编码
     * @return List<FieldMetadata>
     */
    List<FieldMetadataWithObject> getFieldInfoByAssetsCode(String code);

    /**
     * 从redis中查找元数据
     * @param code 资产定义
     * @return
     */
    TableMetadata getTable(String code);

    /**
     * 用户分页 + 资产绑定
     * @param user
     * @param pageable
     * @return
     */
    Page<Map<String,Object>> userPageList(User user, Pageable pageable);
}
