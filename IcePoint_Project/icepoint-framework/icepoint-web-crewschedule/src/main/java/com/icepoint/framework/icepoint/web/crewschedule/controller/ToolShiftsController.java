package com.icepoint.framework.icepoint.web.crewschedule.controller;

import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolShifts;
import com.icepoint.framework.icepoint.web.crewschedule.service.ToolShiftsService;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班次管理
 * @author Juyss
 * @version 1.0
 * @ClassName ToolShiftsController
 * @description
 * @since 2021-07-27 15:54
 */
@RestController
@RequestMapping("toolShift")
public class ToolShiftsController {

    @Autowired
    private ToolShiftsService service;

    /**
     * 增加班次
     * @author Juyss
     * @param toolShifts 数据
     * @return Response<Integer>
     */
    @PostMapping
    public Response<Integer> insert(ToolShifts toolShifts){
        return ResponseUtils.any(service.insert(toolShifts));
    }

    /**
     * 修改班次
     * @author Juyss
     * @param toolShifts 数据
     * @return Response<Integer>
     */
    @PutMapping
    public Response<Integer> update(ToolShifts toolShifts){
        return ResponseUtils.any(service.update(toolShifts));
    }

    /**
     * 删除班次
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
     * @param toolShifts 数据
     * @param pageable 分页数据
     * @return Response<Integer>
     */
    @GetMapping("page")
    public PageResponse<ToolShifts> findAll(ToolShifts toolShifts, Pageable pageable){
        return ResponseUtils.page(service.findAll(toolShifts,pageable));
    }

    /**
     * 详情查询
     * @author Juyss
     * @param id 主键
     * @return Response<Integer>
     */
    @GetMapping("{id}")
    public Response<ToolShifts> findOne(@PathVariable("id") Long id){
        return ResponseUtils.one(service.findOne(id));
    }
}
