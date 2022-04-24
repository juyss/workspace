package com.icepoint.framework.workorder.sys.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.workorder.sys.entity.NumberRule;
import com.icepoint.framework.workorder.sys.service.NumberRuleService;


/**
 * 编号规则控制层
 *
 * @author admin
 * @version 1.0
 * @ClassName NumberRuleController
 * @description
 * @since 2021-07-13 12:02
 */
@RestController
@RequestMapping("numRule")
public class NumberRuleController {
    @Resource
    private NumberRuleService service;
    
    /**
     * 添加编码规则
     *
     * @param entity 编码规则{@linkplain com.icepoint.framework.workorder.sys.entity.NumberRule NumberRule}
     * @return 返回新增后的编码规则{@linkplain com.icepoint.framework.workorder.sys.entity.NumberRule NumberRule}
     */
    @PostMapping
    public Response<NumberRule> add(@RequestBody NumberRule entity) {
        return ResponseUtils.one(service.add(entity));
    }
    
    /**
     * 编辑编码规则
     *
     * @param entity 编辑的编码规则{@linkplain com.icepoint.framework.workorder.sys.entity.NumberRule NumberRule}
     * @return 返回编辑后的编码规则{@linkplain com.icepoint.framework.workorder.sys.entity.NumberRule NumberRule}
     */
    @PatchMapping
    public Response<NumberRule> edit(@RequestBody NumberRule entity) {
        return ResponseUtils.one(service.edit(entity));
    }

    /**
     * 删除编码规则
     *
     * @param ids 要删除的数据主键
     * @return 返回删除后的数据
     */
    @DeleteMapping
    public Response<Integer> delete(@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.one(service.delete(ids));
    }

    /**
     * 列表查询编码规则
     *
     * @param entity 查询实体
     * @param pageable 分页对象
     * @return 所有编码规则{@linkplain com.icepoint.framework.workorder.sys.entity.NumberRule NumberRule}
     */
    @GetMapping
    public PageResponse<NumberRule> list(Map<String, Object> entity, Pageable pageable) {
        return ResponseUtils.page(service.list(entity, pageable));
    }
    
    /**
     * 根据资产ID获取最新的编码
     * 
     * @param assertId 资产ID
     * @return 最新编码
     */
    @GetMapping("getNumber")
    public Response<String> getNumber(@RequestParam Long assertId) {
        return ResponseUtils.any(service.getNumber(assertId));
    }
}
