package com.icepoint.framework.workorder.sys.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.icepoint.framework.web.system.entity.AssetDefine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.data.domain.Example;

import com.icepoint.framework.core.function.MakeList;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.web.system.service.AssetService;
import com.icepoint.framework.workorder.sys.dao.TaskSettingRepository;
import com.icepoint.framework.workorder.sys.entity.TaskSetting;
import com.icepoint.framework.workorder.sys.service.TaskSettingService;

@Service
public class TaskSettingServiceImpl implements TaskSettingService {
	@Resource
    private TaskSettingRepository repository;
	@Resource
    private AssetService assetService;
	
	@Override
	public TaskSetting add(TaskSetting entity) {
		AssetDefine assetDefine = assetService.getAssetDef("").orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));
		//生成排除字段
        Map<String, Object> result = MakeList.execute(8, "id", "createUser", "updateUser", "createTime", "updateUser", "updateTime", "deleted");
        List<String> excludeFields = CastUtils.cast(result.get("list"));
        //判断必填字段是否都有填写
        Assert.isTrue(assetService.verifyObjField(assetDefine.getId(), MapUtils.objectToMap(entity), excludeFields), "存在未填写的必填字段");
        
		entity = repository.save(entity);
		return entity;
	}

	@Override
	public TaskSetting edit(TaskSetting entity) {
		AssetDefine assetDefine = assetService.getAssetDef("").orElseThrow(() -> new IllegalArgumentException("资产定义不存在"));
        //生成排除字段
        Map<String, Object> result = MakeList.execute(8, "createUser", "updateUser", "createTime", "updateUser", "updateTime", "deleted");
        List<String> excludeFields = CastUtils.cast(result.get("list"));
        //判断必填字段是否都有填写
        Assert.isTrue(assetService.verifyObjField(assetDefine.getId(), MapUtils.objectToMap(entity), excludeFields), "存在未填写的必填字段");
        
		entity = repository.save(entity);
		return entity;
	}

	@Override
	public Integer delete(List<Long> ids) {
		repository.deleteAllInId(ids);
		return ids.size();
	}

	@Override
	public Page<TaskSetting> list(TaskSetting entity, Pageable pageable) {
		return repository.findAll(Example.of(entity), pageable, false);
	}

}
