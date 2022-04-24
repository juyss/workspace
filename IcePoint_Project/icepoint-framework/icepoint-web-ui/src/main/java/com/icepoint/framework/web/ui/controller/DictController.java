package com.icepoint.framework.web.ui.controller;

import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.core.entity.Dict;
import com.icepoint.framework.web.core.response.CollectionResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.core.service.DictService;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 数据字典
 */
@RestController
@RequestMapping("dict")
public class DictController {

    @Resource
    private DictService service;

    @GetMapping("tree")
    public CollectionResponse<TreeNode<Dict>> tree(Long appId, Long platformId, String category) {
        Assert.isTrue(!ObjectUtils.isEmpty(appId), "appid为空");
        Assert.isTrue(!ObjectUtils.isEmpty(platformId), "platformId为空");
        return ResponseUtils.many(service.tree(appId, platformId, category));
    }

    /**
     * 分页查询
     *
     * @param dict 条件   owenerId appId 必要
     */
    @GetMapping("page")
    public Response<Dict> page(Dict dict, Pageable pageable) {
        return ResponseUtils.page(service.page(dict, pageable));
    }

    /**
     * 保存
     */
    @PostMapping("save")
    public Response<Boolean> save(Dict dict) {
        return ResponseUtils.any(service.save(dict));
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{id}")
    public Response<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseUtils.any(service.delete(id));
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public Response<Boolean> update(@RequestBody Dict dict) {
        return ResponseUtils.any(service.update(dict));
    }
}
