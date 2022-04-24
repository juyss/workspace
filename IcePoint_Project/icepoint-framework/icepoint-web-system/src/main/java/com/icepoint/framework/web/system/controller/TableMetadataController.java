package com.icepoint.framework.web.system.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.service.TableMetadataService;
import com.icepoint.framework.web.system.util.SystemConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TableMetadata)表控制层
 *
 * @author makejava
 * @since 2021-06-01 16:38:09
 */
@RestController
@RequestMapping("tableMetadata")
@RequiredArgsConstructor
public class    TableMetadataController extends ApiController {

    /**
     * 服务对象
     */
    @Resource
    private TableMetadataService tableMetadataService;

    /**
     * 分页查询所有数据,可以做条件查询
     *
     * @param TableMetadata     查询实体
     * @param pageable                 分页对象
     * @return 所有数据
     */
    @GetMapping("page")
    public PageResponse<TableMetadata> page(TableMetadata TableMetadata, Pageable pageable) {
        Page<TableMetadata> page = tableMetadataService.page(TableMetadata, pageable);
        return ResponseUtils.page(page);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Response<TableMetadata> findOne(@PathVariable Long id) {
        return ResponseUtils.one(tableMetadataService.findOne(id));
    }

    /**
     * 新增数据
     *
     * @return 新增结果
     */
    @PostMapping
    public Response<Boolean> insert(@RequestBody TableMetadata tableMetadata) {
        Boolean insert = tableMetadataService.insert(tableMetadata);
        return ResponseUtils.any(insert);
    }

    /**
     * 修改数据
     *
     * @param TableMetadata 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Response<Boolean> update(@RequestBody TableMetadata TableMetadata) {
        TableMetadata one = tableMetadataService.findOne(TableMetadata.getId());
        Boolean update = false;
        if (one.getTabType().equals(SystemConstants.GENERIC_TABLE_TYPE_TABLE)){
            update = tableMetadataService.update(TableMetadata);
        }
        return ResponseUtils.any(update);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Response<Integer> delete(@RequestParam("idList") List<Long> idList) {
        return ResponseUtils.any(tableMetadataService.delete(idList));
    }

    /**
     * 导入pdm
     */
    @PostMapping("uploadPdm")
    public Response<Boolean> uploadPdm(@RequestBody MultipartFile file)  {
        return ResponseUtils.any(tableMetadataService.upLoadPdm(file));
    }
}
