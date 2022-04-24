package com.icepoint.framework.code.bizfield.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icepoint.framework.code.bizfield.entity.SysBizField;
import com.icepoint.framework.code.bizfield.service.SysBizFieldService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (SysBizField)表控制层
 *
 * @author makejava
 * @since 2021-06-04 16:13:25
 */
@RestController
@RequestMapping("sysBizField")
public class SysBizFieldController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysBizFieldService sysBizFieldService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param sysBizField 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SysBizField> page, SysBizField sysBizField) {
        return success(this.sysBizFieldService.page(page, new QueryWrapper<>(sysBizField)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.sysBizFieldService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysBizField 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SysBizField sysBizField) {
        return success(this.sysBizFieldService.save(sysBizField));
    }

    /**
     * 修改数据
     *
     * @param sysBizField 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SysBizField sysBizField) {
        return success(this.sysBizFieldService.updateById(sysBizField));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.sysBizFieldService.removeByIds(idList));
    }
}
