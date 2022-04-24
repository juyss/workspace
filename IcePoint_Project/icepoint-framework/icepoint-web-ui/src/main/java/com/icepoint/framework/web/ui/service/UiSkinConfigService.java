package com.icepoint.framework.web.ui.service;


import com.icepoint.framework.web.ui.entity.UiSkinConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * (UiSkinConfig)表服务接口
 *
 * @author makejava
 * @since 2021-06-18 16:31:54
 */
public interface UiSkinConfigService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UiSkinConfig queryById(Long id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    UiSkinConfig get(Long appId, Long ownerId);

    /**
     * 修改数据
     *
     * @param uiSkinConfig 实例对象
     * @return 实例对象
     */
    Boolean update(UiSkinConfig uiSkinConfig);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询列表
     */
    Page<UiSkinConfig> page(Map<String, Object> map, Pageable pageable);

    /**
     * 删除数据
     */
    Boolean delete(Long id);
}
