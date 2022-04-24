package com.icepoint.base.web.info.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.icepoint.base.web.basic.controller.CrudController;
import com.icepoint.base.web.info.entity.Column;
import com.icepoint.base.web.info.service.ColumnService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@RequestMapping("info/column")
public class ColumnController extends CrudController<ColumnService, Column, Long> {

    @GetMapping("maxSort")
    public ResponseBean<Boolean> maxSort(@RequestBody Column param) {
        return ResponseBeanUtils.operate(service.maxSort(param) > 0);
    }
}
