package com.icepoint.framework.web.system.service;

import com.icepoint.framework.web.system.entity.TableService;
import com.icepoint.framework.web.system.entity.TableServiceProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * (SysTabService)表服务接口
 *
 * @author makejava
 * @since 2021-06-04 16:12:29
 */
public interface TableServiceService {

    /**
     * 创建工程
     *
     * @param entity 数据封装
     * @return 是否创建成功
     */
    boolean save(TableService entity);

    /**
     * 更新
     *
     * @param entity 对象封装
     * @return boolean
     * @author Juyss
     */
    boolean update(TableService entity);

    /**
     * 根据主键逻辑删除
     *
     * @param id 主键
     * @return boolean
     * @author Juyss
     */
    boolean delete(Long id);

    /**
     * 批量删除
     *
     * @param ids 主键集合
     * @return Integer
     * @author Juyss
     */
    Integer deleteAll(List<Long> ids);

    /**
     * 根据主键查询单条数据
     *
     * @param id 主键
     * @return SysProject
     * @author Juyss
     */
    TableService findOne(Long id);

    /**
     * 条件查询并分页
     *
     * @param columnMap 条件
     * @param pageable  分页参数
     * @return Page<SysProject>
     * @author Juyss
     */
    Page<TableService> findAll(Map<String, Object> columnMap, Pageable pageable);

    /**
     * 根据资源id查询表服务
     *
     * @author Juyss
     * @param resourceId 资源id
     * @return Page<TableService>
     */
    Page<TableService> findAllByResourceId(Long resourceId, Pageable pageable);

    /**
     * 通过数据表ID获取Process信息
     * @author Juyss
     * @return List<TableServiceProcess>
     */
    List<TableServiceProcess> getProcessByTableId(Long tableId);

}
