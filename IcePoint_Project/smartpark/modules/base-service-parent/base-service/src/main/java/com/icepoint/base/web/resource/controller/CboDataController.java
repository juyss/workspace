package com.icepoint.base.web.resource.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.github.tangyi.common.log.annotation.Log;
import com.icepoint.base.api.vo.CboData;
import com.icepoint.base.web.resource.service.simple.CboDataService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "招商统计数据接口")
@RequestMapping("cbo")
@RestController
@RequiredArgsConstructor
public class CboDataController {


    private final CboDataService cboDataService;

    @GetMapping("data")
    @Log("查询招商统计数据")
    public ResponseBean<CboData> data() {
        return ResponseBeanUtils.queryOne(cboDataService.getData());
    }

}
