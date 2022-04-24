package com.icepoint.framework.web.ui.controller;

import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.ui.entity.UiMenuConfig;
import com.icepoint.framework.web.ui.service.UiMenuConfigService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 菜单ui配置
 *
 * @author Administrator
 */
@RestController
@RequestMapping("uiMenuConfig")
public class UiMenuConfigController {
    @Resource
    private UiMenuConfigService service;

    /**
     * 获取用户UiMenuConfig配置
     */
    @GetMapping("get")
    public Response<UiMenuConfig> get(Long appId, Long ownerId) {
        return ResponseUtils.any(service.get(appId, ownerId));
    }

    /**
     * 用户所有配置
     */
    @GetMapping("allConfig")
    public Response<Map<String, Object>> allConfig(Long appId, Long ownerId, Integer termType) {
        return ResponseUtils.map(service.allConfig(appId, ownerId, termType));
    }

    /**
     * 分页
     *
     * @param menuConfig 条件实体
     * @param pageable   分页参数
     */
    @GetMapping("page")
    public PageResponse<UiMenuConfig> page(UiMenuConfig menuConfig, Pageable pageable) {
        return ResponseUtils.page(service.page(menuConfig, pageable));
    }

    /**
     * 根据id获取UiMenuConfig
     *
     * @param id UiMenuConfig id
     */
    @GetMapping("getById/{id}")
    public Response<UiMenuConfig> getById(@PathVariable("id") Long id) {
        return ResponseUtils.any(service.queryById(id));
    }


}
