package com.icepoint.framework.web.ui.service;


import com.icepoint.framework.web.ui.entity.UiMenuTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Administrator
 */
public interface UiMenuTemplateService {
    UiMenuTemplate get(Long appId, Long ownerId);

    /**
     * 分页查询
     */
    Page<UiMenuTemplate> page(UiMenuTemplate uiMenuTemplate, Pageable pageable);

    /**
     * 删除
     */
    Boolean deleteUiMenuTemplate(Long id);

    /**
     * 修改
     */
    Boolean update(UiMenuTemplate uiMenuTemplate);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UiMenuTemplate queryById(Long id);
}
