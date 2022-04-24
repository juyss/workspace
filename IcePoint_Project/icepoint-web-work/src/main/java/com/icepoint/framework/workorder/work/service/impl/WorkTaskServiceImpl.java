package com.icepoint.framework.workorder.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.icepoint.framework.core.function.ListContainObj;
import com.icepoint.framework.core.function.MakeList;
import com.icepoint.framework.core.io.Serializers;
import com.icepoint.framework.core.support.TypeReferences;
import com.icepoint.framework.core.util.BeanUtils;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.security.service.UserService;
import com.icepoint.framework.web.system.dao.AssetMapper;
import com.icepoint.framework.web.system.dao.LabelDefMapper;
import com.icepoint.framework.web.system.dao.LabelMapper;
import com.icepoint.framework.web.system.entity.*;
import com.icepoint.framework.web.system.service.AssetService;
import com.icepoint.framework.web.system.service.ResourceService;
import com.icepoint.framework.workorder.work.dao.*;
import com.icepoint.framework.workorder.work.entity.*;
import com.icepoint.framework.workorder.work.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.icepoint.framework.core.function.Processors.listContainObj;

@Service
@RequiredArgsConstructor
public class WorkTaskServiceImpl implements WorkTaskService {
    private final WorkPlanTemplateMapper workPlanTemplateMapper;
    private final WorkPlanMapper workPlanMapper;
    private final WorkTaskClockMapper workTaskClockMapper;
    private final WorkPlanTemplateDetailMapper workPlanTemplateDetailMapper;
    private final WorkPlanRepository workPlanRepository;
    private final WorkTaskRepository workTaskRepository;
    private final WorkTaskClockRepository workTaskClockRepository;
    private final AssetService assetService;
    private final ExcelService excelService;
    private final CheckPointService checkPointService;
    private final ResourceService resourceService;
    private final WorkDefectService workDefectService;
    List<Map<String, Object>> list = new ArrayList<>();
    private final WorkOrderMapper workOrderMapper;
    private final WorkDefectMapper workDefectMapper;
    private final WorkTaskNoteMapper workTaskNoteMapper;
    @Resource
    private RedisTemplate redisTemplate;
    private final WorkTaskMapper workTaskMapper;
    private final WorkOrderAssistantService workOrderAssistantService;
    private final UserService service;
    private final LabelMapper labelMapper;
    private final LabelDefMapper labelDefMapper;
    private final AssetMapper assetMapper;


    /**
     * 新增内容模板
     *
     * @param template
     * @return
     */
    @Override
    public Map<String, Object> addTemplate(Map<String, Object> template) {
        //获取workPlanTemplateAssetId id
        AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_PLAN_TMPL").orElseThrow(() -> new IllegalArgumentException("未定义资产"));
        WorkPlanTemplate workPlanTemplate = BeanUtils.toBean(template, WorkPlanTemplate.class,true,false);
        workPlanTemplateMapper.insert(workPlanTemplate);
        //添加扩展属性字段
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(template);
        template.put("id", workPlanTemplate.getId());
        assetService.addAllObjAttr(assetDefine.getId(), list);

        //获取模板详情
        List<Map<String, Object>> details = CastUtils.cast(template.get("details"));
        if(!ObjectUtils.isEmpty(details)){
            for (Map<String, Object> detail : details) {
                WorkPlanTemplateDetail workPlanTemplateDetail = BeanUtils.toBean(detail, WorkPlanTemplateDetail.class,true,false);
                workPlanTemplateDetail.setTemplateId(workPlanTemplate.getId());
                workPlanTemplateDetailMapper.insert(workPlanTemplateDetail);
                detail.put("id", workPlanTemplateDetail.getId());
            }
            AssetDefine workOrdPlanTmplItemAsset = assetService.getAssetDef("WORK_ORD_PLAN_TMPL_ITEM").orElseThrow(() -> new IllegalArgumentException("未定义资产"));
            //添加模板详情扩展属性
            assetService.addAllObjAttr(workOrdPlanTmplItemAsset.getId(), details);
        }
        return template;

    }

    /**
     * @param ids 作业计划内容模板详情主键数组
     * @return 删除条数
     */
    @Override
    public Integer deleteTemplateDetail(List<Long> ids) {
        AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_PLAN_TMPL").orElseThrow(() -> new IllegalArgumentException("未定义资产"));
        //删除扩展属性
        assetService.deleteAllObjAttrByObjId(assetDefine.getId(), ids);
        return workPlanTemplateMapper.deleteInBatchIds(ids);
    }

    /**
     * @param template
     * @return 更新的内容
     */
    @Override
    public Map<String, Object> updateTemplate(Map<String, Object> template) {
        //获取workPlanTemplateAssetId id
        AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_PLAN_TMPL").orElseThrow(() -> new IllegalArgumentException("未定义资产"));
        WorkPlanTemplate workPlanTemplate = BeanUtils.toBean(template, WorkPlanTemplate.class,true,false);
        workPlanTemplateMapper.update(workPlanTemplate);
        //修改扩展属性字段
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(template);
        template.put("id", workPlanTemplate.getId());
        assetService.updateAllObjAttr(assetDefine.getId(), list);
        //获取模板详情
        List<Map<String, Object>> details = CastUtils.cast(template.get("details"));
        if (ObjectUtils.isEmpty(details)) {
            return template;
        }
        for (Map<String, Object> detail : details) {
            WorkPlanTemplateDetail workPlanTemplateDetail = BeanUtils.toBean(detail, WorkPlanTemplateDetail.class,true,false);
            workPlanTemplateDetailMapper.update(workPlanTemplateDetail);
        }
        AssetDefine workOrdPlanTmplItemAsset = assetService.getAssetDef("WORK_ORD_PLAN_TMPL_ITEM").orElseThrow(() -> new IllegalArgumentException("未定义资产"));

        assetService.updateAllObjAttr(workOrdPlanTmplItemAsset.getId(), details);
        return template;
    }


    @Override
    public Integer deleteTemplate(List<Long> ids) {
        //TODO 判断是否能删出 现在没有判断
        LambdaQueryWrapper<WorkPlan> queryDetail = new LambdaQueryWrapper();
        workPlanTemplateMapper.deleteInBatchIds(ids);
        LambdaQueryWrapper<WorkPlanTemplateDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(WorkPlanTemplateDetail::getTemplateId,ids);
        List<WorkPlanTemplateDetail> all = workPlanTemplateDetailMapper.findAll(queryWrapper);
        if(!ObjectUtils.isEmpty(all)){
            List<Long> collect = all.stream().map(WorkPlanTemplateDetail::getId).collect(Collectors.toList());
            workPlanTemplateDetailMapper.deleteInBatchIds(collect);
        }
        return ids.size();
    }

    @Override
    public Page<Map<String, Object>> templateList(Map<String, Object> template, Pageable pageable) {
        TableMetadata tableMetadata = getTable("WORK_ORD_PLAN_TMPL");
        //查询类容模板的扩展字段
        QueryWrapper<WorkPlanTemplate> wrapper = new QueryWrapper<>();
        Page<WorkPlanTemplate> workPlanTemplatesPage;
        if (!ObjectUtils.isEmpty(template)) {
            wrapper.allEq(template);
            workPlanTemplatesPage = workPlanTemplateMapper.findAll(wrapper, pageable);
        } else {
            workPlanTemplatesPage = workPlanTemplateMapper.findAll(pageable);
        }
        long totalElements = workPlanTemplatesPage.getTotalElements();
        List<Map<String, Object>> allList = new ArrayList<>();
        //获取所有的扩展字段
        for (WorkPlanTemplate planTemplate : workPlanTemplatesPage) {
            //查询扩展字段
            Map<String, Object> allObjAttrById = assetService.findAllObjAttrById(tableMetadata.getId(), planTemplate.getId());
            Map<String, Object> map = MapUtils.objectToMap(planTemplate);
            map.putAll(allObjAttrById);
            allList.add(map);
        }
        Page<Map<String, Object>> page = new PageImpl<>(allList, pageable, totalElements);
        return page;
    }

    @Override
    public Map<String, Object> getTemplateById(Long id) {
        TableMetadata tableMetadata = getTable("WORK_ORD_PLAN_TMPL");
        WorkPlanTemplate workPlanTemplate = workPlanTemplateMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("未查询到内容模板"));
        Map<String, Object> allObjAttrById = assetService.findAllObjAttrById(tableMetadata.getId(), workPlanTemplate.getId());
        //查询模板详情
        LambdaQueryWrapper<WorkPlanTemplateDetail> wrapper = Wrappers.lambdaQuery(WorkPlanTemplateDetail.class);
        wrapper.eq(WorkPlanTemplateDetail::getTemplateId, id);
        List<WorkPlanTemplateDetail> detailList = workPlanTemplateDetailMapper.findAll(wrapper);
        workPlanTemplate.setWorkPlanTemplateDetails(detailList);
        Map<String, Object> map = MapUtils.objectToMap(workPlanTemplate);
        allObjAttrById.putAll(map);
        return allObjAttrById;
    }

    @Override
    public List<Map<String, Object>> uploadTemplateDetail(MultipartFile multfile) {
        boolean flag = false;
        try {
            CommonsMultipartFile cf = (CommonsMultipartFile) multfile;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            File file = fi.getStoreLocation();
            //模板中的字段
            List<String> fields = new ArrayList<>();
            //通用Excle服务，读取Exle，数据放入列表中
            //看list怎么处理下，避免多并发时候冲突
            list = excelService.uploadFile(file, 1, fields);

            //对数据进行检查，对每一条数据增加处理结果，如用checkMark标识状态，用msg表示检查结果
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map m1 = list.get(i);
                    for (int j = i + 1; j < list.size(); j++) {
                        Map m2 = list.get(j);
                        //数据检查，自身数据不能重复
                    }
                    //必要情况下，需要和数据库中数据对比
                }
                return list;
            }
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  作业计划
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Map<String, Object> addPlan(Map<String, Object> workPlan) {
        //需要确定计划模板在系统中的记录id
        //查询资产定义id
        AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_PLAN").orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));

        TableMetadata tableMetadata = resourceService.getInfoByResourceId(assetDefine.getResourceId());
        Long tableId = tableMetadata.getId();

        //生成排除字段
        Map<String, Object> result = MakeList.execute(8, "id", "createUser", "updateUser", "createTime", "updateUser", "updateTime", "deleted");
//        List<String> excludeFields = CastUtils.cast(result.get("list"));
        //判断必填字段是否都有填写
        Assert.isTrue(assetService.verifyObjField(assetDefine.getId(), workPlan, null), "存在未填写的必填字段");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //设置默认生成次数、状态及创建时间
        workPlan.put("createUser", user.getId());
        workPlan.put("updateUser", user.getId());
        String number = String.valueOf(workPlan.get("code"));
        //根据编码配置，查询最新记录计划编码，确定是否可用
        // FIXME
//        String newCode = numberRuleService.confirmNumber(assetDefine.getId(), number);
        String newCode = "ok";
        if (!"ok".equalsIgnoreCase(newCode)) {
            //看下这里怎么返回，要把新的编码返回给前端，前端再次提交
            HashMap<String, Object> response = new HashMap<>();

            response.put("newCode", newCode);
            response.put("msg", "编码被占用");
            response.put("code", 500);
            return response;
        }
        //设置编码
        workPlan.put("code", number);
        //map转为对象
        // FIXME
//        WorkPlan plan = BeanUtils.toBean(workPlan, WorkPlan.class, true, false);
        WorkPlan plan = BeanUtils.toBean(workPlan, WorkPlan.class,true,false);
        //保存计划数据
        workPlanRepository.save(plan);

        LambdaQueryWrapper<WorkPlan> workPlanWrapper = Wrappers.lambdaQuery(WorkPlan.class);
        workPlanWrapper.eq(WorkPlan::getCode, number);
        //获取作业计划ID
        WorkPlan insert = workPlanMapper.findOne(workPlanWrapper).orElseThrow(() -> new IllegalArgumentException("编码对应作业计划不存在，原因：作业计划保存失败"));
        //作为数据返回
        Map<String, Object> objectMap = BeanUtils.toMap(insert, false);
        //添加模板所有扩展属性assets
        List<Map<String, Object>> plans = new ArrayList<>();
        plans.add(workPlan);
        //添加作业计划所有扩展属性
        assetService.addAllObjAttr(assetDefine.getId(), plans);
        //判断是否不需要审批，一次性设置了指派人、协助人，协助人为多人，需要单独表存储，作业计划接收表
        //判断是否存在指派人数据
        Long assignedUserId = Long.parseLong(workPlan.get("assignedUserId").toString());
        Long assignedDeptId = Long.parseLong(workPlan.get("assignedDeptId").toString());

        //处理协助人
        ArrayList<WorkOrderAssistant> assistantList = new ArrayList<>();
        if (assignedDeptId != null && assignedUserId != null) {
            //获取协助人信息
            List<Map<String, Object>> assistants = CastUtils.cast(workPlan.get("assistants"));
            if (!ObjectUtils.isEmpty(assistants)) {
                //获取数据集合
                assistants.forEach(element -> {
                    //获取协助人id
                    Long assistantId = Long.parseLong(element.get("id").toString());
                    //获取协助人名字
                    String assistantName = element.get("username").toString();

                    WorkOrderAssistant assistant = new WorkOrderAssistant();
                    assistant.setAssistantId(assistantId);
                    assistant.setAssistantName(assistantName);
                    assistant.setObjId(insert.getId());
                    assistant.setTableId(tableId);
                    assistantList.add(assistant);
                });
            }
        }
        //添加到协助人关联表
        workOrderAssistantService.addAssistantByList(assistantList);

        return objectMap;
    }

    @Override
    public Map<String, Object> updatePlan(Map<String, Object> inWorkPlan) {

        //TODO:Mozr 基于角色用户权限模块未完善，搁置

        /*
         * TODO: jiawei - 结合用户权限以及计划状态判断是否处于可编辑状态：
         *  计划部门可编辑的状态：计划创建、停用
         *  执行部门可编辑的状态：待执行部门确认
         *   执行部门只能编辑指派人和协助人，通过对比数据库数据和要修改的数据进行对比实现
         */

        Assert.notNull(inWorkPlan.get("id"), "ID不能为空");
        long id = Long.parseLong(inWorkPlan.get("id").toString());
        WorkPlan workPlan = BeanUtils.toBean(inWorkPlan, WorkPlan.class,true,false);
        workPlan.setId(id);

        //判断数据是否存在
        workPlanMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("更新前，数据不存在,id:" + id));

        //查询资产定义id
        AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_PLAN").orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));

        //生成排除字段
        Map<String, Object> result = MakeList.execute(8, "id", "createUser", "updateUser", "createTime", "updateUser", "updateTime", "deleted");
        //判断必填字段是否都有填写
        Assert.isTrue(assetService.verifyObjField(assetDefine.getId(), inWorkPlan, null), "存在未填写的必填字段");

        TableMetadata tableMetadata = resourceService.getInfoByResourceId(assetDefine.getResourceId());
        Long tableId = tableMetadata.getId();

        //旧的协助人数据
        List<WorkOrderAssistant> assistants = workOrderAssistantService.queryAll(tableId, id);
        List<Long> ids = assistants.stream().map(WorkOrderAssistant::getId).collect(Collectors.toList());
        //删除旧的协助人
        if (!ObjectUtils.isEmpty(ids)) {
            workOrderAssistantService.deleteAllById(ids);
        }
        //判断是否存在指派人数据
        Long assignedUserId = Long.parseLong(inWorkPlan.get("assignedUserId").toString());
        Long assignedDeptId = Long.parseLong(inWorkPlan.get("assignedDeptId").toString());
        //处理协助人
        ArrayList<WorkOrderAssistant> assistantList = new ArrayList<>();
        //获取协助人信息
        List<Map<String, Object>> newAssistants = CastUtils.cast(inWorkPlan.get("assistants"));
        if (assignedDeptId != null && assignedUserId != null) {
            if (!ObjectUtils.isEmpty(newAssistants)) {
                //获取数据集合
                newAssistants.forEach(element -> {
                    //获取协助人id
                    Long assistantId = Long.parseLong(element.get("id").toString());
                    //获取协助人名字
                    String assistantName = element.get("username").toString();

                    WorkOrderAssistant assistant = new WorkOrderAssistant();
                    assistant.setAssistantId(assistantId);
                    assistant.setAssistantName(assistantName);
                    assistant.setObjId(workPlan.getId());
                    assistant.setTableId(tableId);

                    assistantList.add(assistant);
                });
            }
        }
        //添加到协助人关联表
        workOrderAssistantService.addAssistantByList(assistantList);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //设置修改人信息
        workPlan.setUpdateUserId(user.getId());

        //查询数据库中作业计划数据
        workPlanMapper.update(workPlan);

        //修改扩展属性
        List<Map<String, Object>> plans = new ArrayList<>();
        plans.add(inWorkPlan);
        //修改作业计划所有扩展属性
        assetService.updateAllObjAttr(assetDefine.getId(), plans);

        WorkPlan updated = workPlanMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("更新后，数据不存在,id:" + id));

        //生成返回数据
        Map<String, Object> objectMap = BeanUtils.toMap(updated, false);
        objectMap.put("assistant", newAssistants);

        return objectMap;
    }

    @Override
    public Integer deletePlan(List<Long> ids) {

        if (ObjectUtils.isEmpty(ids)) {
            return 0;
        }

        LambdaQueryWrapper<WorkPlan> workPlanWrapper = Wrappers.lambdaQuery(WorkPlan.class);
        workPlanWrapper.in(WorkPlan::getId, ids);

        //查询所有数据
        List<WorkPlan> workPlanList = workPlanMapper.findAll(workPlanWrapper);

        //查询资产定义id
        AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_PLAN").orElseThrow(() -> new IllegalArgumentException("未找到资产定义"));

        //查询表元数据
        Long resourceId = assetDefine.getResourceId();
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);
        Long tableId = tableMetadata.getId();

        for (WorkPlan workPlan : workPlanList) {

            Long id = workPlan.getId();

            //先查询协助人信息
            List<WorkOrderAssistant> assistants = workOrderAssistantService.queryAll(tableId, id);

            //如果存在协助人数据
            if (!ObjectUtils.isEmpty(assistants)) {
                List<Long> longList = assistants.stream().map(WorkOrderAssistant::getId).collect(Collectors.toList());

                //批量删除协助人
                workOrderAssistantService.deleteAllById(longList);
            }

        }

        //批量删除扩展属性
        assetService.deleteAllObjAttrByObjId(assetDefine.getId(), ids);

        //批量删除作业计划
        int delete = workPlanMapper.deleteInBatchIds(ids);

        return delete;
    }

    @Override
    public Page<Map<String, Object>> listPlan(Map<String, Object> template, Pageable pageable) {

        QueryWrapper<WorkPlan> workPlanQueryWrapper = new QueryWrapper<>();
        workPlanQueryWrapper.allEq(template);
        Page<WorkPlan> workPlanList = workPlanMapper.findAll(workPlanQueryWrapper, pageable);

        //查询资产ID
        AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_PLAN").orElseThrow(() -> new IllegalArgumentException("未查询到资产"));

        //查询表元数据
        Long resourceId = assetDefine.getResourceId();
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);
        Long tableId = tableMetadata.getId();

        //创建结果集
        List<Map<String, Object>> mapList = new ArrayList<>();

        //添加扩展属性
        workPlanList.forEach(workPlan -> {
            Long id = workPlan.getId();
            Map<String, Object> allObjAttrById = assetService.findAllObjAttrById(tableId, id);
            Map<String, Object> objectMap = BeanUtils.toMap(workPlan, true);
            objectMap.putAll(allObjAttrById);
            mapList.add(objectMap);
        });

        long total = workPlanList.getTotalElements();

        //构建分页结果
        PageImpl<Map<String, Object>> page = new PageImpl<>(mapList, pageable, total);

        return page;

    }

    @Override
    public Map<String, Object> findPlanById(Long id) {
        //查询作业计划
        LambdaQueryWrapper<WorkPlan> workPlanWrapper = Wrappers.lambdaQuery(WorkPlan.class);
        workPlanWrapper.eq(WorkPlan::getId, id);
        WorkPlan workPlan = workPlanMapper.findOne(workPlanWrapper).orElseThrow(() -> new IllegalArgumentException("指定作业计划不存在"));
        //查询资产定义id
        AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_PLAN").orElseThrow(() -> new IllegalArgumentException("指定作业计划不存在"));

        //查询表元数据
        Long resourceId = assetDefine.getResourceId();
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);
        Long tableId = tableMetadata.getId();

        //添加扩展属性
        Map<String, Object> allObjAttrById = assetService.findAllObjAttrById(tableId, id);
        Map<String, Object> objectMap = BeanUtils.toMap(workPlan, true);
        objectMap.putAll(allObjAttrById);
        //协助人数据
        List<WorkOrderAssistant> assistants = workOrderAssistantService.queryAll(tableId, id);
        objectMap.put("assistants", assistants);

        //查询已生成任务列表
        List<WorkTask> workTaskList = workTaskRepository.findAllByPlanId(id);
        BeanUtils.toMap(workTaskList, true);
        objectMap.put("workTaskList", workTaskList);
        // TODO 可操作按钮
        return objectMap;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  PC-作业任务
    //////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addTask(Map<String, Object> workTask) {
        AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_TASK").orElseThrow(() -> new IllegalArgumentException("指定作业计划不存在"));
        String oldNumber = String.valueOf(workTask.get("code"));
//        //根据编码配置，查询最新记录的作业任务编码，确定是否可用
//        String result2 = numberRuleService.confirmNumber(assetDefine.getId(), oldNumber);
//        //设置编码
//        Assert.isTrue(!"ok".equalsIgnoreCase(result2), "编码冲突");
        //获取作业实体
        WorkTask task = BeanUtils.toBean(workTask, WorkTask.class,true,false);
        task.setCode(oldNumber);
        task.setTaskStatus(1);
        //如果协助人存在数据
        workTaskMapper.insert(task);
        List<Map<String, Object>> tasks = new ArrayList<>();
        tasks.add(MapUtils.objectToMap(task));
        //添加作业任务所有扩展属性
        assetService.addAllObjAttr(assetDefine.getId(), tasks);
        //打卡点查询
        AssetDefine objAssetDefine = assetService.getAssetDef("TEST_QUERY_OBJECT").orElseThrow(() -> new IllegalArgumentException("作业任务对象"));
        CheckPoint checkPoint = checkPointService.findCheckPoints(objAssetDefine.getId(), task.getWorkObj());
        //设置打卡状态
        //添加作业任务打卡  work_order_task_clock  议人打开还是所有人打卡，只打一次，只需要添加打卡信息即可，谁打记录时谁，如果所有人需要打开，那么要把所有指派人添加进去，打卡时候只需要修改状态
        if (!ObjectUtils.isEmpty(checkPoint)) {
            WorkTaskClock taskClock = new WorkTaskClock();
            taskClock.setTaskId(task.getId());
            taskClock.setPoints(checkPoint.getCode());
            taskClock.setPlace(checkPoint.getPlace());
            taskClock.setClockStatus(1);
            taskClock.setAppId(checkPoint.getAppId());
            taskClock.setPlatformId(checkPoint.getPlatformId());
            workTaskClockRepository.save(taskClock);
        }
        //新增协助人
        List<Map<String, Object>> users = CastUtils.cast(workTask.get("users"));
        AssetDefine assetsDefine = assetService.getAssetDef("WORK_ORD_TASK").orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));
        Long resourceId = assetsDefine.getResourceId();
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);
        if (!ObjectUtils.isEmpty(users)) {
            List<WorkOrderAssistant> assistants = users.stream().map(item -> {
                User user = BeanUtils.toBean(workTask, User.class,true,false);
                WorkOrderAssistant assistant = WorkOrderAssistant
                        .builder()
                        .assistantName(user.getUsername())
                        .assistantId(user.getId())
                        .type(3)
                        .tableId(Long.parseLong(tableMetadata.getId().toString()))
                        .objId(task.getId())
                        .build();
                return assistant;
            }).collect(Collectors.toList());
            if (!ObjectUtils.isEmpty(assistants)) {
                workOrderAssistantService.addAssistantByList(assistants);
            }
        }
        return MapUtils.objectToMap(task);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateTask(Map<String, Object> workTask) {
        //TODO 判断作业任务状态是否能修改
        Integer status = (Integer) workTask.get("status");
        if (status.equals(5)) {
            throw new IllegalArgumentException("不可修改此计划");
        }
        //作业任务在资产记录id
        AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_TASK").orElseThrow(() -> new IllegalArgumentException("资产定义为空"));
        WorkTask entity = BeanUtils.toBean(workTask, WorkTask.class, true, false);
        //修改数据
        workTaskMapper.update(entity);
        //查询扩展字段
        List<Map<String, Object>> tasks = new ArrayList<>();
        tasks.add(workTask);
        //修改作业任务扩展属性
        assetService.updateAllObjAttr(assetDefine.getId(), tasks);

        //打卡点查询
        //对象打卡点查询
        AssetDefine objAssetDefine = assetService.getAssetDef("TEST_QUERY_OBJECT").orElseThrow(() -> new IllegalArgumentException("作业任务对象"));
        CheckPoint checkPoint = checkPointService.findCheckPoints(objAssetDefine.getId(), entity.getWorkObj());
        //删除旧的作业任务打卡
        Map<String, Object> map = new HashMap<>(8);
        map.put("task_id", entity.getId());
        workTaskClockMapper.deleteByMap(map);
        //设置打卡状态
        if (!ObjectUtils.isEmpty(checkPoint)) {
            WorkTaskClock taskClock = new WorkTaskClock();
            taskClock.setTaskId(entity.getId());
            taskClock.setPoints(checkPoint.getCode());
            taskClock.setPlace(checkPoint.getPlace());
            taskClock.setClockStatus(1);
            workTaskClockMapper.update(taskClock);
        }
        //修改协助人
        List<Map<String, Object>> users = CastUtils.cast(workTask.get("users"));
        TableMetadata tableMetadata = getTable("WORK_ORD_TASK");
        if (!ObjectUtils.isEmpty(users)) {
            List<WorkOrderAssistant> assistants = users.stream().map(item -> {
                User user = BeanUtils.toBean(workTask, User.class, true, false);
                WorkOrderAssistant assistant = WorkOrderAssistant
                        .builder()
                        .assistantName(user.getUsername())
                        .tableId(tableMetadata.getId())
                        .assistantId(user.getId())
                        .objId(entity.getId())
                        .build();
                return assistant;
            }).collect(Collectors.toList());
            workOrderAssistantService.updateAssistantByList(assistants);
        }
        return MapUtils.objectToMap(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteTask(List<Long> ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return 0;
        }
        //删除作业任务
        workTaskMapper.deleteInBatchIds(ids);
        //作业任务在资产记录id
        AssetDefine taskAsset = assetService.getAssetDef("WORK_ORD_TASK").orElseThrow(() -> new IllegalArgumentException("资产id为空"));
        //删除作业任务扩展属性
        assetService.deleteAllObjAttrByObjId(taskAsset.getId(), ids);
        //删除作业任务打卡
        List<WorkTaskClock> oldClocks = workTaskClockMapper.findAllByTaskIds(ids);
        List<Long> clockIds = oldClocks.stream().map(WorkTaskClock::getId).collect(Collectors.toList());
        if (!ObjectUtils.isEmpty(clockIds)) {
            workTaskClockMapper.deleteInBatchIds(clockIds);
        }
        //删除协助人
        TableMetadata tableMetadata = getTable("WORK_ORD_TASK");
        List<WorkOrderAssistant> workOrderAssistants = ids.stream().map(item -> {
            WorkOrderAssistant assistant = WorkOrderAssistant
                    .builder()
                    .tableId(tableMetadata.getId())
                    .objId(item)
                    .build();
            return assistant;
        }).collect(Collectors.toList());
        if (!ObjectUtils.isEmpty(workOrderAssistants)) {
            workOrderAssistantService.deleteAssistant(workOrderAssistants);
        }
        return ids.size();
    }


    @Override
    public Page<Map<String, Object>> listTask(Map<String, Object> template, Pageable pageable) {
        // TODO Auto-generated method stub
        //查询作业任务
        //查询资产定义
        AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_TASK").orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));
        Long resourceId = assetDefine.getResourceId();
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);
        //查询表元数据id
        Long tableId = tableMetadata.getId();
        List<Map<String, Object>> result = new ArrayList<>();
        //查询实际数据
        QueryWrapper<WorkTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(template);
        Page<WorkTask> workTaskPage = workTaskMapper.findAll(queryWrapper, pageable);
        long totalElements = workTaskPage.getTotalElements();
        for (WorkTask workTask : workTaskPage) {
            Long id = workTask.getId();
            //查询扩展属性
            Map<String, Object> allObjAttr = assetService.findAllObjAttrById(tableId, id);
            //对象转为map
            Map<String, Object> objectMap = BeanUtils.toMap(workTask, true);
            //添加扩展属性
            objectMap.putAll(allObjAttr);
            result.add(objectMap);
        }
        //重新构造分页结果
        Page<Map<String, Object>> page = new PageImpl<>(result, pageable, totalElements);

        return page;
    }


    @Override
    public Map<String, Object> findTaskById(Long id) {
        //查询作业任务
        //查询实际数据
        WorkTask workTask = workTaskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("作业任务不存在"));
        //查询作业任务扩展属性
        //查询资产定义
        AssetDefine assetsDefine = assetService.getAssetDef("WORK_ORD_TASK").orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));
        Long resourceId = assetsDefine.getResourceId();
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);
        List<FieldMetadata> fieldMetadatas = tableMetadata.getFieldMetadatas();
        Long tableId = tableMetadata.getId();
        //查询扩展属性
        Map<String, Object> allObjAttr = assetService.findAllObjAttrById(tableId, id);
        //对象转为map,作为返回结果
        Map<String, Object> objectMap = BeanUtils.toMap(workTask, true);
        //添加扩展属性
        objectMap.putAll(allObjAttr);
        //协助人数据
        List<WorkOrderAssistant> assistants = workOrderAssistantService.queryAll(tableId, id);
        objectMap.put("assistants", assistants);
        //查询关联问题或者工单
        LambdaQueryWrapper<WorkDefect> workDefectWrapper = Wrappers.lambdaQuery(WorkDefect.class);
        workDefectWrapper.eq(WorkDefect::getTaskId, workTask.getId());
        //关联问题
        List<WorkDefect> workDefectList = workDefectMapper.findAll(workDefectWrapper);
        objectMap.put("workDefectList", workDefectList);

        //关联工单
        LambdaQueryWrapper<WorkOrder> workOrderWrapper = Wrappers.lambdaQuery(WorkOrder.class);
        workOrderWrapper.eq(WorkOrder::getTaskCode, workTask.getCode());
        List<WorkOrder> workOrderList = workOrderMapper.findAll(workOrderWrapper);
        objectMap.put("workOrderList", workOrderList);

        //查询工作总结
        LambdaQueryWrapper<WorkTaskNote> workTaskNoteWrapper = Wrappers.lambdaQuery(WorkTaskNote.class);
        workTaskNoteWrapper.eq(WorkTaskNote::getType, "工作总结");
        workTaskNoteWrapper.eq(WorkTaskNote::getTaskId, workTask.getId());
        List<WorkTaskNote> workTaskNoteList = workTaskNoteMapper.findAll(workTaskNoteWrapper);
        objectMap.put("workTaskNoteList", workTaskNoteList);

        return objectMap;
    }

    /**
     * 催办
     *
     * @param workId 任务id
     * @return 是否修改成功
     */
    @Override
    public Boolean urgeOrder(Long workId) {
        WorkTask workTask = workTaskRepository
                .findById(workId)
                .orElseThrow(() -> new IllegalArgumentException("任务为空"));
        //修改状态为 催办状态 需要确定数据字典
        workTask.setTaskStatus(7);
        workTaskRepository.save(workTask);
        return true;
    }

    @Override
    public Page<WorkTaskClock> findTaskClock(WorkTaskClock filter, Pageable pageable) {
        return workTaskClockRepository.findAll(Example.of(filter), pageable, false);
    }

    @Override
    public WorkTaskClock taskClock(WorkTaskClock entity) {
        // 生成打卡记录
        // 发送已打卡通知
        // 添加打卡点信息到轨迹中,未开启位置收集时，可替代轨迹
        return null;
    }

    @Override
    public Page<Map<String, Object>> listDefect(Map<String, Object> filter, Pageable pageable) {
        // 通过sql查询  已经转换的工单及未转换为工单的上报问题查询后的合集
        //两个都有扩展字段，这里客户端展示字段如何进行定义？？
        //上报问题、工单字段不同，名字不同，可能再前端显示，在后端两个不同表中是不同字段，是否需要加一个配置页面，进行列表和详情字段的定义，以及对应两个表中是什么字段，在页面配置下，这里根据配置结果进行查询

        return null;
    }

    @Override
    public Map<String, Object> findDefectById(Integer type, Long id) {
        Map<String, Object> map = null;
        if (1 == type) {
            Map<String, Object> mapById = workDefectService.findMapById(id);
            //根据客户端显示需要，进行字段标准化处理
        } else {
            //   map = workOrderService.getOrderById(id);
            //根据客户端显示需要，进行字段标准化处理
        }
        return map;
    }

    /**
     * 根据资产定义编码获取字段元数据信息
     *
     * @param code 资产定义编码
     * @return List<FieldMetadata>
     * @author Juyss
     */
    @Override
    public List<FieldMetadataWithObject> getFieldInfoByAssetsCode(String code) {
        AssetDefine assetsDefine = assetService.getAssetDef(code).orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));
        Long resourceId = assetsDefine.getResourceId();
        TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);
        List<FieldMetadata> fieldMetadatas = tableMetadata.getFieldMetadatas();
        List<FieldMetadataWithObject> result = new ArrayList<>();

        //字符串会被做转义处理，所以这里需要转换一下
        for (FieldMetadata field : fieldMetadatas) {
            FieldMetadataWithObject metadata = new FieldMetadataWithObject();
            String uiType = field.getUiType();
            Map<String, Object> map = null;

            //如果字符串为空，不进行序列化
            if (StringUtils.isNotBlank(uiType)) {
                map = Serializers.json().deserialize(uiType, TypeReferences.STRING_OBJECT_MAP);
            }

            BeanUtils.copyProperties(field, metadata, true);
            metadata.setUiTypeObject(map);
            metadata.setUiType(null);
            result.add(metadata);
        }

        return result;
    }


    /**
     * TableMetadata redis中查找
     */
    @Override
    public TableMetadata getTable(String code) {
        if (ObjectUtils.isEmpty(redisTemplate.opsForValue().get(code))) {
            AssetDefine assetsDefine = assetService.getAssetDef(code).orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));
            Long resourceId = assetsDefine.getResourceId();
            TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);
            redisTemplate.opsForValue().set(code, Serializers.json().serializeAsString(tableMetadata), 60 * 10, TimeUnit.SECONDS);
            return tableMetadata;
        }
        return Serializers.json().deserialize(redisTemplate.opsForValue().get(code).toString(), TableMetadata.class);
    }


    @Override
    public Page<Map<String, Object>> userPageList(User user, Pageable pageable) {
        Page<User> users = service.pageList(user, pageable);
        List<Map<String,Object>> list = new ArrayList<>();
        long totalElements = users.getTotalElements();

        //查询每个users绑定的资产
        users.forEach(item->{
            Map<String,Object> map = new HashMap<>(2);
            LambdaQueryWrapper<LabelDef> wrapper = Wrappers.lambdaQuery(LabelDef.class);
            wrapper.eq(LabelDef::getType,"assets");
            wrapper.eq(LabelDef::getParam,item.getId());
            List<LabelDef> all = labelDefMapper.findAll(wrapper);
            List<Long> ids = all.stream().map(LabelDef::getId).collect(Collectors.toList());
            if(ObjectUtils.isEmpty(ids)){
                map.put("user",item);
                list.add(map);
                return;
            }
            LambdaQueryWrapper<Label> lambdaQueryWrapper = Wrappers.lambdaQuery(Label.class);
            lambdaQueryWrapper.in(Label::getLabelId,ids);
            List<Label> labels = labelMapper.findAll(lambdaQueryWrapper);
            List<Long> objIds = labels.stream().map(Label::getObjId).collect(Collectors.toList());
            if(ObjectUtils.isEmpty(objIds)){
                map.put("user",item);
                list.add(map);
                return;
            }
            LambdaQueryWrapper<AssetDefine> assetDefineLambdaQueryWrapper = Wrappers.lambdaQuery(AssetDefine.class);
            assetDefineLambdaQueryWrapper.in(AssetDefine::getId,objIds);
            List<AssetDefine> assetMapperAll = assetMapper.findAll(assetDefineLambdaQueryWrapper);

            map.put("user",item);
            map.put("asset",assetMapperAll);
            list.add(map);
        });
        return new  PageImpl<>(list, pageable, totalElements);
    }

}
