package com.icepoint.framework.web.ui.controller;

import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.ui.service.UiLessFileService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("less")
public class UiLessFileController {

    @Resource
    private UiLessFileService service;

    @DeleteMapping("delete/{id}")
    public Response<Boolean> delete(@PathVariable("id") Long id){
        return ResponseUtils.any(service.delete(id));
    }


}
