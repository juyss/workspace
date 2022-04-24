package com.github.tangyi.pub.controller;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.common.security.annotations.AdminAuthorization;
import com.github.tangyi.pub.service.InformationService;
import dto.TPushInformationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 日志controller
 *
 * @author tangyi
 * @date 2018/10/31 20:48
 */
@Slf4j
@AllArgsConstructor
@Api("公共服务-信息推送")
@RestController
@RequestMapping("/v1/information")
public class InformationController extends BaseController {

    private InformationService informationService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation(value = "获取信息推送记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = "title", value = "推送信息标题", dataType = "String"),
            @ApiImplicitParam(name = "plate", value = "信息推送类型", dataType = "int")
    })
    @Log("获取信息推送记录列表")
    public ResponseBean list(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                             @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                             @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                             @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                             @RequestParam(value = "title", required = false, defaultValue = "") String title,
                             @RequestParam(value = "plate", required = false, defaultValue = "") Long plate
            ){

        return new ResponseBean(informationService.list(pageNum, pageSize, sort, order, title,plate));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除信息推送记录", notes = "根据ID信息推送记录")
    @ApiImplicitParam(name = "id", value = "推送记录ID", required = true, paramType = "path")
    @Log("删除信息推送记录")
    public ResponseBean<Boolean> delete(@PathVariable Long id) {
        informationService.del(id);
        return new ResponseBean<Boolean>(true);
    }

    @PostMapping("deleteAll")
    @AdminAuthorization
    @ApiOperation(value = "批量删除记录", notes = "根据ids批量删除记录")
    @ApiImplicitParam(name = "ids", value = "记录ID", dataType = "Long")
    @Log("批量删除记录")
    public ResponseBean<Boolean> deleteAllLog(@RequestBody Long[] ids) {
        // TODO 缺少逻辑代码

        return new ResponseBean<Boolean>();
    }

    //TODO 暂未写
    @PostMapping(value = "/save")
    @ApiOperation(value = "添加或修改推送列表记录", notes = "id不为0时更新，否则是创建")
    @ApiImplicitParam(name = "tPushInformation", value = "信息列表实体类", required = true, dataType = "TPushInformation")
    @Log("添加或修改用户信息")
    public ResponseBean<Integer> save(@RequestBody TPushInformationDto tPushInformation) {
        try {
            return new ResponseBean<>(informationService.save(tPushInformation));
        } catch (Exception e) {
            log.error("Update TPushInformation failed", e);
            throw new CommonException("Update TPushInformation failed");
        }
    }


}
