package com.github.tangyi.user.controller;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.model.PageClickLog;
import com.github.tangyi.user.service.PageClickLogService;
import com.github.tangyi.user.utils.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 页面点击 直接写库，后期再优化
 */
@AllArgsConstructor
@Api("页面点击日志管理")
@RestController
@RequestMapping("/v1/page_click_log")
public class PageClickLogController {

    private PageClickLogService pageClickLogService;

    @GetMapping("/count_list")
    @ApiOperation(value = "PM 获取点击统计")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "type", value = "类型", defaultValue = "DAY", dataType = "String")
//    })
    public ResponseBean list(BaseReq baseReq) {
        return new ResponseBean<>(pageClickLogService.list(baseReq));
    }

    @GetMapping("/detail_list")
    @ApiOperation(value = "PM 按时间区间 获取明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
    })
    public ResponseBean detailList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                   @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                   @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                   @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                   BaseReq baseReq) {
        return new ResponseBean<>(pageClickLogService.detailList(pageNum, pageSize, sort, order, baseReq));
    }


    @PostMapping
    @ApiOperation(value = "创建点击记录", notes = "创建点击记录")
    @ApiImplicitParam(name = "pageClickLog", value = "点击实体contacts", required = true, dataType = "PageClickLog")
//    @Log("创建点击记录")
    public ResponseBean<Integer> save(@RequestBody @Valid PageClickLog pageClickLog, HttpServletRequest request) {
        pageClickLog.setClickIp(IpUtil.obtainIpFromHttpReq(request));
        return new ResponseBean<>(pageClickLogService.save(pageClickLog));
    }

}
