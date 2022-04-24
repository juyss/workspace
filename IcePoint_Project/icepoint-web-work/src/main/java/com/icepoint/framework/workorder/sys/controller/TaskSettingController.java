package com.icepoint.framework.workorder.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.workorder.sys.entity.TaskSetting;
import com.icepoint.framework.workorder.sys.service.TaskSettingService;

/**
 * 系统通知设置控制层
 *
 * @author admin
 * @version 1.0
 * @ClassName TaskSettingController
 * @description
 * @since 2021-07-20 14:28
 */
@RestController
@RequestMapping("taskSetting")
public class TaskSettingController {
    @Resource
    private TaskSettingService service;
    
    /**
     * 添加系统通知设置
     *
     * @param entity 系统通知设置 {@linkplain com.icepoint.framework.workorder.sys.entity.TaskSetting TaskSetting}
     * @return 返回新增后的系统通知设置 {@linkplain com.icepoint.framework.workorder.sys.entity.TaskSetting TaskSetting}
     */
    @PostMapping
    public Response<TaskSetting> add(@RequestBody TaskSetting entity) {
        return ResponseUtils.one(service.add(entity));
    }
    
    /**
     * 编辑系统通知设置
     *
     * @param entity 编辑的系统通知设置 {@linkplain com.icepoint.framework.workorder.sys.entity.NumberRule TaskSetting}
     * @return 返回编辑后的系统通知设置 {@linkplain com.icepoint.framework.workorder.sys.entity.NumberRule TaskSetting}
     */
    @PatchMapping
    public Response<TaskSetting> edit(@RequestBody TaskSetting entity) {
        return ResponseUtils.one(service.edit(entity));
    }

    /**
     * 删除系统通知设置
     *
     * @param ids 要删除的数据主键
     * @return 返回删除后的数据
     */
    @DeleteMapping
    public Response<Integer> delete(@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.one(service.delete(ids));
    }

    /**
     * 列表查询系统通知设置
     *
     * @param entity 查询实体 {@linkplain com.icepoint.framework.workorder.sys.entity.TaskSetting TaskSetting}
     * @param pageable 分页对象
     * @return 所有系统通知设置 {@linkplain com.icepoint.framework.workorder.sys.entity.TaskSetting TaskSetting}
     */
    @GetMapping
    public PageResponse<TaskSetting> list(TaskSetting entity, Pageable pageable) {
        return ResponseUtils.page(service.list(entity, pageable));
    }
}
