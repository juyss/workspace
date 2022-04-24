package com.icepoint.framework.workorder.work.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.workorder.work.service.WorkOrderService;

/**
 * 工单管理
 *
 * @author admin
 * @version 1.0
 * @ClassName WorkOrderController
 * @since 2021-07-16 14:30
 */
@RestController
@RequestMapping("workOrder")
public class WorkOrderController {
    @Resource
    private WorkOrderService workOrderService;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  PC-工单
    //////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 新增工单
     *
     * @param code                           工单编号
     * @param type                           工单类型
     * @param defectType                     问题类型
     * @param level                          问题级别
     * @param scope                          影响范围
     * @param resource                       工单来源
     * @param workObjAssetDefId              作业对象资产定义
     * @param workObjId                      作业对象
     * @param workObjName                    作业对象名称
     * @param workRootAssetDefId             作业对象根节点资产定义
     * @param workRootId                     作业对象根节点
     * @param workRootName                   作业对象根节点名
     * @param regionalType                   区域类型
     * @param region                         区域信息
     * @param adcode                         行政区划
     * @param longitude                      经度
     * @param latitude                       纬度
     * @param location                       详细地址
     * @param flowNode                       当前流程环节
     * @param reportUserId                   上报人id
     * @param reportTime                     登记时间
     * @param content                        问题描述
     * @param lockStatus                     锁定状态
     * @param lockUserId                     锁定人id
     * @param lockedTime                     锁定时间
     * @param deptAssetDefId                 所属组织资产定义
     * @param deptId                         所属组织
     * @param deptName                       所属组织名
     * @param deptRootAssetDefId             所属组织根节点资产定义
     * @param deptRootId                     所属组织根节点
     * @param deptRootName                   所属组织根节点名
     * @param orderStatus                    工单状态
     * @param suspendedStatus                工单挂起状态
     * @param deadline                       工单处理截至时间
     * @param completeTime                   完工时间
     * @param completeContent                完结说明
     * @param completeUser                   完工人
     * @param declaredAmount                 申报金额
     * @param disposalNote                   处置方案
     * @param orderGrpCode                   出库单组
     * @param note                           备注
     * @param registerTime                   开始时间
     * @param ------其他扩展字段
     * @param --------------details子工单------
     * @param code                           子工单号
     * @param workObj                        作业对象，可选，如果填上就是做详细计划，不填那么就是简单管控，再作业内容中做说明
     * @param workObjName                    作业对象，该作业对象可以是作业任务中的对象，也可以是作业对象下面的具体对象，例如设备
     * @param workRootObj                    作业对象，可选，如果填上就是做详细计划，不填那么就是简单管控，再作业内容中做说明
     * @param workRootObjName                作业对象，可选，如果填上就是做详细计划，不填那么就是简单管控，再作业内容中做说明
     * @param regionalType                   区域类型
     * @param region                         区域信息
     * @param adcode                         可以通过位置自动预生成
     * @param longitude                      经度
     * @param latitude                       纬度
     * @param pileNo                         桩号
     * @param location                       详细地址
     * @param flowNode                       当前流程环节
     * @param lockStatus                     锁定状态 0：未锁定 1：已锁定
     * @param lockUserId                     锁定人id
     * @param lockedTime                     锁定时间
     * @param ------其他扩展字段
     * @return 新增的数据
     */
    @PostMapping("addOrder")
    public Response<Map<String, Object>> addOrder(@RequestBody Map<String, Object> template) {
        return ResponseUtils.any(workOrderService.addOrder(template));
    }

    /**
     * 根据id 批量删除工单
     *
     * @param ids 工单id集合
     * @return
     */
    @DeleteMapping("deleteOrders")
    public Response<Integer> deleteOrders(@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.any(workOrderService.deleteOrders(ids));
    }

    /**
     * 新增工单
     *
     * @param id                             id
     * @param code                           工单编号
     * @param type                           工单类型
     * @param defectType                     问题类型
     * @param level                          问题级别
     * @param scope                          影响范围
     * @param resource                       工单来源
     * @param workObjAssetDefId              作业对象资产定义
     * @param workObjId                      作业对象
     * @param workObjName                    作业对象名称
     * @param workRootAssetDefId             作业对象根节点资产定义
     * @param workRootId                     作业对象根节点
     * @param workRootName                   作业对象根节点名
     * @param regionalType                   区域类型
     * @param region                         区域信息
     * @param adcode                         行政区划
     * @param longitude                      经度
     * @param latitude                       纬度
     * @param location                       详细地址
     * @param flowNode                       当前流程环节
     * @param reportUserId                   上报人id
     * @param reportTime                     登记时间
     * @param content                        问题描述
     * @param lockStatus                     锁定状态
     * @param lockUserId                     锁定人id
     * @param lockedTime                     锁定时间
     * @param deptAssetDefId                 所属组织资产定义
     * @param deptId                         所属组织
     * @param deptName                       所属组织名
     * @param deptRootAssetDefId             所属组织根节点资产定义
     * @param deptRootId                     所属组织根节点
     * @param deptRootName                   所属组织根节点名
     * @param orderStatus                    工单状态
     * @param suspendedStatus                工单挂起状态
     * @param deadline                       工单处理截至时间
     * @param completeTime                   完工时间
     * @param completeContent                完结说明
     * @param completeUser                   完工人
     * @param declaredAmount                 申报金额
     * @param disposalNote                   处置方案
     * @param orderGrpCode                   出库单组
     * @param note                           备注
     * @param registerTime                   开始时间
     * @param ------其他扩展字段
     * @param --------------details子工单------
     * @param id                             id
     * @param code                           子工单号
     * @param workObj                        作业对象，可选，如果填上就是做详细计划，不填那么就是简单管控，再作业内容中做说明
     * @param workObjName                    作业对象，该作业对象可以是作业任务中的对象，也可以是作业对象下面的具体对象，例如设备
     * @param workRootObj                    作业对象，可选，如果填上就是做详细计划，不填那么就是简单管控，再作业内容中做说明
     * @param workRootObjName                作业对象，可选，如果填上就是做详细计划，不填那么就是简单管控，再作业内容中做说明
     * @param regionalType                   区域类型
     * @param region                         区域信息
     * @param adcode                         可以通过位置自动预生成
     * @param longitude                      经度
     * @param latitude                       纬度
     * @param pileNo                         桩号
     * @param location                       详细地址
     * @param flowNode                       当前流程环节
     * @param lockStatus                     锁定状态 0：未锁定 1：已锁定
     * @param lockUserId                     锁定人id
     * @param lockedTime                     锁定时间
     * @param ------其他扩展字段
     * @return 新增的数据
     */
    @PutMapping("updateOrder")
    public Response<Map<String, Object>> updateOrder(@RequestBody Map<String, Object> template) {
        return ResponseUtils.any(workOrderService.updateOrder(template));
    }

    /**
     * 条件实体  同上
     * 查询工单
     * @ id                             id
     * @return code                           工单编号
     * @return type                           工单类型
     * @return defectType                     问题类型
     * @return level                          问题级别
     * @return scope                          影响范围
     * @return resource                       工单来源
     * @return workObjAssetDefId              作业对象资产定义
     * @return workObjId                      作业对象
     * @return workObjName                    作业对象名称
     * @return workRootAssetDefId             作业对象根节点资产定义
     * @return workRootId                     作业对象根节点
     * @return workRootName                   作业对象根节点名
     * @return regionalType                   区域类型
     * @return region                         区域信息
     * @return adcode                         行政区划
     * @return longitude                      经度
     * @return latitude                       纬度
     * @return location                       详细地址
     * @return flowNode                       当前流程环节
     * @return reportUserId                   上报人id
     * @return reportTime                     登记时间
     * @return content                        问题描述
     * @return lockStatus                     锁定状态
     * @return lockUserId                     锁定人id
     * @return lockedTime                     锁定时间
     * @return deptAssetDefId                 所属组织资产定义
     * @return deptId                         所属组织
     * @return deptName                       所属组织名
     * @return deptRootAssetDefId             所属组织根节点资产定义
     * @return deptRootId                     所属组织根节点
     * @return deptRootName                   所属组织根节点名
     * @return orderStatus                    工单状态
     * @return suspendedStatus                工单挂起状态
     * @return deadline                       工单处理截至时间
     * @return completeTime                   完工时间
     * @return completeContent                完结说明
     * @return completeUser                   完工人
     * @return declaredAmount                 申报金额
     * @return disposalNote                   处置方案
     * @return orderGrpCode                   出库单组
     * @return note                           备注
     * @return registerTime                   开始时间
     * @return ------其他扩展字段
     * */
    @GetMapping("orderList")
    public PageResponse<Map<String, Object>> orderList(Map<String, Object> template, Pageable pageable) {
        return ResponseUtils.page(workOrderService.orderList(template, pageable));
    }

    /**
     * 条件实体  同上
     * 查询工单
     * @ id                             id
     * @return code                           工单编号
     * @return type                           工单类型
     * @return defectType                     问题类型
     * @return level                          问题级别
     * @return scope                          影响范围
     * @return resource                       工单来源
     * @return workObjAssetDefId              作业对象资产定义
     * @return workObjId                      作业对象
     * @return workObjName                    作业对象名称
     * @return workRootAssetDefId             作业对象根节点资产定义
     * @return workRootId                     作业对象根节点
     * @return workRootName                   作业对象根节点名
     * @return regionalType                   区域类型
     * @return region                         区域信息
     * @return adcode                         行政区划
     * @return longitude                      经度
     * @return latitude                       纬度
     * @return location                       详细地址
     * @return flowNode                       当前流程环节
     * @return reportUserId                   上报人id
     * @return reportTime                     登记时间
     * @return content                        问题描述
     * @return lockStatus                     锁定状态
     * @return lockUserId                     锁定人id
     * @return lockedTime                     锁定时间
     * @return deptAssetDefId                 所属组织资产定义
     * @return deptId                         所属组织
     * @return deptName                       所属组织名
     * @return deptRootAssetDefId             所属组织根节点资产定义
     * @return deptRootId                     所属组织根节点
     * @return deptRootName                   所属组织根节点名
     * @return orderStatus                    工单状态
     * @return suspendedStatus                工单挂起状态
     * @return deadline                       工单处理截至时间
     * @return completeTime                   完工时间
     * @return completeContent                完结说明
     * @return completeUser                   完工人
     * @return declaredAmount                 申报金额
     * @return disposalNote                   处置方案
     * @return orderGrpCode                   出库单组
     * @return note                           备注
     * @return registerTime                   开始时间
     * @return ------其他扩展字段
     * */
    @GetMapping("getOrderById")
    public Response<Map<String, Object>> getOrderById(@RequestParam Long id) {
        return ResponseUtils.any(workOrderService.getOrderById(id));
    }

    /**
     * 问题转工单
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
     * @return 工单实体
     */
    @PostMapping("defectToOrder")
    public Response<Map<String, Object>> defectToOrder(@RequestBody Map<String, Object> defect) {
        return ResponseUtils.any(workOrderService.defectToOrder(defect));
    }

}
