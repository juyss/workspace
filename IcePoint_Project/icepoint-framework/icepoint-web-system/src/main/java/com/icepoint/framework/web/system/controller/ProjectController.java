package com.icepoint.framework.web.system.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseBuilder;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.system.entity.Project;
import com.icepoint.framework.web.system.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (SysProject)表控制层
 *
 * @author makejava
 * @since 2021-05-24 16:57:38
 */
@Api("project管理")
@RestController
@RequestMapping("sysProject")
public class ProjectController extends ApiController {

    @Resource
    private ProjectService projectService;


    /**
     * 分页查询所有数据
     *
     * @param project 查询实体
     * @return 所有数据
     */
    @ApiOperation("分页查询所有数据,可根据传入参数条件查询")
    @ApiImplicitParam(name = "sysProject", value = "条件封装实体", dataType = "SysProject")
    @GetMapping("page")
    public PageResponse<Project> selectAll(Project project, Pageable pageable) {
        Page<Project> page = null;
        //对象转map,属性名驼峰转下划线
        Map<String, Object> columnMap = MapUtils.objectToLineMap(project);

        //移除非表字段
        columnMap.remove("resource_list");

        page = projectService.findAll(columnMap, pageable);
        return ResponseUtils.page(page);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "Long")
    @GetMapping("{id}")
    public Response<Project> selectOne(@PathVariable Long id) {
        Project project = projectService.findOne(id);
        return ResponseUtils.one(project);
    }

    /**
     * 新增数据
     *
     * @param project 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ApiImplicitParam(name = "sysProject", value = "数据封装", required = true, dataType = "SysProject")
    @PostMapping("insert")
    public Response<Project> insert(@RequestBody Project project) throws Exception {
        boolean saved = false;
        try {
            saved = this.projectService.saveSysProject(project);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return ResponseBuilder.any(saved ? project : null)
                .code(saved ? "200" : "500")
                .message(saved ? "success" : "failed")
                .build();
    }

    /**
     * 修改数据
     *
     * @param project 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据", notes = "修改数据")
    @ApiImplicitParam(name = "sysProject", value = "数据封装", required = true, dataType = "SysProject")
    @PutMapping("update")
    public Response<Project> update(@RequestBody Project project) {
        boolean updated = this.projectService.updateProject(project);

        return ResponseBuilder.any(updated ? project : null)
                .code(updated ? "200" : "500")
                .message(updated ? "success" : "failed")
                .build();
    }

    /*
     * 批量删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
//    @ApiOperation(value = "批量删除数据", notes = "批量删除数据")
//    @ApiImplicitParam(name = "ids", value = "id字符串,中间用逗号分隔", required = true, dataType = "String")
//    @DeleteMapping("deleteAll")
//    public Response<String> deleteAll(@RequestParam("ids") String ids) {
//        ArrayList<Long> idList = new ArrayList<>();
//
//        String[] split = ids.split(",");
//        for (String id : split) {
//            idList.add(Long.valueOf(id));
//        }
//
//        boolean deleted = sysProjectService.removeByIds(idList);
//        return ResponseUtils.(deleted ? ids : null,
//                deleted ? 200 : 500,
//                deleted ? "success" : "failed");
//    }

    /**
     * 删除单条数据,返回
     *
     * @param id 主键
     * @return 删除结果
     */
    @ApiOperation(value = "删除单条数据", notes = "删除单条数据")
    @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    @DeleteMapping("deleteOne")
    public Response<Long> deleteOne(@RequestParam("id") Long id) {
        boolean deleted = projectService.deleteProject(id);
        return ResponseBuilder.any(deleted ? id : null)
                .code(deleted ? "200" : "500")
                .message(deleted ? "success" : "删除失败,原因:项目下包含有资源，不允许删除")
                .build();
    }
}
