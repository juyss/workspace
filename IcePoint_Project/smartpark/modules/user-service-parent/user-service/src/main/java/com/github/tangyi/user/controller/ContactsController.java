package com.github.tangyi.user.controller;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.common.security.annotations.AdminTenantTeacherAuthorization;
import com.github.tangyi.model.Contacts;
import com.github.tangyi.user.service.ContactsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author xh
 * @Description 通讯录 api
 * @Date 20:10 2020/11/12
 * @Param
 * @return
 **/

@AllArgsConstructor
@Api("通讯录管理")
@RestController
@RequestMapping("/v1/contacts")
public class ContactsController {

    private ContactsService contactsService;

    @GetMapping("/{type:\\w+}/list")
    @ApiOperation(value = "PM 获取通讯录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", defaultValue = "yuanqu", dataType = "String"),
            @ApiImplicitParam(name = "deptId", value = "部门id(传末级的id即可)", required = false, defaultValue = "-1", dataType = "Long"),
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
    })
    public ResponseBean list(@PathVariable String type,
                                   @RequestParam(value = "deptId", required = false, defaultValue = "-1") Long deptId,
                                   @RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                   @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                   @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                   @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                   BaseReq baseReq) {
        return new ResponseBean<>(contactsService.list(pageNum, pageSize, sort, order, type, deptId, baseReq));
    }

    @PostMapping
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "PM 创建或编辑通讯录", notes = "创建时id为null,编辑时id为long数值")
    @ApiImplicitParam(name = "contacts", value = "通讯录实体contacts", required = true, dataType = "Contacts")
    @Log("保存通讯录")
    public ResponseBean<Integer> save(@RequestBody @Valid Contacts contacts) {
        return new ResponseBean<>(contactsService.save(contacts));
    }

    @DeleteMapping("/{id}")
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "PM 删除通讯录", notes = "根据ID删除通讯录")
    @ApiImplicitParam(name = "id", value = "通讯录ID", required = true, paramType = "path")
    @Log("删除通讯录")
    public ResponseBean<Boolean> del(@PathVariable Long id) {
        return new ResponseBean<>(contactsService.del(id) > 0);
    }

    @GetMapping("/user/export/{type:\\w+}")
    @ApiOperation(value = "PM 导出通讯录", notes = "导出通讯录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "ids", value = "通讯录id,多个用英文逗号隔开", required = false, dataType = "String"),
            @ApiImplicitParam(name = "deptId", value = "部门id(传末级的id即可)", required = false, defaultValue = "-1", dataType = "Long"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String")
    })
    @Log("导出通讯录")
    //@AdminTenantTeacherAuthorization
    public void export(@PathVariable String type, @RequestParam(value = "deptId", required = false, defaultValue = "-1") Long deptId,
                       @RequestParam(value = "ids", required = false) String ids,
                       @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                       @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                       BaseReq baseReq,
                       HttpServletResponse response) {
        contactsService.export(type,deptId, response,sort,order,ids,baseReq);
    }
}
