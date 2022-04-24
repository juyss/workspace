package com.icepoint.framework.code.sysfunction.service;

import com.icepoint.framework.code.response.dto.FunctionDTO;
import com.icepoint.framework.code.response.dto.JavaFunctionDTO;
import com.icepoint.framework.code.sysfunction.entity.SysFunction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * (SysFunction)表服务接口
 *
 * @author makejava
 * @since 2021-06-07 10:05:58
 */
public interface SysFunctionService {


    /**
     * 添加函数
     *
     * @param sysFunction 数据封装
     * @return 是否创建成功
     */
    boolean insert(SysFunction sysFunction);

    /**
     * 更新
     *
     * @param sysFunction 对象封装
     * @return boolean
     * @author Juyss
     */
    boolean update(SysFunction sysFunction);

    /**
     * 根据主键逻辑删除
     *
     * @param id 主键
     * @return boolean
     * @author Juyss
     */
    boolean delete(Long id);

    /**
     * 根据主键查询单条数据
     *
     * @param id 主键
     * @return SysFunction
     * @author Juyss
     */
    SysFunction findOne(Long id);

    /**
     * 条件查询并分页
     *
     * @param columnMap 条件
     * @param pageable  分页参数
     * @return Page<SysFunction>
     * @author Juyss
     */
    Page<SysFunction> findAll(Map<String, Object> columnMap, Pageable pageable);

    /**
     * 删除processList中的函数
     */
    boolean deleteProcessFunction(Long id);

    /**
     * 新增函数,来自工程
     */
    SysFunction fromProject(FunctionDTO functionDTO);

    /**
     * 新增函数来自用户自定义
     *
     * @author Juyss
     */
    SysFunction fromUserDefine(JavaFunctionDTO javaFunctionDTO);

    /**
     * cha查询函数
     */
    SysFunction getFunction(Long id);

    /**
     * 修改函数
     *
     * @param javaFunctionDTO@return
     */
    boolean updateFunction(JavaFunctionDTO javaFunctionDTO);


}
