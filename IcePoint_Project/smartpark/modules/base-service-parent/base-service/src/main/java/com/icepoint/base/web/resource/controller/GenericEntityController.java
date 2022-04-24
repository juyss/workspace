package com.icepoint.base.web.resource.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.github.tangyi.common.log.annotation.Log;
import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.api.vo.LastAndNest;
import com.icepoint.base.web.resource.component.query.QueryParameter;
import com.icepoint.base.web.resource.service.complex.upper.GenericEntityService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Jiawei Zhao
 */
@SuppressWarnings("rawtypes")
@Api(tags = "通用数据接口")
@RequestMapping("generic/entity")
@RestController
@RequiredArgsConstructor
public class GenericEntityController {

    private final GenericEntityService genericEntityService;

    @GetMapping("{key}/{id}")
    @Log("查询通用数据")
    public ResponseBean<GenericEntity> get(
            QueryParameter queryParameter,
            @PathVariable("key") String key,
            @PathVariable("id") Object id,
            @RequestParam("type") SerializeType type) {
        return ResponseBeanUtils.queryOne(genericEntityService.get(queryParameter, key, id, type));
    }

    @GetMapping("{key}/list")
    @Log("查询通用数据列表")
    public ResponseBean<List<GenericEntity>> list(
            QueryParameter queryParameter,
            @PathVariable("key") String key,
            @RequestParam("type") SerializeType type) {
        return ResponseBeanUtils.queryMany(genericEntityService.list(queryParameter, key, type));
    }

    @GetMapping("{key}/page")
    @Log("查询通用数据分页")
    public ResponseBean<Page<GenericEntity>> page(
            QueryParameter queryParameter,
            @PathVariable("key") String key,
            @RequestParam("type") SerializeType type,
            Pageable pageable) {
        return ResponseBeanUtils.queryPage(genericEntityService.page(queryParameter, key, type, pageable));
    }

    @PostMapping("{key}/add")
    @Log("新增通用数据")
    public ResponseBean<GenericEntity> add(
            @PathVariable("key") String key, @RequestBody Map<String, Object> entity) {
        return ResponseBeanUtils.addNewData(genericEntityService.add(key, entity));
    }

    @PostMapping("{key}/push")
    @Log("新增通用数据")
    public ResponseBean<Boolean> push(
            @PathVariable("key") String key, @RequestBody List<Map<String, Object>> entityList) {
        return ResponseBeanUtils.updateData(genericEntityService.push(key, entityList));
    }

    @PutMapping("{key}/update")
    @Log("更新通用数据")
    public ResponseBean<Boolean> update(
            @PathVariable("key") String key, @RequestBody Map<String, Object> entity) {
        return ResponseBeanUtils.updateData(genericEntityService.update(key, entity));
    }

    @DeleteMapping("{key}/delete")
    @Log("删除通用数据")
    public ResponseBean<Boolean> delete(
            @PathVariable("key") String key, @RequestParam("id") List<Object> id) {
        return ResponseBeanUtils.deleteData(genericEntityService.delete(key, id));
    }

    @GetMapping("{key}/download")
    @Log("下载通用数据")
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @PathVariable("key") String key, @RequestParam("docNo") List<String> docNoList) {
        genericEntityService.download(request, response, key, docNoList);
    }

    @PostMapping("{key}/excel")
    @Log("导出通用数据")
    public void excel(@PathVariable("key") String key, MultipartFile excel) {
        genericEntityService.excel(key, excel);
    }

    @GetMapping("{key}/geom")
    @Log("查询坐标")
    public ResponseBean<List<GenericEntity>> geom(
            QueryParameter queryParameter,
            @PathVariable("key") String key,
            @RequestParam("type") SerializeType type,
            @RequestParam("geomType") Integer geomType) {
        return ResponseBeanUtils.queryMany(genericEntityService.geom(queryParameter, key, type, geomType));
    }

    @GetMapping("{key}/lastAndNest")
    @Log("查询上一篇下一篇数据")
    public ResponseBean<LastAndNest> lastAndNest(
            QueryParameter queryParameter,
            @PathVariable("key") String key,
            @RequestParam("id") String id,
            @RequestParam("type") SerializeType type,
            Pageable pageable) {
        return ResponseBeanUtils.queryOne(genericEntityService.lastAndNest(id, queryParameter, key, type, pageable));
    }

//    @GetMapping("{key}/fldOfList")
//    public <P extends GenericProperty>
//    ResponseBean<GenericEntity<P>> fldOfList(
//            @PathVariable("key") String key,
//            @RequestParam("type") PropertySerializeType type) {
//        return ResponseBeanUtils.queryMany(genericEntityService.fldOfList(key, id, type));
//    }
//
//    @GetMapping("{key}/fldOfDetail")
//    public <P extends GenericProperty>
//    ResponseBean<GenericEntity<P>> fldOfDetail(
//            @PathVariable("key") String key,
//            @RequestParam("type") PropertySerializeType type) {
//        return ResponseBeanUtils.queryMany(genericEntityService.fldOfDetail(key, id, type));
//    }
}
