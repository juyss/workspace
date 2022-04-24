package com.icepoint.framework.workorder.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.icepoint.framework.core.function.MakeList;
import com.icepoint.framework.core.util.BeanUtils;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.web.core.entity.Dict;
import com.icepoint.framework.web.core.service.DictService;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.system.entity.AssetDefine;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.service.AssetService;
import com.icepoint.framework.web.system.service.ResourceService;
import com.icepoint.framework.workorder.work.dao.WorkDefectMapper;
import com.icepoint.framework.workorder.work.dao.WorkDefectRepository;
import com.icepoint.framework.workorder.work.dao.WorkTaskMapper;
import com.icepoint.framework.workorder.work.dao.WorkTaskNoteMapper;
import com.icepoint.framework.workorder.work.entity.WorkDefect;
import com.icepoint.framework.workorder.work.entity.WorkPlanTemplate;
import com.icepoint.framework.workorder.work.entity.WorkTask;
import com.icepoint.framework.workorder.work.entity.WorkTaskNote;
import com.icepoint.framework.workorder.work.service.WorkDefectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WorkDefectServiceImpl  implements WorkDefectService {
    @Autowired
    private WorkDefectRepository workDefectRepository;

    @Autowired
	private WorkDefectMapper workDefectMapper;

    @Autowired
	private ResourceService resourceService;

    @Autowired
	private DictService dictService;
    
    @Autowired
    private AssetService assetService;

    @Autowired
	private WorkTaskMapper workTaskMapper;

    @Autowired
	private WorkTaskNoteMapper workTaskNoteMapper;

	@Override
	public Map<String,Object> add(Map<String,Object> entity) {
		//作业问题记录id
		AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_DEFECT").orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));

        Map<String,Object> exclude = MakeList.execute(11, "id","platformId","ownerId","appId","createUser","createTime","updateUser","updateTime","deleted");
//        List<String> excludeField = CastUtils.cast(exclude.get("list"));
        //判断字段
        Assert.isTrue(assetService.verifyObjField(assetDefine.getId(), entity, null),"存在必填字段未填写");
        
        //物理表添加
		WorkDefect workDefect = BeanUtils.toBean(entity, WorkDefect.class, true, false);

		workDefectRepository.save(workDefect);

		List<Map<String,Object>> list = new ArrayList<>();
        list.add(entity);
        //扩展属性添加
        assetService.addAllObjAttr(assetDefine.getId(), list);
        
        return entity;
	}

	@Override
	public Map<String,Object> edit(Map<String,Object> entity) {
		//作业问题记录id
		AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_DEFECT").orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));
        Map<String,Object> result1 = MakeList.execute(11, "platformId","ownerId","appId","createUser","createTime","updateUser","updateTime","deleted");
        List<String> exclude = CastUtils.cast(result1.get("list"));
        //判断字段
        Assert.isTrue(assetService.verifyObjField(assetDefine.getId(), entity, exclude),"存在非法字段");
        
        //物理表修改
		WorkDefect workDefect = BeanUtils.toBean(entity, WorkDefect.class, true, false);

		Assert.notNull(workDefect.getId(),"id不能为空");

        workDefectMapper.update(workDefect);
        
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(entity);
        //扩展属性添加
        assetService.updateAllObjAttr(assetDefine.getId(), list);

		Map<String, Object> objectMap = BeanUtils.toMap(workDefect, true);

		return objectMap;
	}

	@Override
	public Integer delete(List<Long> ids) {

		if (ObjectUtils.isEmpty(ids)){
			return 0;
		}

		AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_DEFECT").orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));

		//删除扩展属性
		assetService.deleteAllObjAttrByObjId(assetDefine.getId(), ids);

		return workDefectMapper.deleteInBatchIds(ids);
	}

	@Override
	public Page<Map<String,Object>> list(Map<String, Object> filter, Pageable pageable) {
		//查询资产定义
		AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_DEFECT").orElseThrow(() -> new IllegalArgumentException("未定义资产"));

		//查询表元数据
		Long resourceId = assetDefine.getResourceId();
		TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);
		Long tableId = tableMetadata.getId();

		//定义结果集
		ArrayList<Map<String, Object>> result = new ArrayList<>();

		//查表数据
		QueryWrapper<WorkDefect> wrapper = Wrappers.query(new WorkDefect());
		wrapper.allEq(filter);
		Page<WorkDefect> workDefectPage = workDefectMapper.findAll(wrapper, pageable);

		//添加扩展属性
		workDefectPage.get().forEach( workDefect -> {
			Long id = workDefect.getId();
			Long taskId = workDefect.getTaskId();
			WorkTask workTask = null;
			if (taskId != null) {
				workTask = workTaskMapper.findById(taskId).orElse(null);
			}
			Map<String, Object> allObjAttr = assetService.findAllObjAttrById(tableId, id);
			Map<String, Object> objectMap = BeanUtils.toMap(workDefect,true);
			objectMap.putAll(allObjAttr);
			objectMap.put("workTask", workTask);
			result.add(objectMap);
		});
		long total = workDefectPage.getTotalElements();

		//封装分页对象
		PageImpl<Map<String, Object>> page = new PageImpl<>(result, pageable, total);

		return page;
	}

	/**
	 * 作业问题查询
	 * @author Juyss
	 * @param id
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> findMapById(Long id) {
		//查询物理表数据
		WorkDefect workDefect = workDefectMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("作业问题不存在"));

		//查询作业任务
		WorkTask workTask = workTaskMapper.findById(workDefect.getTaskId()).orElse(null);
		List<WorkTaskNote> workTaskNoteList = null;

		//查询工作总结
		if (workTask != null && workTask.getId() != null) {
			LambdaQueryWrapper<WorkTaskNote> workTaskNoteWrapper = Wrappers.lambdaQuery(WorkTaskNote.class);
			workTaskNoteWrapper.eq(WorkTaskNote::getType, "工作总结");
			workTaskNoteWrapper.eq(WorkTaskNote::getTaskId, workTask.getId());
			workTaskNoteList = workTaskNoteMapper.findAll(workTaskNoteWrapper);
		}

		//查询资产定义
		AssetDefine assetDefine = assetService.getAssetDef("WORK_ORD_DEFECT").orElseThrow(() -> new IllegalArgumentException("资产未定义"));
		Long resourceId = assetDefine.getResourceId();
		TableMetadata tableMetadata = resourceService.getInfoByResourceId(resourceId);

		//查询表ID
		Long tableId = tableMetadata.getId();

		//查询扩展属性
		Map<String, Object> allObjAttr = assetService.findAllObjAttrById(tableId, id);

		//对象转map
		Map<String, Object> objectMap = BeanUtils.toMap(workDefect,true);

		//合并属性
		objectMap.putAll(allObjAttr);
		//添加作业任务数据
		objectMap.put("workTask", workTask);
		//添加工作总结数据
		objectMap.put("workTaskNoteList", workTaskNoteList);
		return objectMap;
	}

	/**
	 * 问题上报
	 *
	 * @param entity
	 * @return Map<String, Object>
	 * @author Juyss
	 */
	@Override
	public Map<String, Object> problemReport(Map<String, Object> entity) {
		//获取登录用户
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long appId = user.getAppId();
		Long platformId = user.getPlatformId();
		//查询字典值
		Dict dict = dictService.findByCategoryAndValue(appId, platformId, "IS_DOING", "5").orElseThrow(() -> new IllegalArgumentException("字典值不存在"));

		Integer workTaskStatus = Integer.valueOf(dict.getValue());
		Long userId = user.getId();

		LambdaQueryWrapper<WorkTask> workTaskWrapper = Wrappers.lambdaQuery(WorkTask.class);
		workTaskWrapper.eq(WorkTask::getAssignedUserId, userId);
		workTaskWrapper.eq(WorkTask::getTaskStatus, workTaskStatus);
		//获取正在进行中的作业任务
		WorkTask workTask = workTaskMapper.findOne(workTaskWrapper).orElseThrow(() -> new IllegalArgumentException("不存在正在进行中的作业任务"));

		entity.put("taskId",workTask.getId());

		//调用正常添加逻辑
		Map<String, Object> map = add(entity);

		return map;
	}

}
