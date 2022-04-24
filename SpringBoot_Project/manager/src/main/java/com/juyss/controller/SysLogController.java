package com.juyss.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.juyss.common.aop.annotation.LogAnnotation;
import com.juyss.common.utils.DataResult;
import com.juyss.entity.SysLog;
import com.juyss.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: SysLogController
 * @Desc:
 * @package com.juyss.controller
 * @project manager
 * @date 2021/1/21 14:42
 */
@RequestMapping("/sys")
@Api(tags = "系统模块-系统操作日志管理")
@RestController
public class SysLogController {
    @Resource
    private LogService logService;

    @PostMapping("/logs")
    @ApiOperation(value = "分页查询系统操作日志接口")
    @LogAnnotation(title = "系统操作日志管理", action = "分页查询系统操作日志")
    @RequiresPermissions("sys:log:list")
    public DataResult pageInfo(@RequestBody SysLog vo) {
        Page page = new Page(vo.getPage(), vo.getLimit());
        LambdaQueryWrapper<SysLog> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(vo.getUsername())) {
            queryWrapper.like(SysLog::getUsername, vo.getUsername());
        }
        if (!StringUtils.isEmpty(vo.getOperation())) {
            queryWrapper.like(SysLog::getOperation, vo.getOperation());
        }
        if (!StringUtils.isEmpty(vo.getStartTime())) {
            queryWrapper.gt(SysLog::getCreateTime, vo.getStartTime());
        }
        if (!StringUtils.isEmpty(vo.getEndTime())) {
            queryWrapper.lt(SysLog::getCreateTime, vo.getEndTime());
        }
        queryWrapper.orderByDesc(SysLog::getCreateTime);
        return DataResult.success(logService.page(page, queryWrapper));
    }

    @DeleteMapping("/logs")
    @ApiOperation(value = "删除日志接口")
    @LogAnnotation(title = "系统操作日志管理", action = "删除系统操作日志")
    @RequiresPermissions("sys:log:deleted")
    public DataResult deleted(@RequestBody List<String> logIds) {
        logService.removeByIds(logIds);
        return DataResult.success();
    }
}