package com.icepoint.base.web.resource.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.github.tangyi.common.log.annotation.Log;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.web.resource.service.complex.ResourceMetadataService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiawei Zhao
 */
@Api(tags = "资源元数据相关服务")
@AllArgsConstructor
@RestController
@RequestMapping("resource/metadata")
public class ResourceMetadataController {

    private final ResourceMetadataService resourceMetadataService;

    /**
     * 获取元数据
     *
     * @param key
     * @param type
     * @return
     */
    @GetMapping("{key}")
    @Log("获取元数据")
    public ResponseBean<ResourceMetadata> fldOfDetail(
            @PathVariable("key") String key,
            @RequestParam("type") SerializeType type) {
        return ResponseBeanUtils.queryOne(resourceMetadataService.get(key));
    }

}
