package com.icepoint.base.web.resource.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.github.tangyi.common.log.annotation.Log;
import com.icepoint.base.api.entity.Layer;
import com.icepoint.base.web.resource.service.complex.ResourceLayerService;
import com.icepoint.base.web.resource.service.simple.LayerService;
import com.icepoint.base.web.sys.entity.BaseParam;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
@Api(tags = "企业管理-图层管理")
@AllArgsConstructor
@RestController
@RequestMapping("resource/layer")
public class ResourceLayerController {

    private final ResourceLayerService service;
    private final LayerService layerService;

    @GetMapping("list")
    @Log("查询企业管理-图层")
    public ResponseBean<List<Layer>> list(BaseParam entity) {
        return ResponseBeanUtils.queryMany(service.getLayerConfig(entity));
    }

    @PostMapping("update")
    @Log("更新企业管理-图层")
    public ResponseBean<Boolean> update(@RequestBody Layer entity) {
        layerService.update(entity);
        return ResponseBean.success(Boolean.TRUE);
    }

    @PostMapping("top/{resId}")
    @Log("置顶企业管理-图层")
    public ResponseBean<Boolean> top(@PathVariable("resId") Long resId) {
        return ResponseBeanUtils.operate(service.layerTop(resId) > 0);
    }

    @PostMapping("bottom/{resId}")
    @Log("置底企业管理-图层")
    public ResponseBean<Boolean> bottom(@PathVariable("resId") Long resId) {
        return ResponseBeanUtils.operate(service.layerBottom(resId) > 0);
    }

    @PostMapping("up/{resId}")
    @Log("上移企业管理-图层")
    public ResponseBean<Boolean> up(@PathVariable("resId") Long resId) {
        return ResponseBeanUtils.operate(service.layerUp(resId) > 0);
    }

    @PostMapping("down/{resId}")
    @Log("下移企业管理-图层")
    public ResponseBean<Boolean> down(@PathVariable("resId") Long resId) {
        return ResponseBeanUtils.operate(service.layerDown(resId) > 0);
    }
}
