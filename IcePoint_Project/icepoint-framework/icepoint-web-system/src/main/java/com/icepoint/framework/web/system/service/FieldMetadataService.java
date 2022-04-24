package com.icepoint.framework.web.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.icepoint.framework.web.core.support.StdEntityService;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * (FieldMetadata)表服务接口
 *
 * @author ck
 * @since 2021-05-19 14:26:07
 */
public interface FieldMetadataService extends StdEntityService<FieldMetadata, Long> {

    /**
     * 根据id查询
     *
     * @param id 主键id
     * @return 数据对象
     */
    FieldMetadata queryById(Long id);

    /**
     * 分页
     *
     * @param tabId    表id
     * @param pageable 分页参数
     * @return 分页数据
     */
    Page<FieldMetadata> pageList(Long tabId, Pageable pageable);

    /**
     * 新增
     *
     * @param sysField 数据对象
     * @return 是否添加成功
     */
    boolean saveSysFiled(FieldMetadata sysField);

    /**
     * 批量删除
     *
     * @param ids id数组
     * @return Integer
     * @author Juyss
     */
    Integer deleteSysField(List<Long> ids);

    /**
     * 更新
     *
     * @return boolean
     * @author Juyss
     */
    boolean updateSysField(FieldMetadata sysField);

    List<FieldMetadata> list(Wrapper<FieldMetadata> queryWrapper);

    /**
     * tableId 查询所有的字段
     */
    List<FieldMetadata> findByTableId(Long tableId);
}
