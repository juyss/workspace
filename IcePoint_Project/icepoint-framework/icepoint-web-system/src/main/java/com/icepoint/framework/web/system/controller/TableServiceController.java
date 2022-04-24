package com.icepoint.framework.web.system.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.entity.TableService;
import com.icepoint.framework.web.system.service.TableMetadataService;
import com.icepoint.framework.web.system.service.TableServiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (SysTabService)表控制层
 *
 * @author makejava
 * @since 2021-06-04 16:12:30
 */
@RestController
@RequestMapping("sysTabService")
public class TableServiceController extends ApiController {


    /**
     * 服务对象
     */
    @Resource
    private TableServiceService service;

    @Resource
    private TableMetadataService tableMetadataService;

    /**
     * 分页查询所有数据
     *
     * @param pageable      分页对象
     * @param tableService 查询实体
     * @return 所有数据
     */
    @GetMapping("page")
    public PageResponse<TableService> page(TableService tableService, Pageable pageable) {
        Page<TableService> page = null;
        //对象转map,属性名驼峰转下划线
        Map<String, Object> columnMap = MapUtils.objectToLineMap(tableService);

        page = service.findAll(columnMap, pageable);
        return ResponseUtils.page(page);
    }

    /**
     * 根据资源ID查询表服务
     * @author Juyss
     * @param resourceId 资源id
     * @param pageable 分页参数
     * @return PageResponse<TableService>
     */
    @GetMapping("pageByResourceId")
    public PageResponse<TableService> pageByResourceId(Long resourceId, Pageable pageable){
        Page<TableService> page = service.findAllByResourceId(resourceId, pageable);
        return ResponseUtils.page(page);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public TableService findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    /**
     * 新增数据,同时修改对应数据表的资源ID
     *
     * @param tableService 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    public Response<Boolean> insert(TableService tableService, Long resourceId) {
        TableMetadata tableMetadata = tableMetadataService.findOne(tableService.getTableId());
        tableMetadata.setResourceId(resourceId);
        tableMetadataService.update(tableMetadata);
        return ResponseUtils.any(service.save(tableService));
    }

    /**
     * 修改数据
     *
     * @param tableService 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public Response<Boolean> update(@RequestBody TableService tableService) {
        return ResponseUtils.any(service.update(tableService));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public Response<Integer> delete(@RequestParam("idList") List<Long> idList) {
        return ResponseUtils.any(service.deleteAll(idList));
    }

}
