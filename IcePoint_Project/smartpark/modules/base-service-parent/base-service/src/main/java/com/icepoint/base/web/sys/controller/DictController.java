package com.icepoint.base.web.sys.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.icepoint.base.api.domain.TreeEntity;
import com.icepoint.base.web.basic.controller.CrudController;
import com.icepoint.base.web.sys.entity.Dict;
import com.icepoint.base.web.sys.entity.DictListExDto;
import com.icepoint.base.web.sys.service.DictService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sys/dict")
public class DictController extends CrudController<DictService, Dict, Long> {

    @PostMapping("tree")
    public ResponseBean<List<TreeEntity<Dict>>> tree(@RequestBody DictListExDto param) {
        return ResponseBeanUtils.queryMany(service.tree(param));
    }
}
