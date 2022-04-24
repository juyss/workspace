package com.github.tangyi.exam.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.security.annotations.UserAuthorization;
import com.github.tangyi.exam.api.dto.req.StudyReq;
import com.github.tangyi.exam.api.dto.res.StudyDoingRes;
import com.github.tangyi.exam.api.dto.res.StudyOverRes;
import com.github.tangyi.exam.api.dto.res.StudyStartRes;
import com.github.tangyi.exam.service.StudyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api("FE 学习")
@RestController
@RequestMapping("/v1/study")
@AllArgsConstructor
public class StudyController {

    private StudyService studyService;

    @ApiOperation(value = "开始学习", notes = "开始一个任务中的一个课程")
    //@UserAuthorization
    @PutMapping("/start/{id}")
    @ApiImplicitParam(name = "anonUserId", value = "匿名用户ID", required = false, dataType = "String")
    public ResponseBean<StudyStartRes> start(@RequestBody StudyReq studyReq, @PathVariable(value = "id",required = false) String anonUserId) {
        return new ResponseBean<StudyStartRes>(studyService.start(studyReq , anonUserId));
    }

    @ApiOperation(value = "学习中", notes = "学习进行中，需要间隔duration时间，调用此api,以反馈学习状态")
    //@UserAuthorization
    @PutMapping("/doing/{id}")
    @ApiImplicitParam(name = "anonUserId", value = "匿名用户ID", required = false, dataType = "String")
    public ResponseBean<StudyDoingRes> doing(@RequestBody StudyReq studyReq, @PathVariable(value = "id",required = false) String anonUserId) {
        return new ResponseBean<StudyDoingRes>(studyService.doing(studyReq ,anonUserId));
    }

    @ApiOperation(value = "完成学习", notes = "达到学时了，可完成课程学习")
    //@UserAuthorization
    @PutMapping("/finish/{id}")
    @ApiImplicitParam(name = "anonUserId", value = "匿名用户ID", required = false, dataType = "String")
    public ResponseBean<StudyOverRes> finish(@RequestBody StudyReq studyReq, @PathVariable(value = "id",required = false) String anonUserId) {
        return new ResponseBean<StudyOverRes>(studyService.finish(studyReq, anonUserId));
    }
}
