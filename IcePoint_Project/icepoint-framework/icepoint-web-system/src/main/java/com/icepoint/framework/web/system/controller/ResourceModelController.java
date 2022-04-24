package com.icepoint.framework.web.system.controller;

import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseBuilder;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceModel;
import com.icepoint.framework.web.system.service.ResourceModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通用资源数据控制器
 *
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/resource/model")
public class ResourceModelController {

    private final ResourceModelService service;

    @GetMapping("{id}")
    public Response<ResourceModel> getById(Lookup lookup, @PathVariable("id") Object id) {
        return ResponseUtils.one(service.findById(lookup, id).orElse(null));
    }

    @GetMapping
    public PageResponse<ResourceModel> get(Lookup lookup, Pageable pageable) {
        return ResponseUtils.page(service.findAll(lookup, pageable));
    }

    @PostMapping
    public Response<ResourceModel> add(ResourceModel model) {
        Assert.isTrue(model.getId() == null, "新增数据id不能有值");
        return ResponseUtils.one(service.save(model));
    }

    @RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH })
    public Response<ResourceModel> edit(ResourceModel model) {
        Assert.isTrue(model.getId() != null, "修改数据id不能为空");
        return ResponseUtils.one(service.save(model));
    }

    @DeleteMapping("{id}")
    public Response<Void> deleteById(Lookup lookup, @PathVariable("id") Object id) {
        service.deleteById(lookup, id);
        return ResponseBuilder.justOk();
    }

    @DeleteMapping("/deleteAll")
    public Response<Void> deleteAll(Lookup lookup, @RequestParam("id") List<Object> ids) {
        service.deleteAllByIds(lookup, ids);
        return ResponseBuilder.justOk();
    }
}
