package com.github.tangyi.exam.controller;

import cn.hutool.core.convert.Convert;
import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.persistence.BaseEntity;
import com.github.tangyi.common.core.utils.PageUtil;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.common.security.annotations.AdminTenantTeacherAuthorization;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.BaseConstants;
import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.common.util.DateUtils;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.exam.api.dto.ExaminationDto;
import com.github.tangyi.exam.api.dto.ExaminationReportDto;
import com.github.tangyi.exam.api.dto.ExaminationUserRelationDto;
import com.github.tangyi.exam.api.dto.SubjectDto;
import com.github.tangyi.exam.api.dto.req.NotAddExaminationSubjectQry;
import com.github.tangyi.exam.api.module.Examination;
import com.github.tangyi.exam.api.module.ExaminationRecord;
import com.github.tangyi.exam.api.module.ExaminationSubject;
import com.github.tangyi.exam.api.module.ExaminationUserRelation;
import com.github.tangyi.exam.service.ExamRecordService;
import com.github.tangyi.exam.service.ExaminationService;
import com.github.tangyi.exam.service.ExaminationSubjectService;
import com.github.tangyi.exam.service.ExaminationUserRelationService;
import com.github.tangyi.exam.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * 考试controller
 *
 * @author gaokx
 * @date 2020/10/24 23:06
 */
@Slf4j

@Api("考试信息管理")
@RestController
@RequestMapping("/v1/examination")
public class ExaminationController extends BaseController {
    @Autowired
    private  ExaminationService examinationService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private  ExaminationUserRelationService examinationUserRelationService;

    @Autowired
    private ExamRecordService examRecordService;

    @Autowired
    private ExaminationSubjectService examinationSubjectService;


    /**
     * 根据ID获取
     *
     * @param id id
     * @return ResponseBean
     * @author tangyi
     * @date 2018/11/10 21:08
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取考试信息", notes = "根据考试id获取考试详细信息")
    @ApiImplicitParam(name = "id", value = "考试ID", required = true, dataType = "String", paramType = "path")
    public ResponseBean<ExaminationDto> examination(@PathVariable Long id) {
        ExaminationDto examinationDto = new ExaminationDto();
        BeanUtils.copyProperties(examinationService.get(id), examinationDto);
        if (examinationDto != null) {
            examinationDto.setItemScoreTotal(examinationService.getItemScoreTotal(examinationDto.getId()));
        }
        return new ResponseBean<>(examinationDto);
    }

    /**
     * 根据ID获取
     *
     * @param id id
     * @return ResponseBean
     * @author tangyi
     * @date 2018/11/10 21:08
     */
    @GetMapping("/anonymousUser/{id}")
    @ApiOperation(value = "获取考试信息", notes = "根据考试id获取考试详细信息")
    @ApiImplicitParam(name = "id", value = "考试ID", required = true, dataType = "String", paramType = "path")
    public ResponseBean<Examination> anonymousUserGet(@PathVariable Long id) {
        return new ResponseBean<>(examinationService.get(id));
    }

    /**
     * 获取分页数据
     *
     * @param pageNum     pageNum
     * @param pageSize    pageSize
     * @param sort        sort
     * @param order       order
     * @param examination examination
     * @return PageInfo
     * @author tangyi
     * @date 2018/11/10 21:10
     */
    @GetMapping("examinationList")
    @ApiOperation(value = "获取考试列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = "examination", value = "考试信息", dataType = "Examination")
    })
    public PageInfo<ExaminationDto> examinationList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) String pageNum,
                                                    @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) String pageSize,
                                                    @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                                    @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                                    Examination examination) {
        //匹配我的考试和所有开放的考试
        return examinationService.examinationList(pageNum, pageSize, sort, order, examination);
    }

    /**
     * 根据考试ID获取题目分页数据
     *
     * @param pageNum    pageNum
     * @param pageSize   pageSize
     * @param sort       sort
     * @param order      order
     * @param subjectDto subjectDto
     * @return PageInfo
     * @author tangyi
     * @date 2019/6/16 15:45
     */
    @RequestMapping("subjectList")
    @ApiOperation(value = "根据考试获取题目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = "subjectDto", value = "题目信息", dataType = "SubjectDto")
    })
    public PageInfo<SubjectDto> subjectList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) String pageNum,
                                            @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) String pageSize,
                                            @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                            @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
			SubjectDto subjectDto) {
        return examinationService.findSubjectPageById(subjectDto, pageNum, pageSize, sort, order);
    }


    /**
     * 获取未加入到当前考试的试题
     *
     * @param pageNum    pageNum
     * @param pageSize   pageSize
     * @param sort       sort
     * @param order      order
     * @return PageInfo
     * @author tangyi
     * @date 2019/6/16 15:45
     */
    @RequestMapping("notAddSubjectList")
    @ApiOperation(value = "获取未加入到当前考试的试题")
    @ApiImplicitParams({
        @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
        @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
        @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
        @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
        @ApiImplicitParam(name = "subjectDto", value = "题目信息", dataType = "SubjectDto")
    })
    public PageInfo<SubjectDto> notAddSubjectList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) String pageNum,
        @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) String pageSize,
        @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
        @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
        NotAddExaminationSubjectQry qry) {
        return examinationService.findSubjectExcludeExaminationId(qry, pageNum, pageSize, sort, order);
    }

    /**
     * 获取全部题目
     * @param subjectDto subjectDto
     * @return ResponseBean
     * @author tangyi
     * @date 2020/3/12 1:00 下午
     */
	@RequestMapping("anonymousUser/allSubjectList")
	@ApiOperation(value = "获取全部题目列表")
	@ApiImplicitParam(name = "subjectDto", value = "题目信息", dataType = "SubjectDto")
    public ResponseBean<List<SubjectDto>> allSubjectList(SubjectDto subjectDto) {
		return new ResponseBean<>(examinationService.allSubjectList(subjectDto));
	}

    /**
     * 创建
     *
     * @param examinationDto examinationDto
     * @return ResponseBean
     * @author tangyi
     * @date 2018/11/10 21:14
     */
    @PostMapping
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "创建考试", notes = "创建考试")
    @ApiImplicitParam(name = "examinationDto", value = "考试实体examinationDto", required = true, dataType = "ExaminationDto")
    @Log("新增考试")
    public ResponseBean<Boolean> addExamination(@RequestBody @Valid ExaminationDto examinationDto) {
        return new ResponseBean<>(examinationService.insert(examinationDto) > 0);
    }

    /**
     * 更新
     *
     * @param examinationDto examinationDto
     * @return ResponseBean
     * @author tangyi
     * @date 2018/11/10 21:15
     */
    @PutMapping
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "更新考试信息", notes = "根据考试id更新考试的基本信息")
    @ApiImplicitParam(name = "examinationDto", value = "考试实体answer", required = true, dataType = "ExaminationDto")
    @Log("更新考试")
    public ResponseBean<Boolean> updateExamination(@RequestBody @Valid ExaminationDto examinationDto) {
        return new ResponseBean<>(examinationService.update(examinationDto) > 0);
    }

    /**
     * 删除考试
     *
     * @param id id
     * @return ResponseBean
     * @author tangyi
     * @date 2018/11/10 21:20
     */
    @DeleteMapping("{id}")
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "删除考试", notes = "根据ID删除考试")
    @ApiImplicitParam(name = "id", value = "考试ID", required = true, paramType = "path")
    @Log("删除考试")
    public ResponseBean<Boolean> deleteExamination(@PathVariable Long id) {
        boolean success = false;
        try {
            Examination examination = examinationService.get(id);
            if (examination != null) {
                examination.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
                success = examinationService.delete(examination) > 0;
            }
        } catch (Exception e) {
            log.error("Delete examination failed", e);
        }
        return new ResponseBean<>(success);
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ResponseBean
     * @author tangyi
     * @date 2018/12/03 22:03
     */
    @PostMapping("deleteAll")
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "批量删除考试", notes = "根据考试id批量删除考试")
    @ApiImplicitParam(name = "ids", value = "考试ID", dataType = "String")
    @Log("批量删除考试")
    public ResponseBean<Boolean> deleteAllExaminations(@RequestParam(value = "ids") String ids) {
        boolean success = false;
        try {
            if (StringUtils.isNotEmpty(ids)){
                success = examinationService.deleteAll(Convert.toLongArray(ids.split(","))) > 0;
            }
        } catch (Exception e) {
            log.error("Delete examination failed", e);
        }
        return new ResponseBean<>(success);
    }

  /**
   * 绑定试题到考试
   *
   * @param examinationId examinationId
   * @param subjectIds subjectIds
   * @return ResponseBean
   * @author gaokx
   * @date 2020/11/10 21:43
   */
  @PostMapping("addSubjectToExamination")
  //@AdminTenantTeacherAuthorization
  @ApiImplicitParams({
      @ApiImplicitParam(name = "examinationId", value = "考试ID", required = true, dataType = "String"),
      @ApiImplicitParam(name = "subjectIds", value = "试题ID", required = true, dataType = "String")})
  @Log("绑定试题到考试")
  public ResponseBean<Boolean> addExaminationIdSubject(@RequestParam(value = "examinationId")  String  examinationId,
      @RequestParam(value = "subjectIds")  String  subjectIds) {
    if(StringUtils.isEmpty(subjectIds)  || StringUtils.isEmpty(examinationId)){
      throw new CommonException("考试id和试题id串不允许为空");
    }
    Examination examination = examinationService.get(Long.parseLong(examinationId));
    if (examination == null) {
      throw new CommonException("考试不存在");
    }
    Long[] subjectIdsLong = Convert.toLongArray(subjectIds.split(","));
    ExaminationSubject examinationSubject ;
    HashSet subjectIdSet = new HashSet<>(Arrays.asList(subjectIdsLong));
    if (subjectIdSet.size() != subjectIdsLong.length) {
      throw new CommonException("试题重复");
    }
    for (Long subjectId : subjectIdsLong) {
      SubjectDto subjectdto = subjectService.get(subjectId);
      if (subjectdto == null) {
        throw new CommonException("试题不存在:" + subjectId);
      }
      ExaminationSubject examinationSubjectQry = new ExaminationSubject();
      examinationSubjectQry.setExaminationId(examination.getId());
      examinationSubjectQry.setSubjectId(subjectId);
      ExaminationSubject record = examinationSubjectService.findByExaminationIdAndSubjectId(examinationSubjectQry);
      if (record != null) {
        throw new CommonException("试题已加入到考试");
      }
      examinationSubject = new ExaminationSubject();
      examinationSubject.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
      examinationSubject.setSubjectId(subjectId);
      examinationSubject.setExaminationId(examination.getId());
      examinationSubject.setCategoryId(subjectdto.getCategoryId());
      examinationSubject.setType(subjectdto.getType());
      examinationSubjectService.insert(examinationSubject);
    }
    return new ResponseBean<>(true);
  }

    /**
     * 根据考试ID查询题目id列表
     *
     * @param examinationId examinationId
     * @return ResponseBean
     * @author tangyi
     * @date 2019/06/18 14:31
     */
    @ApiImplicitParam(name = "examinationId", value = "考试ID", required = true, paramType = "path")
    @GetMapping("/{examinationId}/subjectIds")
    public ResponseBean<List<ExaminationSubject>> findExaminationSubjectIds(@PathVariable Long examinationId) {
		List<ExaminationSubject> subjects = examinationService.findListByExaminationId(examinationId);
		subjects.forEach(BaseEntity::clearCommonValue);
        return new ResponseBean<>(subjects);
    }

    /**
     * 根据考试ID查询题目id列表
     *
     * @param examinationId examinationId
     * @return ResponseBean
     * @author tangyi
     * @date 2019/06/18 14:31
     */
    @ApiImplicitParam(name = "examinationId", value = "考试ID", required = true, paramType = "path")
    @GetMapping("/anonymousUser/{examinationId}/subjectIds")
    public ResponseBean<List<ExaminationSubject>> anonymousUserFindExaminationSubjectIds(@PathVariable Long examinationId) {
        List<ExaminationSubject> subjects = examinationService.findListByExaminationId(examinationId);
        subjects.forEach(BaseEntity::clearCommonValue);
        return new ResponseBean<>(subjects);
    }

    /**
     * 根据考试ID生成二维码
     * @param examinationId examinationId
     * @param response response
     * @author tangyi
     * @date 2020/3/15 1:16 下午
     */
    @ApiOperation(value = "生成二维码", notes = "生成二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examinationId", value = "考试ID", required = true, dataType = "Long", paramType = "path")
    })
    @GetMapping("anonymousUser/generateQrCode/{examinationId}")
    public void produceCode(@PathVariable Long examinationId, HttpServletResponse response) throws Exception {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(examinationService.produceCode(examinationId)); ServletOutputStream out = response.getOutputStream()) {
            BufferedImage image = ImageIO.read(inputStream);
            ImageIO.write(image, "PNG", out);
        }
    }

    /**
     * 根据考试ID生成二维码
     * @param examinationId examinationId
     * @param response response
     * @author tangyi
     * @date 2020/3/21 5:38 下午
     */
    @ApiOperation(value = "生成二维码(v2)", notes = "生成二维码(v2)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examinationId", value = "考试ID", required = true, dataType = "Long", paramType = "path")
    })
    @GetMapping("anonymousUser/generateQrCode/v2/{examinationId}")
    public void produceCodeV2(@PathVariable Long examinationId, HttpServletResponse response) throws Exception {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(examinationService.produceCodeV2(examinationId)); ServletOutputStream out = response.getOutputStream()) {
            BufferedImage image = ImageIO.read(inputStream);
            ImageIO.write(image, "PNG", out);
        }
    }

    /**
     * 根据考试ID查询用户列表
     *
     * @param examinationUserRelationDto examinationUserRelationDto
     * @return ResponseBean
     * @author gaokx
     * @date 2020/10/22 14:31
     */
    @GetMapping("/userList")
    @ApiOperation(value = "根据考试ID查询用户列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
        @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
        @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
        @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
        @ApiImplicitParam(name = "examinationUserRelationDto", value = "用户信息", dataType = "ExaminationUserRelationDto")
    })
    public PageInfo<ExaminationUserRelationDto> findExaminationUsers(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) String pageNum,
        @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) String pageSize,
        @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
        @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
        ExaminationUserRelationDto examinationUserRelationDto) {
        if (examinationUserRelationDto == null || examinationUserRelationDto.getExaminationId() == null) {
            throw new CommonException("examinationId 不允许为空");
        }
        return  examinationUserRelationService.findUserInfoListPage(PageUtil.pageInfo(pageNum, pageSize, sort, order), examinationUserRelationDto);
    }


    /**
     * 删除
     *
     * @param examinationId examinationId
     * @param subjectIds subjectIds
     * @return ResponseBean
     * @author gaokx
     * @date 2020/11/10 21:43
     */
    @DeleteMapping("/deleteExaminationSubject")
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "批量删除考试关联的试题", notes = "根据ID删除题目")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "examinationId", value = "考试ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "subjectIds", value = "试题ID", required = true, dataType = "String")})
    @Log("批量删除考试关联的试题")
    public ResponseBean<Boolean> deleteExaminationSubject(@RequestParam(value = "examinationId")  String  examinationId,
        @RequestParam(value = "subjectIds")  String  subjectIds) {
        boolean success ;
        if(StringUtils.isEmpty(subjectIds)  || StringUtils.isEmpty(examinationId)){
            throw new CommonException("考试id和试题id串不允许为空");
        }
        Examination examination = examinationService.get(Long.parseLong(examinationId));
        if (examination == null) {
            throw new CommonException("考试不存在");
        }
        Long[] subjectIdsLong = Convert.toLongArray(subjectIds.split(","));
        List<Long> idList = new ArrayList<>();
        for(Long subjectId : subjectIdsLong){
            ExaminationSubject examinationSubject = new ExaminationSubject();
            examinationSubject.setExaminationId(examination.getId());
            examinationSubject.setSubjectId(subjectId);
            ExaminationSubject record = examinationSubjectService.findByExaminationIdAndSubjectId(examinationSubject);
            if (record == null) {
                throw new CommonException("试题和考试关系不存在");
            }
            examinationSubject.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
            idList.add(record.getId());
        }
        success = examinationSubjectService.deleteAll(idList.toArray(new Long[idList.size()])) > 0;
        return new ResponseBean<>(success);
    }

    /**
     * 批量删除考试关联用户
     *
     * @param ids ids
     * @return ResponseBean
     * @author gaokx
     * @date 2020/10/22 17:55
     */
    @PostMapping("deleteExaminationsRelationUsers")
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "批量删除考试关联用户", notes = "根据关联记录id批量删除考试用户")
    @ApiImplicitParam(name = "ids", value = "关联记录id", dataType = "String")
    @Log("批量删除考试关联用户")
    public ResponseBean<Boolean> deleteExaminationsRelationUsers(@RequestParam(value = "ids") String ids) {
        boolean success = false;
        try {
            if (StringUtils.isNotEmpty(ids)){
                success = examinationUserRelationService.physicalDeleteAll(Convert.toLongArray(ids.split(","))) > 0;
            }
        } catch (Exception e) {
            log.error("Delete deleteExaminationsRelationUsers failed", e);
            throw new CommonException("Delete deleteExaminationsRelationUsers failed, " + e.getMessage());
        }
        return new ResponseBean<>(success);
    }


    /**
     * 批量删除考试关联用户
     *
     * @param examinationId ids
     * @param userIds userIds
     * @return ResponseBean
     * @author gaokx
     * @date 2020/10/22 17:55
     */
    @PostMapping("addExaminationUserRelations")
    //@AdminTenantTeacherAuthorization
    @ApiOperation(value = "新增考试关联用户", notes = "新增考试关联用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "examinationId", value = "考试id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "userIds", value = "用户id串(逗号分割)", required = true, dataType = "String")})
    @Log("新增考试关联用户")
    public ResponseBean<Boolean> addExaminationUserRelations(@RequestParam(value = "examinationId") Long examinationId,
        @RequestParam(value = "userIds") String userIds) {
        List<ExaminationUserRelation> examinationUserRelations = new ArrayList<>();
        String[] userIdArr = userIds.split(",");
        ExaminationUserRelation tmp   = new ExaminationUserRelation();
        Set set = new HashSet<>();
        for (String userId : userIdArr) {
            tmp.setUserId(Long.parseLong(userId));
            tmp.setExaminationId(examinationId);
            List<ExaminationUserRelation> examinationUserRelation = examinationUserRelationService.findAllList(tmp);
            if (CollectionUtils.isNotEmpty(examinationUserRelation) || !set.add(userId)) {
                throw new CommonException("用户id ：" + userId + "已经添加到当前考试当中，不允许重复添加");
            }
            ExaminationUserRelation relation = new ExaminationUserRelation();
            relation.setExaminationId(examinationId);
            relation.setUserId(Long.parseLong(userId));
            relation.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
            examinationUserRelations.add(relation);
        }
        examinationUserRelationService.batchInsert(examinationUserRelations);
        return new ResponseBean<>(true);
    }



    /**
     * 考试报表
     *
     * @param   startTime
     * @param   endTime
     * @return ResponseBean
     * @author gaokx
     * @date 2020/10/22 14:31
     */
    @GetMapping("/findExaminationReport")
    @ApiOperation(value = "考试报表查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
        @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
        @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
        @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
        @ApiImplicitParam(name = "startTime", value = "开始时间(yyyy-MM-dd HH:mm:ss)", dataType = "String"),
        @ApiImplicitParam(name = "examinationName", value = "考试名称", dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "结束时间(yyyy-MM-dd HH:mm:ss)", dataType = "String")})
    public PageInfo<ExaminationReportDto> findExaminationReport(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) String pageNum,
        @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) String pageSize,
        @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
        @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
        @RequestParam(value = "startTime", required = false) String startTime,
        @RequestParam(value = "endTime", required = false) String endTime,
        @RequestParam(value = "examinationName", required = false) String examinationName) {
        return  examRecordService.findExaminationReport(PageUtil.pageInfo(pageNum, pageSize, sort, order), startTime,endTime,examinationName);
    }

    /*****************************************以下是前台api*********************************************************/


    @GetMapping("/my_examinations")
    @ApiOperation(value = "FE 获取我的考试列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "Integer"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "Integer"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = "examination", value = "考试信息", dataType = "Examination")
    })
    public ResponseBean myExaminations(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                                    @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                                    @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                                    @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                                    Examination examination) {
      PageResult pageResult = examinationService.myExaminations(pageNum, pageSize, sort, order, examination);
      if(CollectionUtils.isNotEmpty(pageResult.getRows())) {
        List<Map> rows = (List<Map>) pageResult.getRows();
        Date currentDate = new Date(System.currentTimeMillis());
        for (Map map : rows) {
          Timestamp startTime = (Timestamp) map.get("startTime");
          Timestamp endTime = (Timestamp) map.get("endTime");
          int examinationStatus = 0;
          if (startTime == null || endTime == null) {
            continue;
          }
          Date startDate = new Date(startTime.getTime());
          Date endDate = new Date(endTime.getTime());
          if (DateUtils.truncatedCompareTo(currentDate, startDate, Calendar.SECOND) < 0) {
            examinationStatus = 0 ;
          } else if (DateUtils.truncatedCompareTo(currentDate, endDate, Calendar.SECOND) >= 0) {
            examinationStatus = 2 ;
          } else {
            Boolean between = DateUtils.between(currentDate, startDate, endDate);
            if (between) {
              examinationStatus = 1;
            }
          }
          map.put("examinationStatus",examinationStatus);
        }
      }
        return new ResponseBean(pageResult);
    }
}
