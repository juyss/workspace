package com.icepoint.framework.web.system.controller;

import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.service.FieldMetadataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FieldMetadata)表控制层
 *
 * @author ck
 * @since 2021-05-19 14:26:08
 */
@RestController
@RequestMapping("sysField")
public class FieldMetadataController {
    /**
     * 服务对象
     */
    @Resource
    private FieldMetadataService service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select")
    public FieldMetadata selectOne(Long id) {
        return service.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param tabId    所属表的id
     * @param pageable 分页参数
     * @return 分页结果
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("page")
    public PageResponse<FieldMetadata> pageList(Long tabId, Pageable pageable) {
        Page<FieldMetadata> page = service.pageList(tabId, pageable);
        return ResponseUtils.page(page);
    }

    /**
     * 添加一条数据
     *
     * @param sysField 数据对象
     * @return Response<Boolean>
     * @author Juyss
     */
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("save")
    public Response<Boolean> save(@RequestBody FieldMetadata sysField) {
        return ResponseUtils.any(service.saveSysFiled(sysField));
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return Response<Integer>
     * @author Juyss
     */
    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping("delete")
    public Response<Integer> deleteByIds(@RequestParam("ids") List<Long> ids) {
        Integer deleted = service.deleteSysField(ids);
        return ResponseUtils.any(deleted);

    }

    /**
     * 修改
     *
     * @param sysField 数据对象
     * @return Response<Boolean>
     * @author Juyss
     */
    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("update")
    public Response<Boolean> updateSysTable(@RequestBody FieldMetadata sysField) {
        boolean update = service.updateSysField(sysField);
        return ResponseUtils.any(update);
    }

}
