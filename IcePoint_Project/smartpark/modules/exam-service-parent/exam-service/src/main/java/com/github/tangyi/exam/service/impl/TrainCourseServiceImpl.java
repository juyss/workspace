package com.github.tangyi.exam.service.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.util.DateUtils;
import com.github.tangyi.core.common.util.ExcelUtils;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.core.mybatis.page.PageUtils;
import com.github.tangyi.core.mybatis.service.CommonDaoService;
import com.github.tangyi.exam.api.enums.TrainCourseEnum;
import com.github.tangyi.exam.mapper.TrainCourseMapper;
import com.github.tangyi.exam.service.DeptService;
import com.github.tangyi.exam.service.TrainCourseService;
import com.github.tangyi.example.TrainCourseExample;
import com.github.tangyi.example.TrainUserRelationExample;
import com.github.tangyi.model.TrainCourse;
import com.github.tangyi.model.TrainUserRelation;
import com.github.tangyi.user.api.feign.UserServiceClient;
import com.github.tangyi.user.api.module.Dept;
import lombok.AllArgsConstructor;
import lombok.Data;
import my.convert.Map2Bean;
import my.xh.validate.CustomException;
import my.xh.validate.ValidateField;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TrainCourseServiceImpl implements TrainCourseService {
    private static Logger logger = LoggerFactory.getLogger(TrainCourseServiceImpl.class);

    private CommonDaoService commonDaoService;
    private TrainCourseMapper trainCourseMapper;
    private UserServiceClient userServiceClient;
    private DeptService deptService;

    @CustomException(excptionClazz = CommonException.class)
    @ValidateField(index = 0, filedName = "courseName", minLen = 1, maxLen = 64, notNull = true)
    @ValidateField(index = 0, filedName = "teacher", notNull = true, minLen = 1, maxLen = 64)
    @ValidateField(index = 0, filedName = "place", notNull = true, minLen = 1, maxLen = 64)
    @ValidateField(index = 0, filedName = "startTime", notNull = true, dateRule = ">,now")
//    @ValidateField(index = 0, filedName = "signUpStartTime", notNull = true, dateRule = ">,now")
//    @ValidateField(index = 0, filedName = "signUpEndTime", notNull = true, dateRule = ">,now")
    @ValidateField(index = 0, filedName = "endTime", notNull = true, dateRule = ">,now")
    @ValidateField(index = 0, filedName = "courseIntroduce", maxLen = 128)
    @ValidateField(index = 0, filedName = "status", maxVal = 0)
    @Override
    public int save(TrainCourse trainCourse) {
        trainCourse.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
        if (trainCourse.isNewRecord()) return commonDaoService.insert(trainCourse);
        return commonDaoService.update(trainCourse);
    }

    @CustomException(excptionClazz = CommonException.class)
    @ValidateField(index = 3, regStr = "[a-z_]+")
    @Override
    public PageResult getList(BaseReq baseReq, Integer pageNum, Integer pageSize, String sort, String order) {

        TrainCourseExample trainCourseExample = new TrainCourseExample();
        TrainCourseExample.Criteria criteria = trainCourseExample.and().andValid();
        if (StringUtils.isNotBlank(baseReq.getKw()))
            criteria.andCourseNameLike(String.format("%%%s%%", baseReq.getKw()));
        //这里只匹配开始时间
        if (baseReq.getStartTime() != null)
            criteria.andStartTimeGreaterThanOrEqualTo(baseReq.getStartTime());
        if (baseReq.getEndTime() != null)
            criteria.andStartTimeLessThanOrEqualTo(baseReq.getEndTime());

        trainCourseExample.setOrderByClause(String.format("%s %s", sort, order));

        return PageUtils.query(pageNum, pageSize, 10, () -> commonDaoService.selectByExample(trainCourseExample));
    }

    @Override
    public int del(Long id) {
        TrainCourse trainCourse = new TrainCourse();
        trainCourse.setId(id);
        return commonDaoService.delete(trainCourse);
    }

    @ValidateField(index = 3, regStr = "[a-z_]+")
    @Override
    @ValidateField(index = 0, filedName = "id", minVal = 1, notNull = true)
    public PageResult getRelationList(BaseReq baseReq, Long id, Integer pageNum, Integer pageSize, String sort, String order) {
        PageResult result = PageUtils.query(pageNum, pageSize, 10, () -> trainCourseMapper.getRelationList(baseReq, id, sort, order));
        List<Map<String, Object>> mapList = (List<Map<String, Object>>)result.getRows();


        //对于每一条数据
        for (Map<String, Object> map : mapList) {
            String deptName = (String) map.get("deptName");

            //如果deptName为空，证明此用户为统一认证用户，需要通过中间表进行查询部门信息
//            if (!StringUtils.isNotEmpty(deptName)) {
            Long userId = (Long) map.get("userId"); //获取用户id
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
        return result;
    }

    /**
     * @return com.github.tangyi.core.common.web.PageResult
     * @Author xh
     * @Description 前台  返回未开始的 报名列表
     * @Date 10:17 2020/10/18
     * @Param [pageNum, pageSize, sort, order]
     **/
    @Override
    public PageResult getApplyList(Integer pageNum, Integer pageSize, String sort, String order) {

        List<TrainUserRelation> trainUserRelations = getTrainUserRelations();
        List<Long> ids = trainUserRelations.stream().map(TrainUserRelation::getTrainCourseId).collect(Collectors.toList());

        TrainCourseExample trainCourseExample = new TrainCourseExample();
        TrainCourseExample.Criteria criteria = trainCourseExample.and().andValid().andStatusEqualTo(TrainCourseEnum.CourseStatus.ready.getValue());
        Date now = new Date();
        criteria.andStartTimeGreaterThanOrEqualTo(now);
        criteria.andIdNotIn(ids);
        trainCourseExample.setOrderByClause(String.format("%s %s", sort, order));

        return PageUtils.query(pageNum, pageSize, 10, () -> commonDaoService.selectByExample(trainCourseExample));
    }

    /**
     * @return com.github.tangyi.core.common.web.PageResult
     * @Author xh
     * @Description 前台，返回签到列表
     * @Date 10:18 2020/10/18
     * @Param [pageNum, pageSize, sort, order]
     **/
    @Override
    public PageResult getSignList(Integer pageNum, Integer pageSize, String sort, String order) {

        List<TrainUserRelation> trainUserRelations = getTrainUserRelations();
        if (trainUserRelations.size() == 0) return new PageResult(pageNum, pageSize); // 该用户没有报名过，就返回空数组

        List<Long> ids = trainUserRelations.stream().map(TrainUserRelation::getTrainCourseId).collect(Collectors.toList());

        TrainCourseExample trainCourseExample = new TrainCourseExample();
        TrainCourseExample.Criteria criteria = trainCourseExample.and().andValid().andStatusEqualTo(TrainCourseEnum.CourseStatus.ready.getValue());
        criteria.andIdIn(ids);
        Date now = new Date();
        criteria.andEndTimeGreaterThan(now);
        // criteria.andSignUpStartTimeLessThanOrEqualTo(now).andSignUpEndTimeGreaterThanOrEqualTo(now);
        trainCourseExample.setOrderByClause(String.format("%s %s", sort, order));

        PageResult result = PageUtils.query(pageNum, pageSize, 10, () -> commonDaoService.selectByExample(trainCourseExample));
        List<TrainCourse> rows = (List<TrainCourse>) result.getRows();
        List<TrainCourseSignVo> collect = rows.stream().map(item -> new TrainCourseSignVo(item, trainUserRelations)).collect(Collectors.toList());
        result.setRows(collect);
        return result;
    }

    @Data
    public static class TrainCourseSignVo extends TrainCourse {

        protected Integer isSignIn;//是否签到
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        protected Date signInTime;//签到时间

        public TrainCourseSignVo() {
        }

        public TrainCourseSignVo(TrainCourse trainCourse, List<TrainUserRelation> trainUserRelations) {
            try {
                Map2Bean.getInstance().getBeanFromBean(TrainCourseSignVo.class, this, trainCourse);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (TrainUserRelation item : trainUserRelations) {
                if (item.getTrainCourseId().equals(this.getId())) {
                    this.setSignInTime(item.getSignInTime());
                    this.setIsSignIn(item.getIsSignIn());
                    break;
                }
            }
        }
    }

    /**
     * 查询当前用户报名过的列表
     *
     * @return
     */
    private List<TrainUserRelation> getTrainUserRelations() {

        String user = SysUtil.getUser();
        String tenantCode = SysUtil.getTenantCode();
        ResponseBean<UserVo> userVoResponseBean = userServiceClient.findUserByIdentifier(user, tenantCode);
        Long userId = userVoResponseBean.getData().getUserId();

        TrainUserRelationExample trainUserRelationExample = new TrainUserRelationExample();
        trainUserRelationExample.and().andValid().andUserIdEqualTo(userId).andTenantCodeEqualTo(tenantCode);

        return commonDaoService.selectByExample(trainUserRelationExample);
    }

    /**
     * @return int
     * @Author xh
     * @Description 报名
     * @Date 14:50 2020/10/18
     * @Param [trainCourseId]
     **/
    @Override
    public int apply(Long trainCourseId) {
        TrainCourse trainCourse = commonDaoService.get(TrainCourse.class, trainCourseId);
        if (trainCourse == null) throw new CommonException("培训不存在");

        String user = SysUtil.getUser();
        String tenantCode = SysUtil.getTenantCode();

        ResponseBean<UserVo> userVoResponseBean = userServiceClient.findUserByIdentifier(user, tenantCode);

        TrainUserRelationExample trainUserRelationExample = new TrainUserRelationExample();
        trainUserRelationExample.and().andValid().andUserIdEqualTo(userVoResponseBean.getData().getUserId())
                .andTrainCourseIdEqualTo(trainCourseId);
        TrainUserRelation trainUserRelation = commonDaoService.selectOneByExample(trainUserRelationExample);

        if (trainUserRelation != null) throw new CommonException("已报名");

        trainUserRelation = new TrainUserRelation();
        trainUserRelation.setIsSignIn(TrainCourseEnum.IS_SIGN_IN.no_sign_in.getValue());
        trainUserRelation.setUserId(userVoResponseBean.getData().getUserId());
        trainUserRelation.setTrainCourseId(trainCourseId);

        trainUserRelation.setCommonValue(user, SysUtil.getSysCode(), tenantCode);

        return commonDaoService.insert(trainUserRelation);
    }

    /**
     * @param trainCourseId
     * @return int
     * @Author xh
     * @Description 签到
     * @Date 14:50 2020/10/18
     * @Param [trainCourseId]
     */
    @Override
    public int sign(Long trainCourseId) {
        TrainCourse trainCourse = commonDaoService.get(TrainCourse.class, trainCourseId);
        if (trainCourse == null) throw new CommonException("培训不存在");

        String user = SysUtil.getUser();
        String tenantCode = SysUtil.getTenantCode();

        ResponseBean<UserVo> userVoResponseBean = userServiceClient.findUserByIdentifier(user, tenantCode);

        TrainUserRelationExample trainUserRelationExample = new TrainUserRelationExample();
        trainUserRelationExample.and().andValid().andUserIdEqualTo(userVoResponseBean.getData().getUserId())
                .andTrainCourseIdEqualTo(trainCourseId);
        TrainUserRelation trainUserRelation = commonDaoService.selectOneByExample(trainUserRelationExample);
        if (trainUserRelation == null) throw new CommonException("未报名该培训");

        if (TrainCourseEnum.IS_SIGN_IN.sign_in.getValue().equals(trainUserRelation.getIsSignIn())) return 0;

        trainUserRelation.setIsSignIn(TrainCourseEnum.IS_SIGN_IN.sign_in.getValue());//签到
        trainUserRelation.setSignInTime(new Date());

        return commonDaoService.update(trainUserRelation);

    }

    @Override
    public void export(Long id, HttpServletResponse response) {
        Map<String, Object> data = new HashMap<String, Object>();
        BaseReq baseReq = new BaseReq();

        List<Map<String, Object>> dataList = trainCourseMapper.getRelationList(baseReq, id, CommonConstant.PAGE_SORT_DEFAULT, "asc");

        //对于每一条数据
        for (Map<String, Object> map : dataList) {
            String deptName = (String) map.get("deptName");

            //如果deptName为空，证明此用户为统一认证用户，需要通过中间表进行查询部门信息
//            if (!StringUtils.isNotEmpty(deptName)) {
            Long userId = (Long) map.get("userId"); //获取用户id
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

        TrainCourse entity = commonDaoService.get(TrainCourse.class, id);
        data.put("dataList", dataList);
        data.put("trainCourse", entity);
        data.put("dataFormat", new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"));
        String filename = "培训报名人员列表-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String template = "train_user_relation_template";
        ExcelUtils.export(data, filename, template, response);
    }

    /**
     * @return com.github.tangyi.model.TrainCourse
     * @Author xh
     * @Description 审核
     * @Date 21:49 2020/10/18
     * @Param [id, status]
     **/
    @Override
    public TrainCourse check(Long id, Integer status) {
        TrainCourse trainCourse = commonDaoService.get(TrainCourse.class, id);
        if (trainCourse == null) throw new CommonException("培训不存在");

        trainCourse.setStatus(TrainCourseEnum.CourseStatus.getByValue(status).getValue());
        trainCourse.setModifier(SysUtil.getUser());

        ResponseBean<UserVo> userVoResponseBean = userServiceClient.findUserByIdentifier(SysUtil.getUser(), SysUtil.getTenantCode());
        trainCourse.setAuditUserName(userVoResponseBean.getData().getName());
        trainCourse.setAuditUserId(userVoResponseBean.getData().getUserId());
        commonDaoService.update(trainCourse);
        return trainCourse;
    }
}
