package com.icepoint.framework.web.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.icepoint.framework.web.system.entity.Parameter;

/**
 * (Parameter)表服务接口
 *
 * @author ck
 * @since 2021-05-25 16:48:04
 */
public interface ParameterService {

	 /**
     * 新增系统参数
     *
     * @param entity 新增的系统参数
     * @return 返回新增后的数据
     */
	Parameter add(Parameter entity);

    /**
     * 编辑系统参数
     *
     * @param entity 编辑的数据
     * @return 返回编辑后的数据
     */
	Parameter edit(Parameter entity);

    /**
     * 批量删除系统参数记录
     * 
     * @param ids 系统参数ID
     * @return
     */
	Integer delete(List<Long> ids);

	/**
	 * 分页查询系统参数记录
	 * @param filter 查询条件
	 * @param pageable 分页参数
	 * @return 返回分页查询列表
	 */
    Page<Parameter> list(Parameter filter, Pageable pageable);
    
    /**
     * 查询系统参数记录详情
     * @param id 系统参数ID
     * @return 返回详情数据
     */
    Parameter findById(Long id);

    /**
     * 根据名字查询系统参数
     * 
     * @param type 系统参数类型
     * @param appId 应用编号
     * @param platformId 平台编号
     * @param ownerId 所有者编号
     * @param paramCode 参数编码
     * @param paramNameEn 参数名称
     * @return
     */
    Parameter findByName(Integer type,Long appId,Long platformId,Long ownerId,String paramCode,String paramNameEn);
    
    /**
     * 查询工程生成路径
     *
     * @return TParameter
     */
    Parameter queryProjectPath();

    /**
     * 查询模板
     */
    Parameter queryTemplate();

    /**
     * 查询函数分组文件路径
     *
     * @param id 用户id
     * @return 文件路径
     */
    String groupDescription(Long id);

    /**
     * 表服务id  查询工程路径
     *
     * @param tableServiceId tableService表id
     */
    String getProjectPath(Long tableServiceId);

    /**
     * 获取函数文件路径
     *
     * @return 函数文件路径
     */
    String getFunctionPath(Long tableServiceId);

}
