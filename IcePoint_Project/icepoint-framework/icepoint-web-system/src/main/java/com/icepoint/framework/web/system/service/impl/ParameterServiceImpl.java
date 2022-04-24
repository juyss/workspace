package com.icepoint.framework.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.web.core.message.CodedMessageException;
import com.icepoint.framework.web.core.message.NullArgumentMessageException;
import com.icepoint.framework.web.system.dao.ModuleRepository;
import com.icepoint.framework.web.system.dao.ParameterMapper;
import com.icepoint.framework.web.system.dao.TableServiceMapper;
import com.icepoint.framework.web.system.entity.*;
import com.icepoint.framework.web.system.service.ProjectService;
import com.icepoint.framework.web.system.service.ParameterService;
import com.icepoint.framework.web.system.service.TableMetadataService;
import com.icepoint.framework.web.system.util.PathKey;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * 关于项目生成的路径
 *
 * @author ck
 * @since 2021-05-25 16:48:04
 */
@Service("ParameterService")
public class ParameterServiceImpl implements ParameterService {


    @Resource
    private ParameterMapper parameterMapper;

    @Resource
    private TableServiceMapper mapper;

    @Resource
    private TableMetadataService tableMetadataService;

    @Resource
    private ModuleRepository moduleRepository;

    @Resource
    private ProjectService projectService;

    @Override
	public Parameter add(Parameter entity) {
		if (parameterMapper.insert(entity) <= 0){
			throw new CodedMessageException("4001");
		}
		return entity;
	}

	@Override
	public Parameter edit(Parameter entity) {
		if (null == entity.getId() || entity.getId() <= 0){
			throw new NullArgumentMessageException("id");
		}
		if (parameterMapper.update(entity) <= 0){
			throw new CodedMessageException("4001");
		}
		return entity;
	}

	@Override
	public Integer delete(List<Long> ids) {
		Integer ret = parameterMapper.deleteInBatchIds(ids);
		if (ret <= 0){
			throw new CodedMessageException("4001");
		}
		return ret;
	}

	@Override
	public Page<Parameter> list(Parameter filter, Pageable pageable) {
		QueryWrapper<Parameter> queryWrapper = new QueryWrapper<>(filter);
		return parameterMapper.findAll(queryWrapper, pageable);
	}

	@Override
	public Parameter findById(Long id) {
		if (null == id || id <= 0){
			throw new NullArgumentMessageException("id");
		}
		return parameterMapper.findById(id).orElse(null);
	}
	
	@Override
	public Parameter findByName(Integer type, Long appId, Long platformId, Long ownerId, String paramCode,
			String paramNameEn) {
		QueryWrapper<Parameter> queryWrapper = new QueryWrapper<>();
		if (null == appId || appId <= 0){
			throw new IllegalArgumentException("APPID值非法");
		}
		if (1 == type){
			 queryWrapper.eq("app_id", appId);
		}
		else if(2 == type){
			if (null == platformId || platformId <= 0){
				throw new IllegalArgumentException("平台ID值非法");
			}
			queryWrapper.eq("platform_id", platformId);
		}
		else if(3 == type){
			if (null == platformId || platformId <= 0){
				throw new IllegalArgumentException("平台ID值非法");
			}
			if (null == ownerId || ownerId <= 0){
				throw new IllegalArgumentException("所有者ID值非法");
			}
			queryWrapper.eq("platform_id", platformId);
			queryWrapper.eq("owner_id", ownerId);
		}
		else{
			throw new IllegalArgumentException("系统参数类型值非法");
		}
		if (StringUtils.isEmpty(paramCode)){
			throw new IllegalArgumentException("参数编码值为空");
		}
		if (StringUtils.isEmpty(paramNameEn)){
			throw new IllegalArgumentException("参数编码英文名称为空");
		}
		queryWrapper.eq("param_code", paramCode);
		queryWrapper.eq("param_name_en", paramNameEn);
		Optional<Parameter> one = parameterMapper.findOne(queryWrapper);
		if (one.isPresent()) {
            Parameter parameter = one.get();
            if (ObjectUtils.isEmpty(parameter)) {
                if (ObjectUtils.isEmpty(parameter.getCval())) {
                    throw new IllegalArgumentException(String.format("请设置编码为'%s'名称为'%s'的值",paramCode,paramNameEn));
                }
                throw new IllegalArgumentException(String.format("请配置编码为'%s'名称为'%s'系统参数",paramCode,paramNameEn));
            }
            return parameter;
        }
		return null;
	}
	
    @Override
    public Parameter queryProjectPath() {
        QueryWrapper<Parameter> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("param_code", PathKey.PROJECT_SET);
        queryWrapper.eq("param_name_en", PathKey.PROJECT_DIR);
        Optional<Parameter> one = parameterMapper.findOne(queryWrapper);
        if (one.isPresent()) {
            Parameter parameter = one.get();
            if (ObjectUtils.isEmpty(parameter)) {
                if (ObjectUtils.isEmpty(parameter.getCval())) {
                    throw new IllegalArgumentException("请设置工程源路径");
                }
                throw new IllegalArgumentException("请设置工程源路径");
            }
            return parameter;
        }
        return null;
    }

    @Override
    public Parameter queryTemplate()  {
        QueryWrapper<Parameter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("param_code", PathKey.PROJECT_SET);
        queryWrapper.eq("param_name_en", PathKey.TEMPLATE_DIR);
        Optional<Parameter> one = parameterMapper.findOne(queryWrapper);
        if (one.isPresent()) {
            Parameter parameter = one.get();
            if (ObjectUtils.isEmpty(parameter)) {
                if (ObjectUtils.isEmpty(parameter.getCval())) {
                    throw new IllegalArgumentException("请设置工程源路径");
                }
                throw new IllegalArgumentException("请设置工程源路径");
            }
            return parameter;
        }
        return null;
    }

    @Override
    public String groupDescription(Long id) {
        Parameter parameter = queryProjectPath();
        String cval = parameter.getCval();
        //得到当前用户id TODO 写死 默认用户id 为1000
        StringBuilder stringBuilder = new StringBuilder(cval);
        stringBuilder.append(File.separator);
        //这里为用户id
        stringBuilder.append("1000");
        //分组函数文件名
        stringBuilder.append(File.separator);
        stringBuilder.append("functionList.xml");
        return stringBuilder.toString();
    }

    @Override
    public String getProjectPath(Long tableSericeId) {
        Parameter parameter = queryProjectPath();
        //获得系统设置地址
        String cval = parameter.getCval();
        //根据服务查询出对应的

        return null;
    }

    @Override
    public String getFunctionPath(Long tableServiceId) {
        if(ObjectUtils.isEmpty(tableServiceId)){
            throw new IllegalArgumentException("tableServiceId参数为空");
        }
        Parameter parameter = queryTemplate();
        Optional<TableService> TableService = mapper.findById(tableServiceId);
        TableService tableService = TableService.orElse(null);
        TableMetadata tableMetadata = tableMetadataService.findOne(tableService.getTableId());
        Module module = moduleRepository.getOne(tableMetadata.getModuleId(), false);
        Project project = projectService.findOne(module.getProjectId());
        //拼接路径
        StringBuilder path = new StringBuilder(parameter.getCval());
        return null;
    }

	
}
