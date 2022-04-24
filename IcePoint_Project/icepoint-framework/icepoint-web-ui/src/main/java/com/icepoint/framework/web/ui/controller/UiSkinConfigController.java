package com.icepoint.framework.web.ui.controller;

import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.ui.entity.UiSkinConfig;
import com.icepoint.framework.web.ui.service.UiSkinConfigService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (UiSkinConfig)表控制层
 * 皮肤配置
 *
 * @author makejava
 * @since 2021-06-18 16:31:57
 */
@RestController
@RequestMapping("uiSkinConfig")
public class UiSkinConfigController {
    /**
     * 服务对象
     */
    @Resource
    private UiSkinConfigService service;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("get")
    public Response<UiSkinConfig> get(Long appId, Long ownerId) {
        return ResponseUtils.any(this.service.get(appId, ownerId));
    }

    /**
     * 分页
     */
    @GetMapping("page")
    public PageResponse<UiSkinConfig> page(UiSkinConfig uiSkinConfig, Pageable pageable) {
        Map<String, Object> stringObjectMap = MapUtils.objectToLineMap(uiSkinConfig);
        return ResponseUtils.page(this.service.page(stringObjectMap, pageable));
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public Response<Boolean> update(@RequestBody UiSkinConfig uiSkinConfig) {
        return ResponseUtils.any(service.update(uiSkinConfig));
    }

    /**
     * 通过id删除
     */
    @DeleteMapping("delete/{id}")
    public Response<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseUtils.any(service.delete(id));
    }

    /**
     * 通过id获取
     */
    @GetMapping("getById/{id}")
    public Response<UiSkinConfig> getById(@PathVariable("id") Long id) {
        return ResponseUtils.any(service.queryById(id));
    }

}
