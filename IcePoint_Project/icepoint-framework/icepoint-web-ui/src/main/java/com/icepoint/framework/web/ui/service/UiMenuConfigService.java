package com.icepoint.framework.web.ui.service;

import com.icepoint.framework.web.ui.entity.UiMenuConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @author Administrator
 */
public interface UiMenuConfigService {
    /**
     * 获取菜单配置
     */
    UiMenuConfig get(Long appId, Long ownerId);

    Map<String, Object> allConfig(Long appId, Long ownerId, Integer termType);

    /**
     * 分页查询
     */
    Page<UiMenuConfig> page(UiMenuConfig menuConfig, Pageable pageable);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UiMenuConfig queryById(Long id);
}
