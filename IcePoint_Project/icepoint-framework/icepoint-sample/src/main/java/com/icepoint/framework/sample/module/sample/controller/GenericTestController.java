package com.icepoint.framework.sample.module.sample.controller;

import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceModel;
import com.icepoint.framework.web.system.resource.source.ResourceDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jiawei Zhao
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/generic")
//@RestController
public class GenericTestController {

    private final ResourceDataSource source;

    @GetMapping("/lookup")
    public Response<String> lookup(Lookup lookup) {
        return ResponseUtils.any("");
    }

    @GetMapping("/test")
    public PageResponse<ResourceModel> testForGet(Lookup lookup, Pageable pageable) {
        Page<ResourceModel> models = source.findAll(lookup, pageable);
        return ResponseUtils.page(models);
    }

    @PostMapping("/test")
    public Response<ResourceModel> testForPost(ResourceModel model) {
        return ResponseUtils.one(source.save(model));
    }


}
