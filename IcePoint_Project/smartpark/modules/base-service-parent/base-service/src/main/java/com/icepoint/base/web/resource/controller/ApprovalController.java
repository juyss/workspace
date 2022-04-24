package com.icepoint.base.web.resource.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.github.tangyi.common.log.annotation.Log;
import com.icepoint.base.api.entity.Approval;
import com.icepoint.base.web.basic.controller.CrudController;
import com.icepoint.base.web.resource.service.simple.ApprovalService;
import com.icepoint.base.web.resource.service.complex.upper.GenericEntityService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@SuppressWarnings("rawtypes")
@Api(tags = "审批接口")
@RequestMapping("approval")
@RestController
@RequiredArgsConstructor
public class ApprovalController extends CrudController<ApprovalService, Approval, Long> {

    private final GenericEntityService genericEntityService;

    @PostMapping("{key}")
    @Log("执行审批")
    public ResponseBean<Boolean> approval(
            @PathVariable("key") String key, @RequestBody Map<String, Object> entity) {
        return ResponseBeanUtils.updateData(genericEntityService.approval(key, entity));
    }

    @Log("查询审批列表")
    @GetMapping("{key}")
    public ResponseBean<List<Approval>> list(
            @PathVariable("key") String key, Approval approval) {
        switch (key) {
            // 消息推送
            case "affairOpen":
                approval.setTabId(74L);
                break;
            // 招商政策
            case "cboPolicy":
                approval.setTabId(24L);
                break;
            // 招商项目
            case "cboItem":
                approval.setTabId(25L);
                break;
            // 招商动态
            case "cboDynamic":
                approval.setTabId(26L);
                break;
            default:
                return ResponseBeanUtils.queryMany(null);
        }
        return ResponseBeanUtils.queryMany(service.list(Example.of(approval)));
    }

}
