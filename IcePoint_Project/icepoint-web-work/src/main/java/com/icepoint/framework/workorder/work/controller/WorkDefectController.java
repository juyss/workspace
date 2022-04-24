package com.icepoint.framework.workorder.work.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.workorder.work.service.WorkDefectService;
import com.icepoint.framework.workorder.work.entity.WorkDefect;
import com.icepoint.framework.web.core.support.EntityMetadata;

/**
 * @author admin
 */
@RestController
@RequestMapping("workDefect")
public class WorkDefectController {
    @Resource
    private WorkDefectService workDefectService;
    
    /**
     * 新增作业问题记录
     *
     * @param taskId 作业任务
     * @param workOrderId 工单
     * @param defectType 问题类型
     * @param defectObjAssetDefId 问题对象资产定义ID
     * @param defectObjId 问题对象
     * @param defectObjName 问题对象名
     * @param defectRootAssetDefId 关联根对象根节点资产定义ID
     * @param defectRootId 关联根对象
     * @param defectRootName 关联根对象名
     * @param regionalType 区域类型
     * @param region 区域信息
     * @param adcode 行政区划
     * @param longitude 经度
     * @param latitude 纬度
     * @param pileNumber 桩号
     * @param location 详细地址
     * @param content 描述
     * @param source 问题来源
     * @param defectStatus 问题状态
     * @param reportUserId 上报人id
     * @param reportTime 登记时间
     * @param note 备注
     * @return 返回新增后的数据
     */
    @PostMapping
    public Response<Map<String, Object>> add(@RequestBody Map<String, Object> entity) {
        return ResponseUtils.any(workDefectService.add(entity));
    }

    /**
     * 编辑作业问题记录
     * @param id 主键
     * @param taskId 作业任务
     * @param workOrderId 工单
     * @param defectType 问题类型
     * @param defectObjAssetDefId 问题对象资产定义ID
     * @param defectObjId 问题对象
     * @param defectObjName 问题对象名
     * @param defectRootAssetDefId 关联根对象根节点资产定义ID
     * @param defectRootId 关联根对象
     * @param defectRootName 关联根对象名
     * @param regionalType 区域类型
     * @param region 区域信息
     * @param adcode 行政区划
     * @param longitude 经度
     * @param latitude 纬度
     * @param pileNumber 桩号
     * @param location 详细地址
     * @param content 描述
     * @param source 问题来源
     * @param defectStatus 问题状态
     * @param reportUserId 上报人id
     * @param reportTime 登记时间
     * @param note 备注
     * @return 返回编辑后的数据
     */
    @PutMapping
    public Response<Map<String, Object>> edit(@RequestBody Map<String, Object> entity) {
        return ResponseUtils.any(workDefectService.edit(entity));
    }

    /**
     * 批量删除作业问题记录
     *
     * @param ids 作业问题记录ID集合 1,2,3
     * @return 删除记录数
     */
    @DeleteMapping
    public Response<Integer> delete(@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.any(workDefectService.delete(ids));
    }

	/**
	 * 分页查询作业问题记录
	 * @param filter 查询条件
	 * @param pageable 分页参数
	 * @return 返回分页查询列表
	 */
    @GetMapping
    public PageResponse<Map<String, Object>> list(Map<String, Object> filter, Pageable pageable) {
        return ResponseUtils.page(workDefectService.list(filter, pageable));
    }
    
    /**
     * 查询作业问题记录详情
     *
     * @param id 作业问题记录ID
     * @return 返回详情数据
     */
    @GetMapping("/{id}")
    public Response<Map<String, Object>> findById(@PathVariable("id") Long id) {
        return ResponseUtils.any(workDefectService.findMapById(id));
    }

    /**
     * 问题上报
     * @param taskId 作业任务
     * @param workOrderId 工单
     * @param defectType 问题类型
     * @param defectObjAssetDefId 问题对象资产定义ID
     * @param defectObjId 问题对象
     * @param defectObjName 问题对象名
     * @param defectRootAssetDefId 关联根对象根节点资产定义ID
     * @param defectRootId 关联根对象
     * @param defectRootName 关联根对象名
     * @param regionalType 区域类型
     * @param region 区域信息
     * @param adcode 行政区划
     * @param longitude 经度
     * @param latitude 纬度
     * @param pileNumber 桩号
     * @param location 详细地址
     * @param content 描述
     * @param source 问题来源
     * @param defectStatus 问题状态
     * @param reportUserId 上报人id
     * @param reportTime 登记时间
     * @param note 备注
     * @author Juyss
     * @return Response<Map<String,Object>>
     */
    @PostMapping("problemReport")
    public Response<Map<String, Object>> problemReport(@RequestBody Map<String, Object> entity) {
        return ResponseUtils.any(workDefectService.problemReport(entity));
    }
}
