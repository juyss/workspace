package com.github.tangyi.exam.controller;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.common.security.annotations.AdminTenantTeacherAuthorization;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.exam.service.ExamCourseService;
import com.github.tangyi.model.ExamCourse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author xh
 * @Description 原来的课程管理代码因后来加了字段，不便维护，因此，使用tk.mybatis工具生成的model再做实现
 * @Date 8:13 2020/10/22
 * @Param
 * @return
 **/
@Slf4j
@AllArgsConstructor
@Api("NEW课程信息管理")
@RequestMapping("/v1/exam_course")
@RestController
public class ExamCourseController extends BaseController {

    private ExamCourseService examCourseService;

    @GetMapping("/{id}")
    @ApiOperation(value = "PM 获取课程信息", notes = "根据课程id获取课程详细信息")
    @ApiImplicitParam(name = "id", value = "课程ID", required = true, dataType = "Long", paramType = "path")
    public ResponseBean<ExamCourse> course(@PathVariable Long id) {
        return new ResponseBean<>(examCourseService.get(id));
    }


    @GetMapping("courseList")
    @ApiOperation(value = "PM 获取课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
    })
    public ResponseBean courseList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                   @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                   @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                   @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                   BaseReq baseReq) {
        return new ResponseBean<>(examCourseService.list(pageNum, pageSize, sort, order, baseReq));
    }


    @PostMapping
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "PM 创建或编辑课程", notes = "创建时id为null,编辑时id为long数值")
    @ApiImplicitParam(name = "course", value = "课程实体course", required = true, dataType = "ExamCourse")
    @Log("新增课程")
    public ResponseBean<Boolean> saveCourse(@RequestBody @Valid ExamCourse course) {
        course.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
        return new ResponseBean<>(examCourseService.save(course) > 0);
    }


    @DeleteMapping("{id}")
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "PM 删除课程", notes = "根据ID删除课程")
    @ApiImplicitParam(name = "id", value = "课程ID", required = true, paramType = "path")
    @Log("删除课程")
    public ResponseBean<Boolean> del(@PathVariable Long id) {
        return new ResponseBean<>(examCourseService.del(id) > 0);
    }


    @PostMapping("deleteAll")
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "PM 批量删除课程", notes = "根据课程id批量删除课程")
    @ApiImplicitParam(name = "ids", value = "课程ID", dataType = "Long")
    @Log("批量删除课程")
    public ResponseBean<Boolean> deleteAllCourses(@RequestBody Long[] ids) {
        boolean success = false;
        try {
            if (ArrayUtils.isNotEmpty(ids)) {
                for (int i = 0; i < ids.length; i++) {
                    if (ids[i] != null) this.del(ids[i]);
                }
            }
            success = true;
        } catch (Exception e) {
            log.error("Delete course failed", e);
        }
        return new ResponseBean<>(success);
    }

    @PostMapping("/check/{id:\\d+}")
    @ApiOperation(value = "PM 审核", notes = "审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "状态（0:停用，1：启用）", defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "field", value = "字段（'status':发布，'statusA': 审核）", defaultValue = "status", dataType = "String")
    })
    @Log("审核发布课程")
    //@AdminTenantTeacherAuthorization
    public ResponseBean check(@PathVariable Long id,@RequestParam Integer status,@RequestParam(defaultValue = "status") String field) {
        return new ResponseBean(examCourseService.check(id,status,field));
    }

    /*****************************前台接口*********************************************/
    @GetMapping("/course_list_for_fe")
    @ApiOperation(value = "FE 获取公开任务关联的课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
    })
    public ResponseBean courseListForFe(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                   @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                   @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                   @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                   BaseReq baseReq) {
        return new ResponseBean<>(examCourseService.courseListForFe(pageNum, pageSize, sort, order, baseReq));
    }
}
