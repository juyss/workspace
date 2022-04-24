package com.icepoint.framework.workorder.work.controller;

import com.icepoint.framework.web.core.response.CollectionResponse;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.workorder.work.entity.FieldMetadataWithObject;
import com.icepoint.framework.workorder.work.entity.WorkTaskClock;
import com.icepoint.framework.workorder.work.service.WorkTaskService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 作业管理控制层
 *
 * @author admin
 * @version 1.0
 * @ClassName WorkTaskController
 * @description
 * @since 2021-07-07 11:33
 */
@RestController
@RequestMapping("workTask")
public class WorkTaskController {

    @Resource
    private WorkTaskService workTaskService;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  PC-作业计划内容模板
    //////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 新增作业计划内容模板
     *
     * @param id        主键
     * @param type      作业类型
     * @param code      模板代码
     * @param name      模板名称
     * @param note      备注
     * @param ...       作业计划内容模板其他扩展属性
     * @param details[] 作业计划内容模板详情 数组
     * @param --        workObjAssetDefId 作业对象资产定义ID
     * @param --        workObj 作业对象
     * @param --        workObjName 作业对象名
     * @param --        workRootObjAssetDefId 作业对象根节点资产定义ID
     * @param --        workRootObj 作业对象根节点
     * @param --        workRootObjName 作业对象根节点名
     * @param --        startTime 开始时间
     * @param --        endTime 结束时间
     * @param --        sendTime 下发时间
     * @param --        content 作业内容
     * @param --        cost 作业耗时
     * @param --        note 备注
     * @param --        ... 作业计划内容模板详情扩展属性
     * @return 作业计划内容模板
     */
    @PostMapping("addTemplate")
    public Response<Map<String, Object>> addTemplate(@RequestBody Map<String, Object> template) {
        return ResponseUtils.any(workTaskService.addTemplate(template));
    }

    /**
     * 批量删除作业计划内容模板详情
     *
     * @param ids 作业计划内容模板详情主键数组
     * @return DefaultResponse<Integer>
     * @author admin
     */
    @DeleteMapping("deleteTemplateDetail")
    public Response<Integer> deleteTemplateDetail(@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.any(workTaskService.deleteTemplateDetail(ids));
    }

    /**
     * 修改作业计划内容模板
     *
     * @param id        主键
     * @param type      作业类型
     * @param code      模板代码
     * @param name      模板名称
     * @param note      备注
     * @param ...       作业计划内容模板其他扩展属性
     * @param details[] 作业计划内容模板详情 数组
     * @param --        workObjAssetDefId 作业对象资产定义ID
     * @param --        workObj 作业对象
     * @param --        workObjName 作业对象名
     * @param --        workRootObjAssetDefId 作业对象根节点资产定义ID
     * @param --        workRootObj 作业对象根节点
     * @param --        workRootObjName 作业对象根节点名
     * @param --        startTime 开始时间
     * @param --        endTime 结束时间
     * @param --        sendTime 下发时间
     * @param --        content 作业内容
     * @param --        cost 作业耗时
     * @param --        note 备注
     * @param --        ... 作业计划内容模板详情扩展属性
     * @return 作业计划内容模板
     */
    @PutMapping("updateTemplate")
    public Response<Map<String, Object>> updateTemplate(@RequestBody Map<String, Object> template) {
        return ResponseUtils.any(workTaskService.updateTemplate(template));
    }

    /**
     * 删除作业计划内容模板
     *
     * @param ids 作业计划内容模板主键数组
     * @return DefaultResponse<Integer>
     * @author admin
     */
    @DeleteMapping("deleteTemplate")
    public Response<Integer> deleteTemplate(@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.any(workTaskService.deleteTemplate(ids));
    }

    /**
     * 列表查询作业计划内容模板
     *
     * @param template 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    @GetMapping("templateList")
    public PageResponse<Map<String, Object>> templateList(Map<String, Object> template, Pageable pageable) {
        return ResponseUtils.page(workTaskService.templateList(template, pageable));
    }

    /**
     * 查询作业计划内容模板条件实体
     *
     * @param id        主键
     * @param type      作业类型
     * @param code      模板代码
     * @param name      模板名称
     * @param note      备注
     * @param ...       作业计划内容模板其他扩展属性
     * @param details[] 作业计划内容模板详情 数组
     * @param --        workObjAssetDefId 作业对象资产定义ID
     * @param --        workObj 作业对象
     * @param --        workObjName 作业对象名
     * @param --        workRootObjAssetDefId 作业对象根节点资产定义ID
     * @param --        workRootObj 作业对象根节点
     * @param --        workRootObjName 作业对象根节点名
     * @param --        startTime 开始时间
     * @param --        endTime 结束时间
     * @param --        sendTime 下发时间
     * @param --        content 作业内容
     * @param --        cost 作业耗时
     * @param --        note 备注
     * @param --        ... 作业计划内容模板详情扩展属性
     * @return 作业计划内容模板
     */
    @GetMapping("getTemplateById")
    public Response<Map<String, Object>> getTemplateById(@RequestParam Long id) {
        return ResponseUtils.any(workTaskService.getTemplateById(id));
    }

    @PostMapping("uploadTemplateDetail")
    public PageResponse<List<Map<String, Object>>> uploadTemplateDetail(@RequestBody MultipartFile file) {
        return ResponseUtils.page(null); //return ResponseUtils.page(workTaskService.uploadTemplateDetail(file));
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  PC-作业计划
    //////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 新增作业计划
     *
     * @param templateId     作业模版id
     * @param assignedDeptId 指派部门id
     * @param assignedUserId 指派人id
     * @param workType       作业类型
     * @param code           计划编码
     * @param name           计划名称
     * @param cycleType      周期类型
     * @param firstTime      第一次任务开始时间
     * @param lastTime       上次计划执行时间
     * @param generateTime   每期任务生成时间，T+N分钟
     * @param note           注意事项
     * @param planStatus     计划状态  未启用 已启用 已停用  1，2，3
     * @param executeMode    作业模式 1-有一人完成即可 2-多人都完成方可
     * @param overdueAging   逾期时效
     * @param autoDispatch   是否自动派工
     * @param assistants     协助人列表
     * @return 返回新增后的数据
     */
    @PostMapping("addPlan")
    public Response<Map<String, Object>> addPlan(@RequestBody Map<String, Object> workPlan) {
        // FIXME: jiawei - 需要指定作业类型workType，需要指定计划状态为：计划创建
        return ResponseUtils.one(workTaskService.addPlan(workPlan));
    }

    /**
     * 编辑作业计划
     *
     * @param id             主键
     * @param templateId     作业模版id
     * @param assignedDeptId 指派部门id
     * @param assignedUserId 指派人id
     * @param workType       作业类型
     * @param code           计划编码
     * @param name           计划名称
     * @param cycleType      周期类型
     * @param firstTime      第一次任务开始时间
     * @param lastTime       上次计划执行时间
     * @param generateTime   每期任务生成时间，T+N分钟
     * @param note           注意事项
     * @param planStatus     计划状态  未启用 已启用 已停用  1，2，3
     * @param executeMode    作业模式 1-有一人完成即可 2-多人都完成方可
     * @param overdueAging   逾期时效
     * @param autoDispatch   是否自动派工
     * @param assistants     协助人列表
     * @return 返回编辑后的数据
     */
    @PostMapping("updatePlan")
    public Response<Map<String, Object>> updatePlan(@RequestBody Map<String, Object> workPlan) {
        return ResponseUtils.one(workTaskService.updatePlan(workPlan));
    }

    /**
     * 删除作业计划
     *
     * @param ids 主键集合 1,2,3
     * @return 删除的数量
     * @author Juyss
     */
    @DeleteMapping("deletePlan")
    public Response<Integer> deletePlan(@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.any(workTaskService.deletePlan(ids));
    }

    /**
     * 列表查询作业计划
     *
     * @param template 查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    @GetMapping("listPlan")
    public PageResponse<Map<String, Object>> listPlan(Map<String, Object> template, Pageable pageable) {
        return ResponseUtils.page(workTaskService.listPlan(template, pageable));
    }

    /**
     * 查询作业计划详情
     *
     * @param id 作业计划ID
     * @return 计划详情
     */
    @GetMapping("findPlanById/{id}")
    public Response<Map<String, Object>> findPlanById(@PathVariable("id") Long id) {
        return ResponseUtils.any(workTaskService.findPlanById(id));
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  PC-作业任务
    //////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 新增作业任务
     *
     * @param workType              作业类型
     * @param code                  任务编码
     * @param name                  任务名称
     * @param startTime             开始时间
     * @param endTime               结束时间
     * @param planTime              计划下发时间
     * @param realTime              实际下发时间
     * @param workObjAssetDefId     作业对象资产定义ID
     * @param workObj               作业对象
     * @param workObjName           作业对象名
     * @param workRootObjAssetDefId 作业对象根节点资产定义ID
     * @param workRootObj           作业对象根节点
     * @param content               作业内容
     * @param cost                  作业耗时
     * @param note                  注意事项
     * @param workOrg               作业组织
     * @param workRootOrg           作业组织根节点
     * @param planId                所属计划
     * @param task_status           任务状态
     * @param execute_mode          作业模式
     * @param overdue               逾期状态
     * @param completeTime          完工时间
     * @param completeContent       完结说明
     * @param completeUser          完工人
     * @param urgingTime            催办时间
     * @param assetsId              协助人Id
     * @param workTask              新增的作业任务
     * @return 返回新增后的作业任务
     */
    @PostMapping("addTask")
    public Response<Map<String, Object>> addTask(@RequestBody Map<String, Object> workTask) {
        return ResponseUtils.one(workTaskService.addTask(workTask));
    }

    /**
     * 修改作业任务
     *
     * @param id                    id
     * @param workType              作业类型
     * @param code                  任务编码
     * @param name                  任务名称
     * @param startTime             开始时间
     * @param endTime               结束时间
     * @param planTime              计划下发时间
     * @param realTime              实际下发时间
     * @param workObjAssetDefId     作业对象资产定义ID
     * @param workObj               作业对象
     * @param workObjName           作业对象名
     * @param workRootObjAssetDefId 作业对象根节点资产定义ID
     * @param workRootObj           作业对象根节点
     * @param content               作业内容
     * @param cost                  作业耗时
     * @param note                  注意事项
     * @param workOrg               作业组织
     * @param workRootOrg           作业组织根节点
     * @param planId                所属计划
     * @param task_status           任务状态
     * @param execute_mode          作业模式
     * @param overdue               逾期状态
     * @param completeTime          完工时间
     * @param completeContent       完结说明
     * @param completeUser          完工人
     * @param urgingTime            催办时间
     * @param assetsId              协助人Id
     * @param workTask              新增的作业任务
     * @return 返回新增后的作业任务
     */
    @PutMapping("updateTask")
    public Response<Map<String, Object>> updateTask(@RequestBody Map<String, Object> workTask) {
        return ResponseUtils.one(workTaskService.updateTask(workTask));
    }

    /**
     * 删除作业任务
     *
     * @param ids id集合
     * @return
     */
    @DeleteMapping("deleteTask")
    public Response<Integer> deleteTask(@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.any(workTaskService.deleteTask(ids));
    }

    /**
     * 分页查询作业任务 条件
     *
     * @param id                    id
     * @param workType              作业类型
     * @param code                  任务编码
     * @param name                  任务名称
     * @param startTime             开始时间
     * @param endTime               结束时间
     * @param planTime              计划下发时间
     * @param realTime              实际下发时间
     * @param workObjAssetDefId     作业对象资产定义ID
     * @param workObj               作业对象
     * @param workObjName           作业对象名
     * @param workRootObjAssetDefId 作业对象根节点资产定义ID
     * @param workRootObj           作业对象根节点
     * @param content               作业内容
     * @param cost                  作业耗时
     * @param note                  注意事项
     * @param workOrg               作业组织
     * @param workRootOrg           作业组织根节点
     * @param planId                所属计划
     * @param task_status           任务状态
     * @param execute_mode          作业模式
     * @param overdue               逾期状态
     * @param completeTime          完工时间
     * @param completeContent       完结说明
     * @param completeUser          完工人
     * @param urgingTime            催办时间
     * @param assetsId              协助人Id
     * @param workTask              新增的作业任务
     * @return 返回新增后的作业任务
     */
    @GetMapping("listTask")
    public PageResponse<Map<String, Object>> listTask(Map<String, Object> template, Pageable pageable) {
        return ResponseUtils.page(workTaskService.listTask(template, pageable));
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return workType 作业类型
     * @return code 任务编码
     * @return name 任务名称
     * @return startTime 开始时间
     * @return endTime 结束时间
     * @return planTime 计划下发时间
     * @return realTime 实际下发时间
     * @return workObjAssetDefId 作业对象资产定义ID
     * @return workObj 作业对象
     * @return workObjName 作业对象名
     * @return workRootObjAssetDefId 作业对象根节点资产定义ID
     * @return workRootObj 作业对象根节点
     * @return content  作业内容
     * @return cost 作业耗时
     * @return note 注意事项
     * @return workOrg 作业组织
     * @return workRootOrg 作业组织根节点
     * @return planId 所属计划
     * @return task_status 任务状态
     * @return execute_mode 作业模式
     * @return overdue 逾期状态
     * @return completeTime 完工时间
     * @return completeContent 完结说明
     * @return completeUser 完工人
     * @return urgingTime 催办时间
     * @return assetsId 协助人Id
     * @return workTask 新增的作业任务
     */
    @GetMapping("findTaskById/{id}")
    public Response<Map<String, Object>> findTaskById(@PathVariable("id") Long id) {
        return ResponseUtils.any(workTaskService.findTaskById(id));
    }

    /**
     * 获取作业任务打卡分页列表
     *
     * @param filter   打卡查询条件
     * @param pageable 分页参数
     * @return 打卡分页列表
     */
    @GetMapping("findTaskClock")
    public PageResponse<WorkTaskClock> findTaskClock(WorkTaskClock filter, Pageable pageable) {
        return ResponseUtils.page(workTaskService.findTaskClock(filter, pageable));
    }

    /**
     * 作业任务打卡 未完成
     *
     * @param entity 打卡信息 {@linkplain com.icepoint.framework.workorder.work.entity.WorkTaskClock WorkTaskClock}
     * @return 打卡结果 {@linkplain com.icepoint.framework.workorder.work.entity.WorkTaskClock WorkTaskClock}
     */
    @PutMapping("taskClock")
    public Response<WorkTaskClock> taskClock(@RequestBody WorkTaskClock entity) {
        return ResponseUtils.one(workTaskService.taskClock(entity));
    }

    /**
     * 作业任务关联问题列表
     *
     * @param filter   查询实体
     * @param pageable 分页对象
     * @return 所有数据
     */
    @GetMapping("listDefect")
    public PageResponse<Map<String, Object>> listDefect(Map<String, Object> filter, Pageable pageable) {
        return ResponseUtils.page(workTaskService.listDefect(filter, pageable));
    }

    /**
     * 查询作业任务关联问题列表
     *
     * @param type 问题类型
     *             1-上报问题
     *             2-工单
     * @param id   作业任务关联问题ID
     * @return
     */
    @GetMapping("findDefectById/{id}")
    public Response<Map<String, Object>> findDefectById(@RequestParam("type") Integer type, @PathVariable("id") Long id) {
        return ResponseUtils.any(workTaskService.findDefectById(type, id));
    }

    /**
     * 根据资产定义编码获取字段元数据信息
     *
     * @param code 资产定义编码
     * @return CollectionResponse<FieldMetadata>
     * @author Juyss
     */
    @GetMapping("getFieldInfoByAssetsCode")
    public CollectionResponse<FieldMetadataWithObject> getFieldInfoByAssetsCode(@RequestParam("code") String code) {
        return ResponseUtils.many(workTaskService.getFieldInfoByAssetsCode(code));
    }

    /**
     * security 和 system 不能相互引用  所以先写在这儿
     */

    @GetMapping("userPageList")
    public PageResponse<Map<String, Object>> userPageList(User user, Pageable pageable) {
        return ResponseUtils.page(workTaskService.userPageList(user, pageable));
    }


}
