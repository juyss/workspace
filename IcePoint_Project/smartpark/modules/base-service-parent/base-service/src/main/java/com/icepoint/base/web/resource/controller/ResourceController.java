package com.icepoint.base.web.resource.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.github.tangyi.common.log.annotation.Log;
import com.icepoint.base.api.domain.TreeEntity;
import com.icepoint.base.web.basic.controller.CrudController;
import com.icepoint.base.api.entity.Resource;
import com.icepoint.base.web.resource.service.complex.ResourceLayerService;
import com.icepoint.base.web.resource.service.simple.ResourceService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
@Api("资源相关接口")
@RestController
@RequestMapping("resource")
@RequiredArgsConstructor
public class ResourceController extends CrudController<ResourceService, Resource, Long> {

    private final ResourceLayerService resourceLayerService;

    /**
     * 资源树状列表
     */
    @GetMapping("tree")
    @Log("查询资源树状列表")
    public ResponseBean<List<TreeEntity<Resource>>> getResourceTree() {
        return ResponseBeanUtils.queryMany(resourceLayerService.getResourceTree());
    }

}
