package com.icepoint.framework.web.ui.service;

import com.icepoint.framework.web.ui.entity.UiLogoConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UiLogoConfigService {
    /**
     * 查询
     */
    List<UiLogoConfig> get(Long appId, Long ownerId);

    /**
     * 修改
     */
    Boolean updateUiLogoConfig(UiLogoConfig uiLogoConfig);

    /**
     * 删除
     */
    Boolean deleteById(Long id);

    /**
     * 查询列表
     */
    Page<UiLogoConfig> page(UiLogoConfig uiLogoConfig, Pageable pageable);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UiLogoConfig queryById(Long id);
}
