package com.icepoint.framework.web.system.service;

import com.icepoint.framework.web.system.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * (SysProject)表服务接口
 *
 * @author makejava
 * @since 2021-05-24 16:57:37
 */
public interface ProjectService {
    /**
     * 创建工程
     * @param project 数据封装
     * @return 是否创建成功
     */
    boolean saveSysProject(Project project);

    /**
     * 更新
     * @author Juyss
     * @param project 对象封装
     * @return boolean
     */
    boolean updateProject(Project project);

    /**
     * 根据主键逻辑删除
     * @author Juyss
     * @param id 主键
     * @return boolean
     */
    boolean deleteProject(Long id);

    /**
     * 根据主键查询单条数据
     * @author Juyss
     * @param id 主键
     * @return SysProject
     */
    Project findOne(Long id);

    /**
     * 条件查询并分页
     * @author Juyss
     * @param columnMap 条件
     * @param pageable 分页参数
     * @return Page<SysProject>
     */
    Page<Project> findAll(Map<String, Object> columnMap, Pageable pageable);

}
