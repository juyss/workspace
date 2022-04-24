package com.github.tangyi.dataYear.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.dataYear.service.DateYearServiceImpl;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "通用接口只能直接删除,现在改为逻辑删除")
@RestController
@RequestMapping("/v1/dateyear")
public class DateYearController {

    @Autowired
    private DateYearServiceImpl dateYearService ;


    @DeleteMapping("delete")
    public ResponseBean<Boolean> deleteByid(@RequestParam("ids") String ids){
        String[] split = ids.split(",");
        Integer integer =0;
        ArrayList<Long> list = new ArrayList<>();
        for (String id : split) {
            list.add(Long.valueOf(id));
        }
        for (Long id : list) {
            integer= dateYearService.deleteByid(id);
        }
        return new ResponseBean<>(integer>0);
    }




}
