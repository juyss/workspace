package com.github.tangyi.exam.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.core.mybatis.service.CommonDaoService;
import com.github.tangyi.example.SysConfigExample;
import com.github.tangyi.model.ExamStudyTask;
import com.github.tangyi.model.SysConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xh
 * @Description 存在数据库的配置
 * @Date 10:28 2020/10/30
 * @Param
 * @return
 **/
@Slf4j
@AllArgsConstructor
@Api("系统配置")
@RequestMapping("/v1/sys_config")
@RestController
public class SysConfigController {
    @Autowired
    private CommonDaoService commonDaoService;

    @GetMapping("/{code}")
    @ApiOperation(value = "PM 根据code获取配置", notes = "根据code获取配置")
    @ApiImplicitParam(name = "code", value = "配置标识", required = true, dataType = "String", paramType = "path")
    public ResponseBean<ExamStudyTask> course(@PathVariable String  code) {
        SysConfigExample sysConfigExample = new SysConfigExample();
        sysConfigExample.and().andCodeEqualTo(code);
        SysConfig sysConfig = commonDaoService.selectOneByExample(sysConfigExample);
        return new ResponseBean(sysConfig);
    }
}
