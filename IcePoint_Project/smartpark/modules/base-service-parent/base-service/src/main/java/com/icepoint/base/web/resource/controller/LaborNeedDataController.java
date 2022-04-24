package com.icepoint.base.web.resource.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.github.tangyi.common.log.annotation.Log;
import com.icepoint.base.api.vo.LaborNeedData;
import com.icepoint.base.web.resource.service.simple.LaborNeedDataService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Longfei Xiang
 */
@SuppressWarnings("rawtypes")
@Api(tags = "用工需求数据接口")
@RequestMapping("labor/data")
@RestController
@RequiredArgsConstructor
public class LaborNeedDataController {

    private final LaborNeedDataService dataService;

    @GetMapping("line")
    @Log("查询用工需求数据")
    public ResponseBean<LaborNeedData> getLineData() {
        return ResponseBeanUtils.queryOne(dataService.getLineData());
    }
}
