package com.github.tangyi.exam.controller;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.common.security.annotations.AdminTenantTeacherAuthorization;
import com.github.tangyi.common.security.annotations.UserAuthorization;
import com.github.tangyi.exam.service.TrainCourseService;
import com.github.tangyi.model.TrainCourse;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
@Api("培训报名管理")
@RestController
@RequestMapping("/v1")
public class TrainCourseController extends BaseController {

    @Autowired
    private TrainCourseService trainCourseService;


    @PostMapping("/train_course/save")
    @ApiOperation(value = "PM 创建或编辑培训", notes = "创建时id参数为空或不传;编辑时需带上id参数")
    @ApiImplicitParam(name = "trainCourse", value = "培训模型对象", required = true, dataType = "TrainCourse")
    @Log("创建或编辑培训")
    //@AdminTenantTeacherAuthorization
    public ResponseBean save(@RequestBody TrainCourse trainCourse) {
        return new ResponseBean(trainCourseService.save(trainCourse));
    }


    @GetMapping("/train_course/list")
    @ApiOperation(value = "PM 培训列表", notes = "查询培训报名列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "Integer"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "Integer"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
    })
    public ResponseBean getList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                BaseReq baseReq) {
        return new ResponseBean(trainCourseService.getList(baseReq, pageNum, pageSize, sort, order));
    }

    @DeleteMapping("/train_course/{id:\\d+}")
    @ApiOperation(value = "PM 删除培训", notes = "删除")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
    @Log("删除培训")
    //@AdminTenantTeacherAuthorization
    public ResponseBean del(@PathVariable Long id) {
        return new ResponseBean(trainCourseService.del(id));
    }

    @PutMapping("/train_course/check/{id:\\d+}")
    @ApiOperation(value = "PM 审核", notes = "审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "状态（0:停用，1：启用）", defaultValue = "1", dataType = "Integer")
    })
    @Log("审核发布培训")
    //@AdminTenantTeacherAuthorization
    public ResponseBean check(@PathVariable Long id,@RequestParam Integer status) {
        return new ResponseBean(trainCourseService.check(id,status));
    }

    @GetMapping("/train_user_relation/list")
    @ApiOperation(value = "PM 培训报名人员列表", notes = "通过培训报名的ID,查询培训报名人员列表(这个api用postman测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "Integer"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "Integer"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "培训报名ID", required = true, dataType = "Long")
    })
    public ResponseBean getRelationList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                        @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                        @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                        @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                        @RequestParam Long id,
                                        BaseReq baseReq) {//这里用trainCourser接收查询参数
        return new ResponseBean(trainCourseService.getRelationList(baseReq,id, pageNum, pageSize, sort, order));
    }

    @GetMapping("/train_user_relation/export/{id:\\d+}")
    @ApiOperation(value = "PM 导出报名人员列表", notes = "导出报名人员列表")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
    @Log("导出报名人员列表")
    //@AdminTenantTeacherAuthorization
    public void export(@PathVariable Long id, HttpServletResponse response) {
        trainCourseService.export(id, response);
    }

    /***********************************************前台接口**************************************************************/


    @GetMapping("/train_course/apply_list")
    @ApiOperation(value = "FE 培训报名列表", notes = "前台接口：培训报名列表,返回报名中培训报名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "Integer"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "Integer"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String")
    })
    //@UserAuthorization
    public ResponseBean getApplyList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                     @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                     @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                     @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order) {
        return new ResponseBean(trainCourseService.getApplyList(pageNum, pageSize, sort, order));
    }

    @GetMapping("/train_course/sign_list")
    @ApiOperation(value = "FE 签到报名列表", notes = "前台接口：培训报名列表,返回报名中培训报名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "Integer"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "Integer"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String")
    })
    public ResponseBean getSignList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                    @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                    @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                    @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order
    ) {
        return new ResponseBean(trainCourseService.getSignList(pageNum, pageSize, sort, order));
    }

    @PutMapping("/train_course/apply")
    @ApiOperation(value = "FE 报名", notes = "前台接口：报名")
    @Log("报名")
    public ResponseBean apply(@ApiParam(value = "培训id", required = true, type = "Long") @RequestParam Long trainCourseId) {
        return new ResponseBean(trainCourseService.apply(trainCourseId));
    }

    @PutMapping("/train_course/sign")
    @ApiOperation(value = "FE 签到", notes = "前台接口：签到")
    @Log("签到")
    public ResponseBean sign(@ApiParam(value = "培训id", required = true, type = "Long") @RequestParam Long trainCourseId) {
        return new ResponseBean(trainCourseService.sign(trainCourseId));
    }
}
