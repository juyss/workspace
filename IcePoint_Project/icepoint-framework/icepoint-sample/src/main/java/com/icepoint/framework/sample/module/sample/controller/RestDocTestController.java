package com.icepoint.framework.sample.module.sample.controller;

import com.icepoint.framework.restdoc.annotation.MapParam;
import com.icepoint.framework.restdoc.annotation.Param;
import com.icepoint.framework.web.core.response.MapResponse;
import com.icepoint.framework.web.core.response.ResponseBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 测试文档控制器
 *
 * @author Jiawei Zhao
 */
@RequestMapping("/doc/test")
@RestController
public class RestDocTestController {

    @MapParam(params = {
            @Param(name = "id", type = String.class, description = "主键id"),
            @Param(name = "name", type = String.class, description = "名称"),
            @Param(name = "object", type = Map.class, description = "一个对象"),
            @Param(name = "--field", type = String.class, description = "对象的一个属性"),
    })
    @PostMapping
    public MapResponse<String, Object> postMap(@RequestBody Map<String, Object> map) {
        return ResponseBuilder.map(map).build();
    }
}
