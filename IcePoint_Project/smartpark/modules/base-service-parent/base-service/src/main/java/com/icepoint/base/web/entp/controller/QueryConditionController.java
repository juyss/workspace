package com.icepoint.base.web.entp.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.icepoint.base.api.entity.QueryCondition;
import com.icepoint.base.web.basic.controller.CrudController;
import com.icepoint.base.web.entp.service.QueryConditionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("rawtypes")
@Api(tags = "高级查询条件")
@RequestMapping("queryCondition")
@RestController
@RequiredArgsConstructor
public class QueryConditionController extends CrudController<QueryConditionService, QueryCondition, Long> {

    @PostMapping("addAll")
    public ResponseBean<List<QueryCondition>> addAll(@RequestBody List<QueryCondition> queryCondition) {
        return ResponseBeanUtils.addNewData(service.addAll(queryCondition));
    }

    @GetMapping("listName")
    public ResponseBean<List<String>> listName() {
        return ResponseBeanUtils.queryMany(service.listName());
    }

    @GetMapping("get")
    public ResponseBean<List<QueryCondition>> get(String name) {
        return ResponseBeanUtils.queryMany(service.getListByName(name));
    }
    
    @DeleteMapping("delete")
    public ResponseBean<Boolean> delete(@RequestParam("id") Long id){
        return ResponseBeanUtils.deleteData(service.deleted(id));
        
    }
    
}
