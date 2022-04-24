package com.github.tangyi.exam.controller;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.common.security.annotations.AdminTenantTeacherAuthorization;
import com.github.tangyi.common.security.annotations.UserAuthorization;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.exam.service.DeptService;
import com.github.tangyi.exam.service.ExamStudyTaskService;
import com.github.tangyi.model.ExamStudyTask;
import com.github.tangyi.user.api.module.Dept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author xh
 * @Description 原来的学习任务管理代码因后来加了字段，不便维护，因此，使用tk.mybatis工具生成的model再做实现
 * @Date 8:13 2020/10/22
 * @Param
 * @return
 **/
@Slf4j
@AllArgsConstructor
@Api("NEW学习任务管理")
@RequestMapping("/v1/exam_study_task")
@RestController
public class ExamStudyTaskController extends BaseController {

    private ExamStudyTaskService examStudyTaskService;

    private DeptService deptService;

    @GetMapping("/{id}")
    @ApiOperation(value = "PM 获取学习任务", notes = "根据学习任务id获取学习任务详细信息")
    @ApiImplicitParam(name = "id", value = "学习任务ID", required = true, dataType = "Long", paramType = "path")
    public ResponseBean<ExamStudyTask> course(@PathVariable Long id) {
        return new ResponseBean<>(examStudyTaskService.get(id));
    }


    @GetMapping("list")
    @ApiOperation(value = "PM 获取学习任务列表")
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
        return new ResponseBean<>(examStudyTaskService.list(pageNum, pageSize, sort, order, baseReq));
    }


    @PostMapping
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "PM 创建或编辑学习任务", notes = "创建时id为null,编辑时id为long数值")
    @ApiImplicitParam(name = "course", value = "学习任务实体course", required = true, dataType = "ExamStudyTask")
    @Log("新增学习任务")
    public ResponseBean<Boolean> saveCourse(@RequestBody @Valid ExamStudyTask course) {
        course.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
        return new ResponseBean<>(examStudyTaskService.save(course) > 0);
    }


    @DeleteMapping("{id}")
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "PM 删除学习任务", notes = "根据ID删除学习任务")
    @ApiImplicitParam(name = "id", value = "学习任务ID", required = true, paramType = "path")
    @Log("删除学习任务")
    public ResponseBean<Boolean> del(@PathVariable Long id) {
        return new ResponseBean<>(examStudyTaskService.del(id) > 0);
    }


    @DeleteMapping("delete_all")
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "PM 批量删除学习任务", notes = "根据学习任务id批量删除学习任务")
    @ApiImplicitParam(name = "ids", value = "学习任务ID,多个以逗号分隔", dataType = "String")
    @Log("批量删除学习任务")
    public ResponseBean<Boolean> deleteAllCourses(@RequestParam String ids) {
        boolean success = false;
        try {
            if (StringUtils.isNotEmpty(ids)) {
                String[] split = ids.split(",");
                for (int i = 0; i < split.length; i++) {
                    if (StringUtils.isNotEmpty(split[i])) this.del(Long.valueOf(split[i]));
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
    @Log("审核发布学习任务")
    //@AdminTenantTeacherAuthorization
    public ResponseBean check(@PathVariable Long id, @RequestParam Integer status,@RequestParam(defaultValue = "status") String field) {
        return new ResponseBean(examStudyTaskService.check(id, status,field));
    }

    @PutMapping("/join_course/{id:\\d+}")
    @ApiOperation(value = "PM 关联课程", notes = "通过id 关联课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "courseIds", value = "课程ids，多个id用英文逗号隔开", required = true, dataType = "String")
    })
    @Log("学习任务关联课程")
    //@AdminTenantTeacherAuthorization
    public ResponseBean joinCourse(@PathVariable Long id, @RequestParam String courseIds) {
        return new ResponseBean(examStudyTaskService.joinCourse(id, courseIds));
    }

    @PutMapping("/join_user/{id:\\d+}")
    @ApiOperation(value = "PM 关联学员", notes = "通过id 关联学员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "userIds", value = "学员用户ids，多个id用英文逗号隔开", required = true, dataType = "String")
    })
    @Log("学习任务关联学员")
    //@AdminTenantTeacherAuthorization
    public ResponseBean joinUser(@PathVariable Long id, @RequestParam String userIds) {
        return new ResponseBean(examStudyTaskService.joinUser(id, userIds));
    }


    @PutMapping("/cancel_join_course/{id:\\d+}")
    @ApiOperation(value = "PM 取消关联课程", notes = "通过id 取消关联课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "courseIds", value = "课程ids，多个id用英文逗号隔开", required = true, dataType = "String")
    })
    @Log("取消学习任务关联课程")
    //@AdminTenantTeacherAuthorization
    public ResponseBean cancelJoinCourse(@PathVariable Long id, @RequestParam String courseIds) {
        return new ResponseBean(examStudyTaskService.cancelJoinCourse(id, courseIds));
    }

    @PutMapping("/cancel_join_user/{id:\\d+}")
    @ApiOperation(value = "PM 取消关联学员", notes = "通过id 取消关联学员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "userIds", value = "学员用户ids，多个id用英文逗号隔开", required = true, dataType = "String")
    })
    @Log("取消学习任务关联学员")
    //@AdminTenantTeacherAuthorization
    public ResponseBean canceljoinUser(@PathVariable Long id, @RequestParam String userIds) {
        return new ResponseBean(examStudyTaskService.cancelJoinUser(id, userIds));
    }

    @GetMapping("/user/export/{id:\\d+}")
    @ApiOperation(value = "PM 导出学员列表", notes = "导出学员列表")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
    @Log("导出学员列表")
    //@AdminTenantTeacherAuthorization
    public void export(@PathVariable Long id, HttpServletResponse response) {
        examStudyTaskService.export(id, response);
    }

    @GetMapping("/course/list/{id:\\d+}")
    @ApiOperation(value = "PM 通过任务id获取课程列表", notes = "通过任务id获取课程列表，参数join=true表示查询已关联的，join=false表示查询未关联的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "join", value = "是否已关联", defaultValue = "true", dataType = "Boolean")
    })
    public ResponseBean getCourseList(@PathVariable Long id, @RequestParam(defaultValue = "true") Boolean join, BaseReq baseReq) {
        return new ResponseBean(examStudyTaskService.getCourseList(id, join, baseReq));
    }

    @GetMapping("/user/list/{id:\\d+}")
    @ApiOperation(value = "PM 通过任务id获取用户列表", notes = "通过任务id获取用户列表，参数join=true表示查询已关联的，join=false表示查询未关联的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "join", value = "是否已关联", defaultValue = "true", dataType = "Boolean"),
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String")
    })
    public ResponseBean<PageResult> getUserList(@PathVariable Long id, BaseReq baseReq, @RequestParam(defaultValue = "true") Boolean join,
                                    @RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                    @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                    @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                    @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order
    ) {
        PageResult result = examStudyTaskService.getUserList(pageNum, pageSize, sort, order, id, join, baseReq);
        List<Map<String, Object>> mapList = (List<Map<String, Object>>)result.getRows();


        //对于每一条数据
        for (Map<String, Object> map : mapList) {
            String deptName = (String) map.get("deptName");

            //如果deptName为空，证明此用户为统一认证用户，需要通过中间表进行查询部门信息
//            if (!StringUtils.isNotEmpty(deptName)) {
                Long userId = (Long) map.get("id"); //获取用户id
                List<Dept> list = deptService.getListByUserId(userId); //根据id获取部门集合

                //构建部门信息字符串，多个部门中间加空格
                StringBuilder builder;
                if (deptName!=null){
                    builder= new StringBuilder(deptName);
                }else {
                    builder= new StringBuilder();
                }
                for (Dept dept : list) {
                    String deptDeptName = dept.getDeptName();
                    builder.append("  "+deptDeptName);
                }
                String deptString = builder.toString().trim();

                //放入map集合
                map.put("deptName", deptString);
//            }
        }
        return new ResponseBean<>(result);
    }


    @GetMapping("/study_record/list")
    @ApiOperation(value = "PM 在线学习记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
    })
    public ResponseBean studyRecordList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                        @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                        @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                        @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                        BaseReq baseReq) {
        return new ResponseBean<>(examStudyTaskService.studyRecordList(pageNum, pageSize, sort, order, baseReq));
    }
    @GetMapping("/statistics/list")
    @ApiOperation(value = "PM 学习任务统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
    })
    public ResponseBean statistics(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                        @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                        @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                        @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                        BaseReq baseReq) {
        return new ResponseBean<>(examStudyTaskService.statistics(pageNum, pageSize, sort, order, baseReq));
    }

    @GetMapping("/user_login_statistics/list")
    @ApiOperation(value = "PM 用户登陆次数统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
    })
    public ResponseBean userLoginStatistics(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                   @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                   @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                   @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                   BaseReq baseReq) {
        return new ResponseBean<>(examStudyTaskService.userLoginStatistics(pageNum, pageSize, sort, order, baseReq));
    }
    @GetMapping("/access_time_statistics/list")
    @ApiOperation(value = "PM 访问时间段统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
    })
    public ResponseBean accessTimeStatistics(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                            @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                            @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                            @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                            BaseReq baseReq) {
        return new ResponseBean<>(examStudyTaskService.accessTimeStatistics(pageNum, pageSize, sort, order, baseReq));
    }
    @GetMapping("/access_num_statistics/list")
    @ApiOperation(value = "PM 访问量统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
    })
    public ResponseBean accessNumStatistics(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                            @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                            @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                            @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                            BaseReq baseReq) {
        return new ResponseBean<>(examStudyTaskService.accessNumStatistics(pageNum, pageSize, sort, order, baseReq));
    }
    /**********************************以下是前台接口********************************/
    @GetMapping("/my_task_list")
    @ApiOperation(value = "FE 我的学习任务列表", notes = "我的学习任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "查询类型：all 所有/current 当前", defaultValue = "all", dataType = "String")
    })
    //@UserAuthorization
    public ResponseBean myTaskList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                   @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                   @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                   @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                   @RequestParam(value = "type", required = false, defaultValue = "all") String type
    ) {
        return new ResponseBean(examStudyTaskService.myTaskList(pageNum, pageSize, sort, order, type));
    }

    @GetMapping("/my_task_course_list/{id:\\d+}")
    @ApiOperation(value = "FE 我的课程列表", notes = "通过我的学习任务id,获取我的课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
    })
    //@UserAuthorization
    public ResponseBean myTaskCourses(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                      @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = "100") Integer pageSize,
                                      @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                      @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                      @PathVariable("id") Long taskId) {
        return new ResponseBean(examStudyTaskService.myTaskCourses(pageNum, pageSize, sort, order, taskId));
    }

    @GetMapping("/my_statistics")
    @ApiOperation(value = "FE 我的学习任务统计", notes = "我的学习任务统计")
    //@UserAuthorization
    public ResponseBean myStatistics() {
        return new ResponseBean(examStudyTaskService.myStatistics());
    }

    @GetMapping("/my_course")
    @ApiOperation(value = "FE 我的课程详情", notes = "我的课程详情")
    //@UserAuthorization
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "courseId", value = "课程id", required = true, dataType = "Long"),
    })
    public ResponseBean myCourse(@RequestParam Long taskId, @RequestParam Long courseId) {
            return new ResponseBean(examStudyTaskService.myCourse(taskId, courseId));
    }

    /**
     * 学习任务列表，匿名用户查询向后台传一个六位数随机ID，登录用户ID大于六位，以此区分返回课程类型为公开还是非公开
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @param major
     * @param anonUserId 是否为匿名用户
     * @return
     */
    @GetMapping("/my_task_with_course_list")
    @ApiOperation(value = "FE 我的学习任务列表(含课程)", notes = "我的学习任务列表(含课程)。当传入major参数，接口返回此专业的数据，适合在该专业下分页查询时调用。而不传major参数时，返回所有专业的数据，适合进入页面时第一次调用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = "4", dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = "major", value = "专业分类", dataType = "String"),
            @ApiImplicitParam(name = "anonUserId", value = "匿名用户ID", dataType = "String")
    })
    //@UserAuthorization
    public ResponseBean<?> myTaskWithCourseList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                                @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = "4") Integer pageSize,
                                                @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                                @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                                @RequestParam(required = false) String major,
                                                @RequestParam(required = false) String anonUserId
    ) {
        String user = SysUtil.getUser();

        //如果user为null或者为“anonymousUser” ，表示为匿名用户
        if(!StringUtils.equalsIgnoreCase("anonymousUser",user)){

            //已登录用户查询公开和私有学习任务
            return new ResponseBean<>(examStudyTaskService.myTaskWithCourseList(pageNum, pageSize, sort, order, major));
        }else{

            //匿名用户查询公开学习任务
            return new ResponseBean<>(examStudyTaskService.publicTaskList(pageNum, pageSize, sort, order, major));
        }
    }

    /******************************以下是暴露给Feign的*******************************/

    /**
     * 根据用户id 删除 用户与任务的关联
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/del_user_relation")
    //@AdminTenantTeacherAuthorization
    public Boolean delUserRelation(@RequestParam(value = "userId") Long userId) {
        examStudyTaskService.delUserRelation(userId, null);
        return true;
    }
}
