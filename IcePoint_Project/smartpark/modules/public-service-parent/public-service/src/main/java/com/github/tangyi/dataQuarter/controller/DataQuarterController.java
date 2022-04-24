package com.github.tangyi.dataQuarter.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.dataQuarter.service.DataQuarterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/dataQuarter")
//季度报表修改为逻辑删除  通用接口是直接删除
public class DataQuarterController {
    @Autowired
    private DataQuarterServiceImpl dataQuarterService;

    @DeleteMapping("delete")
    public ResponseBean<Boolean> delete(@RequestParam("ids") String ids){
        return new ResponseBean<>(dataQuarterService.delete(ids));
    }

}
