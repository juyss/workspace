package com.icepoint.framework.web.system.controller;

import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseBuilder;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.system.entity.Module;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.service.ModuleService;
import com.icepoint.framework.web.system.service.TableMetadataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模块相关接口
 *
 * @author Juyss
 * @version 1.0
 * @since 2021-06-21 17:04
 */
@RestController
@RequestMapping("module")
public class ModuleController {

    /**
     * 服务对象
     */
    @Resource
    private ModuleService service;

    @Resource
    private TableMetadataService tableMetadataService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select")
    public Module selectOne(Long id) {
        return service.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param projectId 所属工程的id
     * @param pageable  分页参数
     * @return 分页结果
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("page")
    public PageResponse<Module> pageList(Long projectId, Pageable pageable) {
        Page<Module> page = service.pageList(projectId, pageable);
        return ResponseUtils.page(page);
    }

    /**
     * 添加一条数据
     *
     * @param module 数据对象
     * @return Response<Boolean>
     * @author Juyss
     */
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("save")
    public Response<Boolean> save(@RequestBody Module module) {
        return ResponseUtils.any(service.save(module));
    }

    /**
     * 批量删除(未做是否能删除判断)
     *
     * @param ids id集合
     * @return Response<Integer>
     * @author Juyss
     */
    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping("deleteAll")
    public Response<Integer> deleteAll(@RequestParam("ids") List<Long> ids) {
        Integer deleted = service.delete(ids);
        return ResponseUtils.any(deleted);
    }

    /**
     * 删除(做了是否能删除判断)
     *
     * @param id id集合
     * @return Response<Integer>
     * @author Juyss
     */
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("deleteOne")
    public Response<Module> deleteOne(@RequestParam("id") Long id) {
        Module module = service.queryById(id);

        List<TableMetadata> tableList = tableMetadataService.findByProjectId(module.getProjectId());

        Integer deleted = 0;
        if (!(tableList != null && tableList.size() != 0)) {
            deleted = service.deleteOne(id);
        }
        return ResponseBuilder.any(deleted == 1 ? module : null)
                .code(deleted == 1 ? "200" : "500")
                .message(deleted == 1 ? "success" : "failed")
                .build();
    }

    /**
     * 修改
     *
     * @param module 数据对象
     * @return Response<Boolean>
     * @author Juyss
     */
    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("update")
    public Response<Boolean> update(@RequestBody Module module) {
        boolean update = service.update(module);
        return ResponseUtils.any(update);
    }
}
