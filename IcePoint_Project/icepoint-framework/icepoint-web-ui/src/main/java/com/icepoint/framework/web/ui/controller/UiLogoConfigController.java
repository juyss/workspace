package com.icepoint.framework.web.ui.controller;

import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.ui.entity.UiLogoConfig;
import com.icepoint.framework.web.ui.service.UiLogoConfigService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * logo
 */
@RestController
@RequestMapping("uiLogoConfig")
public class UiLogoConfigController {

    @Resource
    private UiLogoConfigService service;

    /**
     * 获取用户logo配置
     */
    @RequestMapping("get")
    public Response<List<UiLogoConfig>> get(Long appId, Long ownerId) {
        return ResponseUtils.any(service.get(appId, ownerId));
    }

    /**
     * 修改logo配置
     */
    @PutMapping("updateUiLogoConfig")
    public Response<Boolean> updateUiLogoConfig(@RequestBody UiLogoConfig uiLogoConfig) {
        return ResponseUtils.any(service.updateUiLogoConfig(uiLogoConfig));
    }

    /**
     * 根据id删除logo
     */
    @DeleteMapping("deleteUiLogoConfig/{id}")
    public Response<Boolean> updateUiLogoConfig(@PathVariable("id") Long id) {
        return ResponseUtils.any(service.deleteById(id));
    }

    /**
     * logo分页
     */
    @GetMapping("page")
    public PageResponse<UiLogoConfig> page(UiLogoConfig uiLogoConfig, Pageable pageable) {
        return ResponseUtils.page(service.page(uiLogoConfig, pageable));
    }

    /**
     * 通过id获取UiLogoConfig实体
     */
    @GetMapping("getById/{id}")
    public Response<UiLogoConfig> getById(@PathVariable("id") Long id) {
        return ResponseUtils.any(service.queryById(id));
    }

}
