package com.icepoint.framework.web.ui.controller;

import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.ui.entity.UiMenuTemplate;
import com.icepoint.framework.web.ui.service.UiMenuTemplateService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 菜单模板
 *
 * @author Administrator
 */
@RestController
@RequestMapping("uiMenuTemplate")
public class UiMenuTemplateController {

    @Resource
    private UiMenuTemplateService service;

    /**
     * 查询用户菜单模板
     */
    @GetMapping("get")
    public Response<UiMenuTemplate> get(Long appId, Long ownerId) {
        return ResponseUtils.any(service.get(appId, ownerId));
    }

    /**
     * 分页
     *
     * @param uiMenuTemplate 条件实体
     * @param pageable       分页参数
     */
    @GetMapping("page")
    public PageResponse<UiMenuTemplate> page(UiMenuTemplate uiMenuTemplate, Pageable pageable) {
        return ResponseUtils.page(service.page(uiMenuTemplate, pageable));
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{id}")
    public Response<Boolean> deleteUiMenu(@PathVariable("id") Long id) {
        return ResponseUtils.any(service.deleteUiMenuTemplate(id));
    }

    /**
     * 修改
     *
     * @param uiMenuTemplate 条件实体
     */
    @PutMapping("update")
    public Response<Boolean> update(@RequestBody UiMenuTemplate uiMenuTemplate) {
        return ResponseUtils.any(service.update(uiMenuTemplate));
    }

    /**
     * 根据id获取UiMenuTemplate
     */
    @GetMapping("getById/{id}")
    public Response<UiMenuTemplate> getById(@PathVariable("id") Long id) {
        return ResponseUtils.any(service.queryById(id));
    }

}
