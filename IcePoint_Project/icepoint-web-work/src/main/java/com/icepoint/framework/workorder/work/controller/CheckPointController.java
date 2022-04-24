package com.icepoint.framework.workorder.work.controller;

import com.icepoint.framework.web.core.response.CollectionResponse;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.system.entity.AssetDefine;
import com.icepoint.framework.workorder.work.entity.CheckPoint;
import com.icepoint.framework.workorder.work.service.CheckPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


/**
 * @author Administrator
 * 打卡点控制器
 */
@RestController
@RequestMapping("checkPoint")
@RequiredArgsConstructor
public class CheckPointController {
    private final CheckPointService service;

    /**
     * 列表查询所有的打卡点
     */
    @GetMapping("pageList")
    public PageResponse<CheckPoint> pageList(CheckPoint checkPoint, Pageable pageable) {
        return ResponseUtils.page(service.pageList(checkPoint, pageable));
    }

    /**
     * 修改打卡点
     */
    @PutMapping("updateCheckPoint")
    public Response<Boolean> updateCheckPoint(CheckPoint checkPoint) {
        return ResponseUtils.one(service.updateCheckPoint(checkPoint));
    }
    /**
     * 删除打卡点
     */
    @DeleteMapping("deleteCheckPoint/{id}")
    public Response<Boolean> deleteCheckPoint(@PathVariable("id") Long id){
        return ResponseUtils.one(service.deleteCheckPoint(id));
    }
    /**
     * 新增打卡点
     */
    @PostMapping("addCheckPoint")
    public Response<Boolean> addCheckPoint(CheckPoint checkPoint){
        return ResponseUtils.one(service.addCheckPoint(checkPoint));
    }
    /**
     * 查询未绑定打卡点的资产
     */
    @GetMapping("queryCheckPointByAsset")
    public CollectionResponse<AssetDefine> queryCheckPointByAsset(){
        return ResponseUtils.many(service.queryCheckPointByAsset());
    }

}
