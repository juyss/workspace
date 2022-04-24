package com.icepoint.framework.icepoint.web.crewschedule.controller;

import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolGroup;
import com.icepoint.framework.icepoint.web.crewschedule.service.ToolGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班次组管理
 * @author Juyss
 * @version 1.0
 * @ClassName ToolGroupController
 * @since 2021-07-28 11:03
 */
@RestController
@RequestMapping("toolGroup")
public class ToolGroupController {

    @Autowired
    private ToolGroupService service;

    /**
     * 增加班组
     * @author Juyss
     * @param toolGroup 数据
     * @return Response<Integer>
     */
    @PostMapping
    public Response<Integer> insert(ToolGroup toolGroup){
        return ResponseUtils.any(service.insert(toolGroup));
    }

    /**
     * 修改班组
     * @author Juyss
     * @param toolGroup 数据
     * @return Response<Integer>
     */
    @PutMapping
    public Response<Integer> update(ToolGroup toolGroup){
        return ResponseUtils.any(service.update(toolGroup));
    }

    /**
     * 删除班组
     * @author Juyss
     * @param ids 主键集合 多个id示例 1,2,3
     * @return Response<Integer>
     */
    @DeleteMapping
    public Response<Integer> delete(List<Long> ids){
        return ResponseUtils.any(service.delete(ids));
    }

    /**
     * 分页查询
     * @author Juyss
     * @param toolGroup 查询条件封装
     * @param pageable 分页数据
     * @return Response<Integer>
     */
    @GetMapping("page")
    public PageResponse<ToolGroup> findAll(ToolGroup toolGroup, Pageable pageable){
        return ResponseUtils.page(service.findAll(toolGroup,pageable));
    }

    /**
     * 详情查询
     * @author Juyss
     * @param id 主键
     * @return Response<Integer>
     */
    @GetMapping("{id}")
    public Response<ToolGroup> findOne(@PathVariable("id") Long id){
        return ResponseUtils.one(service.findOne(id));
    }
}
