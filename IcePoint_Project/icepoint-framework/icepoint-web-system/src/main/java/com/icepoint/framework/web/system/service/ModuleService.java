package com.icepoint.framework.web.system.service;

import com.icepoint.framework.web.system.entity.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-21 17:05
 */
public interface ModuleService {
    /**
     * 根据id查询
     * @param id 主键id
     * @return 数据对象
     */
    Module queryById(Long id);

    /**
     * 分页
     * @param projectId 表id
     * @param pageable 分页参数
     * @return 分页数据
     */
    Page<Module> pageList(Long projectId, Pageable pageable);

    /**
     * 新增
     * @param module 数据对象
     * @return 是否添加成功
     */
    boolean save(Module module);

    /**
     * 批量删除
     * @author Juyss
     * @param ids id数组
     * @return Integer
     */
    Integer delete(List<Long> ids);

    /**
     * 单个删除
     * @author Juyss
     * @param id 主键
     * @return Integer
     */
    Integer deleteOne(Long id);

    /**
     * 更新
     * @author Juyss
     * @return boolean
     */
    boolean update(Module module);
}
