package com.icepoint.base.web.basic.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.icepoint.base.api.domain.BasicEntity;
import com.icepoint.base.web.basic.service.CrudService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class CrudController<S extends CrudService<E, ID>, E extends BasicEntity<ID>, ID extends Serializable>
        extends AutowiredController<S, E, ID> {

    @ApiOperation("获取单体对象")
    @GetMapping(value = "get/{id}")
    @ResponseBody
    public ResponseBean<E> get(@PathVariable(value = "id") ID id) {
        return ResponseBeanUtils.queryOne(service.get(id));
    }

    @ApiOperation("新增")
    @PostMapping("add")
    @ResponseBody
    public ResponseBean<ID> add(@RequestBody E entity) {
        return ResponseBeanUtils.addNewData(service.add(entity));
    }

    @ApiOperation("编辑")
    @PutMapping("update")
    @ResponseBody
    public ResponseBean<Boolean> update(@RequestBody E entity) {
        service.update(entity);
        return ResponseBean.success(Boolean.TRUE);
    }

    @ApiOperation("删除")
    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseBean<Boolean> delete(@PathVariable(value = "id") ID id) {
        service.delete(id);
        return ResponseBean.success(Boolean.TRUE);
    }

    @ApiOperation("查询列表")
    @GetMapping("list")
    @ResponseBody
    public ResponseBean<List<E>> list(E entity) {
        return ResponseBeanUtils.queryMany(service.list(Example.of(entity)));
    }

    @ApiOperation("分页查询列表")
    @GetMapping("page")
    @ResponseBody
    public ResponseBean<Page<E>> page(E entity, Pageable pageable) {
        return ResponseBeanUtils.queryPage(service.page(Example.of(entity), pageable));
    }

}
