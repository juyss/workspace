package com.icepoint.framework.code.sysfunservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.code.sysfunservice.entity.SysFunService;
import com.icepoint.framework.code.xml.entity.Definitions;
import com.icepoint.framework.code.xml.pojo.FunctionFlow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (SysFunService)表服务接口
 *
 * @author ck
 * @since 2021-06-04 17:44:23
 */
public interface SysFunServiceService  {
    /**
     * 分页查询
     * @param queryWrapper 查询参数
     * @param pageable 分页参数
     * @return
     */
    Page<SysFunService> pageList(QueryWrapper<SysFunService> queryWrapper, Pageable pageable);

    /**
     * id查询单条数据
     * @param id 主键
     */
    SysFunService getById(Long id);

    /**
     * 新增实体
     * @param sysFunService 实体对象
     */
    Integer save(SysFunService sysFunService);

    /**
     * 根据id修改实体属性
     * @param sysFunService 实体对象
     */
    Integer updateById(SysFunService sysFunService);

    /**
     * 删除函数服务
     * @author Juyss
     * @param id 主键
     * @return Integer
     */
    Integer delete(Long id);

    /**
     * 获取processList.xml
     * @author Juyss
     * @return Definitions
     */
    Definitions getProcessList();

    /**
     * 保存函数流程图
     * @author Juyss
     * @param functionFlow 实体对象
     * @param id 主键
     * @return Boolean
     */
    Boolean putFunctionFlow(FunctionFlow functionFlow, Long id);

    /**
     * 读取函数流程
     * @author Juyss
     * @param id 数据主键
     * @return SysFunService
     */
    FunctionFlow getFunctionFlow(Long id);

    /**
     * 保存函数流程图
     * @author Juyss
     * @param json 函数流程图JSON文件
     * @param id 主键
     * @return Boolean
     */
    Boolean saveFunctionFlow(String json, Long id);

    /**
     * 读取函数流程图
     * @author Juyss
     * @param id 主键
     * @return String
     */
    Object readFunctionFlow(Long id);
}
