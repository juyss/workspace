package com.github.tangyi.exam.service;

import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.utils.PageUtil;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.exam.api.constants.ExamSubjectConstant;
import com.github.tangyi.exam.api.dto.ExaminationSubjectDto;
import com.github.tangyi.exam.api.dto.SubjectCategoryDto;
import com.github.tangyi.exam.api.dto.SubjectDto;
import com.github.tangyi.exam.api.module.ExaminationSubject;
import com.github.tangyi.exam.api.module.Subject;
import com.github.tangyi.exam.api.module.SubjectCategory;
import com.github.tangyi.exam.api.module.SubjectChoices;
import com.github.tangyi.exam.api.module.SubjectJudgement;
import com.github.tangyi.exam.api.module.SubjectShortAnswer;
import com.github.tangyi.exam.enums.SubjectTypeEnum;
import com.github.tangyi.exam.utils.SubjectUtil;
import com.github.tangyi.user.api.module.Student;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 题目service
 *
 * @author gaokx
 * @date 2019/6/16 17:22
 */
@Slf4j
@Service
public class SubjectService {

  /*  private final SubjectChoicesService subjectChoicesService;

    private final SubjectShortAnswerService subjectShortAnswerService;

    private final ExaminationSubjectService examinationSubjectService;

    private final SubjectJudgementService subjectJudgementService;*/

    @Autowired
    private  SubjectCommonService subjectCommonService ;
    
    @Autowired
    private  ExaminationSubjectService examinationSubjectService;

    @Autowired
    private SubjectCategoryService subjectCategoryService ;
    /**
     * 根据题目ID，题目类型查询题目信息
     *
     * @param id   id
     * @param type type
     * @return SubjectDto
     * @author gaokx
     * @date 2020/10/16 17:24
     */
    public SubjectDto get(Long id, Integer type) {
        return subjectService(type).getSubject(id);
    }

    /**
     * 根据题目ID查询题目信息
     *
     * @param id id
     * @return SubjectDto
     * @author gaokx
     * @date 2020/10/16 17:26
     */
    public SubjectDto get(Long id) {
        Integer type = SubjectTypeEnum.CHOICES.getValue();
        ExaminationSubject es = new ExaminationSubject();
        es.setSubjectId(id);
        List<ExaminationSubject> examinationSubjects = examinationSubjectService.findListBySubjectId(es);
        if (CollectionUtils.isNotEmpty(examinationSubjects)) {
            type = examinationSubjects.get(0).getType();
        }
        return subjectService(type).getSubject(id);
    }

    /**
     * 根据上一题ID查找
     *
     * @param examinationId     examinationId
     * @param previousSubjectId previousSubjectId
     * @param type              type
     * @param nextType          0：下一题，1：上一题
     * @return SubjectDto
     * @author gaokx
     * @date 2020/10/18 13:49
     */
    @Transactional
    public SubjectDto getNextByCurrentIdAndType(Long examinationId, Long previousSubjectId, Integer type, Integer nextType) {
        return subjectService(type).getNextByCurrentIdAndType(examinationId, previousSubjectId, nextType);
    }

    /**
     * 查询列表
     *
     * @param subjectDto subjectDto
     * @return SubjectDto
     * @author gaokx
     * @date 2020/10/16 18:12
     */
    public List<SubjectDto> findList(SubjectDto subjectDto) {
        // 先查询题目考试关联表
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setExaminationId(subjectDto.getExaminationId());
        List<ExaminationSubject> examinationSubjects = examinationSubjectService.findList(examinationSubject);
        return this.findSubjectDtoList(examinationSubjects, true);
    }

    /**
     * 查询具体类型的题目列表
     *
     * @param subjectDto subjectDto
     * @return SubjectDto
     * @author gaokx
     * @date 2020/10/17 17:12
     */
    public List<SubjectDto> findListByType(SubjectDto subjectDto) {
        List<SubjectDto> subjectDtos = subjectService(subjectDto.getType()).findSubjectList(subjectDto);
        // 选择题则查找具体的选项
        if (SubjectTypeEnum.CHOICES.getValue().equals(subjectDto.getType()) && CollectionUtils.isNotEmpty(subjectDtos)) {
			// 查找选项信息
			subjectDtos = subjectDtos.stream()
					.map(dto -> SubjectUtil.subjectToDto(subjectCommonService.get(dto.getId()), true))
					.collect(Collectors.toList());
        }
        return subjectDtos;
    }

    /**
     * 查询分页列表
     *
     * @param pageInfo   pageInfo
     * @param subjectDto subjectDto
     * @return SubjectDto
     * @author gaokx
     * @date 2020/10/16 18:12
     */
    public PageInfo<SubjectDto> findPage(PageInfo pageInfo, SubjectDto subjectDto) {
        Subject subject = new Subject();
        if (subjectDto.getCategoryId() != null) {
            List<SubjectCategoryDto> categoryList = subjectCategoryService.getChildCategory(subjectDto.getCategoryId(), true);
            if (CollectionUtils.isNotEmpty(categoryList)) {
                List<Long> ids = categoryList.stream().map(SubjectCategoryDto::getId).collect(Collectors.toList());
                subject.setCategoryIdList(ids);
            } else {
                subject.setCategoryIdList(Collections.singletonList(subjectDto.getCategoryId()));
            }
        }
        subject.setTenantCode(SysUtil.getTenantCode());
        subject.setSubjectName(subjectDto.getSubjectName());
        PageInfo<Subject> examinationSubjectPageInfo = subjectCommonService.findPage(pageInfo, subject);
        List<SubjectDto> subjectDtos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(examinationSubjectPageInfo.getList())) {
                examinationSubjectPageInfo.getList().forEach(tempExaminationSubject -> {
                SubjectDto tempSubjectDto =  SubjectUtil.subjectToDto(tempExaminationSubject, true);
                if(SubjectTypeEnum.isChoices(tempExaminationSubject.getType())){
                    List options = subjectCommonService.getOptions(tempExaminationSubject.getId());
                    tempSubjectDto.setOptions(options);
                }
                subjectDtos.add(tempSubjectDto);
            });
        }
        PageInfo<SubjectDto> subjectDtoPageInfo = new PageInfo<>();
        PageUtil.copyProperties(examinationSubjectPageInfo, subjectDtoPageInfo);
        subjectDtoPageInfo.setList(subjectDtos);
        return subjectDtoPageInfo;
    }

    /**
     * 查询列表
     *
     * @param type type
     * @param ids  ids
     * @return SubjectDto
     * @author gaokx
     * @date 2020/10/16 18:14
     */
    public List<SubjectDto> findListById(Integer type, Long[] ids) {
        return subjectService(type).findSubjectListById(ids);
    }

    /**
     * 保存
     *
     * @param subjectDto subjectDto
     * @return int
     * @author gaokx
     * @date 2020/10/16 17:59
     */
    @Transactional(rollbackFor = Exception.class)
    public int insert(SubjectDto subjectDto) {
        // 保存与考试的关联关系
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setCommonValue(subjectDto.getCreator(), subjectDto.getApplicationCode(),
                subjectDto.getTenantCode());
        if (subjectDto.getExaminationId() != null) {
            examinationSubject.setExaminationId(subjectDto.getExaminationId());
            examinationSubject.setCategoryId(subjectDto.getCategoryId());
            examinationSubject.setSubjectId(subjectDto.getId());
            examinationSubject.setType(subjectDto.getType());
            examinationSubject.setCreateDate(new Date());
            examinationSubject.setCreator(subjectDto.getCreator());
            examinationSubjectService.insert(examinationSubject);
        }
        return subjectService(subjectDto.getType()).insertSubject(subjectDto);
    }

    /**
     * 更新
     *
     * @param subjectDto subjectDto
     * @return int
     * @author gaokx
     * @date 2020/10/16 18:01
     */
    @Transactional(rollbackFor = Exception.class)
    public int update(SubjectDto subjectDto) {
        int update;
        if ((update = subjectService(subjectDto.getType()).updateSubject(subjectDto)) == 0){
            update = this.insert(subjectDto);
        }
        return update;
    }

    /**
     * 删除
     *
     * @param subjectDto subjectDto
     * @return int
     * @author gaokx
     * @date 2020/10/16 18:04
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(SubjectDto subjectDto) {
        return subjectService(subjectDto.getType()).deleteSubject(subjectDto);
    }

    /**
     * 物理删除
     *
     * @param subjectDto subjectDto
     * @return int
     * @author gaokx
     * @date 2020/10/16 22:51
     */
    @Transactional(rollbackFor = Exception.class)
    public int physicalDelete(SubjectDto subjectDto) {
        if (subjectService(subjectDto.getType()).physicalDeleteSubject(subjectDto) > 0) {
            ExaminationSubject examinationSubject = new ExaminationSubject();
            examinationSubject.setSubjectId(subjectDto.getId());
            return examinationSubjectService.deleteBySubjectId(examinationSubject);
        }
        return -1;
    }

    /**
     * 批量删除
     *
     * @param type type
     * @param ids  ids
     * @return int
     * @author gaokx
     * @date 2020/10/16 18:04
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteAll(Integer type, Long[] ids) {
        return subjectService(type).deleteAllSubject(ids);
    }

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     * @author gaokx
     * @date 2020/10/16 22:52
     */
    @Transactional(rollbackFor = Exception.class)
    public int physicalDeleteAll(Long[] ids) {
        if (ArrayUtils.isNotEmpty(ids)) {
            ExaminationSubject examinationSubject = new ExaminationSubject();
            Map<Long, List<ExaminationSubject> > examinationSubjectsMap = new HashMap<>();
            for (Long id : ids) {
                examinationSubject.setSubjectId(id);
                List<ExaminationSubject> examinationSubjects = examinationSubjectService.findListBySubjectId(examinationSubject);
                if(CollectionUtils.isNotEmpty(examinationSubjects)){
                    throw  new CommonException(" 试题存在考试中，不能删除");
                }
                examinationSubjectsMap.put(id,examinationSubjects);
            }

            for (Long id : ids) {
                Subject subject = subjectCommonService.get(id);
                if (subject == null) {
                    throw new CommonException(" 试题不存在 : " + id);
                }
                subjectCommonService.delete(subject);
               /* List<ExaminationSubject> examinationSubjects = examinationSubjectsMap.get(id);
                if (CollectionUtils.isNotEmpty(examinationSubjects)) {
                    examinationSubjects.forEach(tempExaminationSubject -> {
                        subjectDto.setId(tempExaminationSubject.getSubjectId());
                        subjectService(tempExaminationSubject.getType()).physicalDeleteSubject(subjectDto);
                        examinationSubjectService.delete(tempExaminationSubject);
                    });
                }*/
            }
        }
        return 1;
    }

    /**
     * 根据题目类型返回对应的BaseSubjectService
     *
     * @param type type
     * @return BaseSubjectService
     * @author gaokx
     * @date 2020/10/16 17:34
     */
	private ISubjectService subjectService(Integer type) {
		return SpringContextHolder.getApplicationContext().getBean(SubjectTypeEnum.matchByValue(type).getService());
	}

    /**
     * 导入题目
     *
     * @param subjects      subjects
     * @param examinationId examinationId
     * @param categoryId    categoryId
     * @return int
     * @author gaokx
     * @date 2020/10/17 14:39
     */
    @Transactional(rollbackFor = Exception.class)
    public int importSubject(List<SubjectDto> subjects, Long examinationId, Long categoryId) {
        int updated = 0;
        String creator = SysUtil.getUser(), sysCode = SysUtil.getSysCode(), tenantCode = SysUtil.getTenantCode();
        // 暂时循环遍历保存
        for (SubjectDto subject : subjects) {
            if (examinationId != null){
                subject.setExaminationId(examinationId);
            }

            if (categoryId == null){
                categoryId = ExamSubjectConstant.DEFAULT_CATEGORY_ID;
            }
            subject.setCategoryId(categoryId);
            if (subject.getId() == null) {
                subject.setCommonValue(creator, sysCode, tenantCode);
                updated += this.insert(subject);
            } else {
                subject.setCommonValue(creator, sysCode, tenantCode);
                updated += this.update(subject);
            }
        }
        return updated;
    }

    /**
     * 遍历关系集合，按类型分组题目ID，返回map
     *
     * @param examinationSubjects examinationSubjects
     * @return Map
     * @author gaokx
     * @date 2020/10/17 10:43
     */
    private Map<String, Long[]> getSubjectIdByType(List<ExaminationSubject> examinationSubjects) {
        Map<String, Long[]> idMap = new HashMap<>();
        examinationSubjects.stream().collect(Collectors.groupingBy(ExaminationSubject::getType, Collectors.toList()))
                .forEach((type, temp) -> {
                    // 匹配类型
                    SubjectTypeEnum subjectType = SubjectTypeEnum.matchByValue(type);
                    if (subjectType != null) {
                        switch (subjectType) {
                            case CHOICES:
                                idMap.put(SubjectTypeEnum.CHOICES.name(),
                                        temp.stream().map(ExaminationSubject::getSubjectId).distinct()
                                                .toArray(Long[]::new));
                                break;
                            case JUDGEMENT:
                                idMap.put(SubjectTypeEnum.JUDGEMENT.name(),
                                        temp.stream().map(ExaminationSubject::getSubjectId).distinct()
                                                .toArray(Long[]::new));
                                break;
                            case MULTIPLE_CHOICES:
                                idMap.put(SubjectTypeEnum.MULTIPLE_CHOICES.name(),
                                        temp.stream().map(ExaminationSubject::getSubjectId).distinct()
                                                .toArray(Long[]::new));
                                break;
                            case SHORT_ANSWER:
                                idMap.put(SubjectTypeEnum.SHORT_ANSWER.name(),
                                        temp.stream().map(ExaminationSubject::getSubjectId).distinct()
                                                .toArray(Long[]::new));
                                break;
                        }
                    }
                });
        return idMap;
    }

    /**
     * 根据关系列表查询对应的题目的详细信息
     *
     * @param examinationSubjects examinationSubjects
     * @return List
     * @author gaokx
     * @date 2020/10/17 10:54
     */
    public List<SubjectDto> findSubjectDtoList(List<ExaminationSubject> examinationSubjects) {
        return findSubjectDtoList(examinationSubjects, false);
    }

	/**
	 * 根据关系列表查询对应的题目的详细信息
	 *
	 * @param examinationSubjects examinationSubjects
	 * @param findOptions findOptions
	 * @return List
	 * @author gaokx
	 * @date 2020/10/17 11:54
	 */
	public List<SubjectDto> findSubjectDtoList(List<ExaminationSubject> examinationSubjects, boolean findOptions) {
    	return findSubjectDtoList(examinationSubjects, findOptions, true);
	}

    /**
     * 根据关系列表查询对应的题目的详细信息
     *
     * @param examinationSubjects examinationSubjects
     * @param findOptions findOptions
	 * @param findAnswer findAnswer
	 * @return List
     * @author gaokx
     * @date 2020/10/17 11:54
     */
    public List<SubjectDto> findSubjectDtoList(List<ExaminationSubject> examinationSubjects, boolean findOptions, boolean findAnswer) {
        Map<String, Long[]> idMap = this.getSubjectIdByType(examinationSubjects);
        // 查询题目信息，聚合
        List<SubjectDto> subjectDtoList = new ArrayList<>();
        SubjectTypeEnum[] subjectTypes = SubjectTypeEnum.values();
        for(SubjectTypeEnum typeEnum : subjectTypes){
            if(idMap.containsKey(typeEnum.name())){
                List<Subject> subjects = subjectCommonService.findListById(idMap.get(typeEnum.name()));
                if(SubjectTypeEnum.isChoices(typeEnum.getValue())){
                    if (CollectionUtils.isNotEmpty(subjects)) {
                        // 查找选项信息
                        if (findOptions) {
                            subjects = subjects.stream().map(subjectCommonService::get).collect(Collectors.toList());
                        }
                        subjectDtoList.addAll(SubjectUtil.subjectToDto(subjects, findAnswer));
                    }
                }
            }
        }
        return subjectDtoList;
    }



    /**
     * 根据关系列表查询对应的题目的详细信息,不再做题型分组，做分组会打乱顺序
     *
     * @param examinationSubjects examinationSubjects
     * @param findAnswer findAnswer
     * @return List
     * @author gaokx
     * @date 2020/10/17 11:54
     */
    public List<SubjectDto> findSubjectDtoAllList(List<ExaminationSubject> examinationSubjects, boolean findAnswer) {
        List<SubjectDto> subjectDtoList = new ArrayList<>();
        List<Subject> subjects = new ArrayList<>();
        for (ExaminationSubject examinationSubject : examinationSubjects) {
            Subject subject = subjectCommonService.get(examinationSubject.getSubjectId());
            if (subject != null) {
                subjects.add(subject);
            }
        }
        subjectDtoList.addAll(SubjectUtil.subjectToDto(subjects, findAnswer));
        return subjectDtoList;
    }


    /**
     * 查询第一题
     *
     * @param examinationId examinationId
     * @return SubjectDto
     * @author gaokx
     * @date 2019/10/13 18:36:58
     */
    public SubjectDto findFirstSubjectByExaminationId(Long examinationId) {
        // 第一题
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setExaminationId(examinationId);
        // 根据考试ID查询考试题目管理关系，题目ID递增
        List<ExaminationSubject> examinationSubjects = examinationSubjectService.findList(examinationSubject);
        if (CollectionUtils.isEmpty(examinationSubjects)){
            throw new CommonException("该考试未录入题目");
        }
        // 第一题的ID
        examinationSubject = examinationSubjects.get(0);
        // 根据题目ID，类型获取题目的详细信息
        return this.get(examinationSubject.getSubjectId(), examinationSubject.getType());
    }

    /**
     * 导出
     * @param ids           ids
     * @param examinationId examinationId
     * @param categoryId    categoryId
     * @return List
     * @author gaokx
     * @date 2020/12/23 17:40:58
     */
    public List<SubjectDto> export(Long[] ids, Long examinationId, Long categoryId) {
        List<SubjectDto> subjects = new ArrayList<>();
        ExaminationSubject examinationSubject = new ExaminationSubject();
        List<ExaminationSubject> examinationSubjects = new ArrayList<>();
        // 根据题目id导出
        if (ArrayUtils.isNotEmpty(ids)) {
            for (Long id : ids) {
                examinationSubject.setSubjectId(id);
                Subject subject = subjectCommonService.get(id);
                ExaminationSubject examinationSubject1 = new ExaminationSubject();
                examinationSubject1.setSubjectId(subject.getId());
                examinationSubject1.setType(subject.getType());
                examinationSubject1.setCategoryId(subject.getCategoryId());
                examinationSubjects.add(examinationSubject1);
            }
        }  if (examinationId != null) {
            // 根据考试ID
            examinationSubjects = examinationSubjectService.findListByExaminationId(examinationId);
        } else {
            //按分类或者导出全部试题
            Subject subjectQry = new Subject();
            subjectQry.setTenantCode(SysUtil.getTenantCode());
            if (categoryId != null) {
                List<SubjectCategoryDto> categoryList = subjectCategoryService.getChildCategory(categoryId, true);
                if (CollectionUtils.isNotEmpty(categoryList)) {
                    List<Long> categoryIds = categoryList.stream().map(SubjectCategoryDto::getId).collect(Collectors.toList());
                    subjectQry.setCategoryIdList(categoryIds);
                } else {
                    subjectQry.setCategoryIdList(Collections.singletonList(categoryId));
                }
            }
            PageInfo<Subject> pageResult = subjectCommonService.findPage(PageUtil
                .pageInfo("1", "99999", CommonConstant.PAGE_SORT_DEFAULT, CommonConstant.PAGE_ORDER_DEFAULT), subjectQry);
            if (CollectionUtils.isNotEmpty(pageResult.getList())) {
                for(Subject subject : pageResult.getList()){
                    ExaminationSubject examinationSubject1 = new ExaminationSubject();
                    examinationSubject1.setSubjectId(subject.getId());
                    examinationSubject1.setType(subject.getType());
                    examinationSubject1.setCategoryId(subject.getCategoryId());
                    examinationSubjects.add(examinationSubject1);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(examinationSubjects)) {
            for (ExaminationSubject es : examinationSubjects) {
                SubjectDto subjectDto = this.get(es.getSubjectId(), es.getType());
                subjectDto.setExaminationId(es.getExaminationId());
                subjectDto.setCategoryId(es.getCategoryId());
                subjects.add(subjectDto);
              }
        }
        return subjects;
    }

}
