package com.github.tangyi.exam.service;

import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.util.StringUtils;
import com.github.tangyi.exam.api.constants.AnswerConstant;
import com.github.tangyi.exam.api.dto.SubjectDto;
import com.github.tangyi.exam.api.module.ExaminationSubject;
import com.github.tangyi.exam.api.module.Subject;
import com.github.tangyi.exam.api.module.SubjectCategory;
import com.github.tangyi.exam.api.module.SubjectChoices;
import com.github.tangyi.exam.api.module.SubjectOption;
import com.github.tangyi.exam.enums.SubjectTypeEnum;
import com.github.tangyi.exam.mapper.SubjectMapper;
import com.github.tangyi.exam.utils.AnswerHandlerUtil;
import com.github.tangyi.exam.utils.SubjectUtil;
import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 题 公共service
 *
 * @author gaokx
 * @date 2020/06/08 21:23
 */
@AllArgsConstructor
@Service
public class SubjectCommonService extends CrudService<SubjectMapper, Subject>
        implements ISubjectService {

    private final SubjectOptionService subjectOptionService;

    private final ExaminationSubjectService examinationSubjectService;

    private final SubjectCategoryService subjectCategoryService;

    /**
     * 查找题目
     *
     */
    @Override
    @Cacheable(value = "subject#" + CommonConstant.CACHE_EXPIRE, key = "#subject.id")
    public Subject get(Subject subject) {
        Subject record = super.get(subject);
        // 查找选项信息
        if (record != null) {
            if(SubjectTypeEnum.isChoices(record.getType())){
                SubjectOption subjectOption = new SubjectOption();
                subjectOption.setSubjectChoicesId(record.getId());
                List<SubjectOption> options = subjectOptionService.getBySubjectChoicesId(subjectOption);
                record.setOptions(options);
            }
        }
        return record;
    }

    @Override
    public List<SubjectOption> getOptions(Long subjectId) {
        // 查找选项信息
                SubjectOption subjectOption = new SubjectOption();
                subjectOption.setSubjectChoicesId(subjectId);
        return  subjectOptionService.getBySubjectChoicesId(subjectOption);
    }

    /**
     * 新增
     *
     */
    @Override
    @Transactional
    public int insert(Subject subject) {
        return super.insert(subject);
    }

    /**
     * 更新题目
     *
     */
    @Override
    @Transactional
    @CacheEvict(value = "subject", key = "#subject.id")
    public int update(Subject subject) {
        // 更新选项
        this.insertOptions(subject);
        return super.update(subject);
    }

    /**
     * 根据ID查询
     *
     */
    public Subject getByCurrentId(Long examinationId, Subject subject) {
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setExaminationId(examinationId);
        examinationSubject.setSubjectId(subject.getId());
        examinationSubject = examinationSubjectService.findByExaminationIdAndSubjectId(examinationSubject);
        if (examinationSubject == null)
            return null;
        return this.getSubjectChoicesById(examinationSubject.getSubjectId());
    }

    /**
     * 根据上一题ID查询下一题
     *
     */
    public Subject getByPreviousId(Long examinationId, Subject subject) {
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setExaminationId(examinationId);
        examinationSubject.setSubjectId(subject.getId());
        examinationSubject = examinationSubjectService.getByPreviousId(examinationSubject);
        if (examinationSubject == null)
            return null;
        return this.getSubjectChoicesById(examinationSubject.getSubjectId());
    }

    /**
     * 根据当前题目ID查询上一题
     *
     * @param examinationId  examinationId
     */
    public Subject getPreviousByCurrentId(Long examinationId, Subject subject) {
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setExaminationId(examinationId);
        examinationSubject.setSubjectId(subject.getId());
        examinationSubject = examinationSubjectService.getPreviousByCurrentId(examinationSubject);
        if (examinationSubject == null)
            return null;
        return this.getSubjectChoicesById(examinationSubject.getSubjectId());
    }

    /**
     * 删除题目
     *
     */
    @Override
    @Transactional
    @CacheEvict(value = "subject", key = "#subject.id")
    public int delete(Subject subject) {
        int update;
        if ((update = super.delete(subject)) > 0){
                if(SubjectTypeEnum.isChoices(subject.getType())){
                    this.deleteOptionAndRelation(subject.getId());
                }
        }

        return update;
    }

    /**
     * 物理删除
     *
     */
    @Transactional
    @CacheEvict(value = "subject", key = "#subject.id")
    public int physicalDelete(Subject subject) {
        int update;
        if ((update = this.dao.physicalDelete(subject)) > 0){
            if(SubjectTypeEnum.isChoices(subject.getType())){
                this.deleteOptionAndRelation(subject.getId());
            }
        }
        return update;
    }

    /**
     * 批量删除题目
     *
     * @param ids ids
     * @return int
     * @author gaokx
     * @date 2020/1/3 14:24
     */
    @Override
    @Transactional
    @CacheEvict(value = "subject", allEntries = true)
    public int deleteAll(Long[] ids) {
        int update;
        if ((update = super.deleteAll(ids)) > 0) {
            for (Long id : ids)
                this.deleteOptionAndRelation(id);
        }
        return update;
    }

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     * @author gaokx
     * @date 2020/06/16 22:44
     */
    @Transactional
    @CacheEvict(value = "subject", allEntries = true)
    public int physicalDeleteAll(Long[] ids) {
        int update;
        if ((update = this.dao.physicalDeleteAll(ids)) > 0) {
            for (Long id : ids)
                this.deleteOptionAndRelation(id);
        }
        return update;
    }

    /**
     * 删除题目的选项和与考试的关联
     *
     * @param subjectId subjectId
     * @author gaokx
     * @date 2020/06/16 22:09
     */
    @Transactional
    public void deleteOptionAndRelation(Long subjectId) {
        // 删除选项
        SubjectOption option = new SubjectOption();
        option.setSubjectChoicesId(subjectId);
        subjectOptionService.deleteBySubjectChoicesId(option);
        // 删除关联关系
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setSubjectId(subjectId);
        examinationSubjectService.deleteBySubjectId(examinationSubject);
    }

    /**
     * 根据ID查询
     *
     * @param id id
     * @return SubjectDto
     * @author gaokx
     * @date 2020/06/16 17:36
     */
    @Override
    @Cacheable(value = "subject#" + CommonConstant.CACHE_EXPIRE, key = "#id")
    public SubjectDto getSubject(Long id) {
        Subject subject = this.get(id);
        // 查找选项信息
        if (subject != null) {
            SubjectOption subjectOption = new SubjectOption();
            subjectOption.setSubjectChoicesId(subject.getId());
            List<SubjectOption> options = subjectOptionService.getBySubjectChoicesId(subjectOption);
            subject.setOptions(options);
        }
        SubjectDto subjectDto =  SubjectUtil.subjectToDto(subject, true);
        SubjectCategory subjectCategory = subjectCategoryService.get(subjectDto.getCategoryId());
        if (subjectCategory != null && StringUtils.isNotEmpty(subjectCategory.getCategoryName())) {
            subjectDto.setCategoryName(subjectCategory.getCategoryName());
        }
        return subjectDto ;
    }

    /**
     * 根据上一题ID查询下一题
     *
     */
    @Override
    @Transactional
    public SubjectDto getNextByCurrentIdAndType(Long examinationId, Long previousId, Integer nextType) {
        Subject subject = new Subject();
        subject.setId(previousId);
        if (AnswerConstant.CURRENT.equals(nextType)) {
            subject = this.getByCurrentId(examinationId, subject);
        } else if (AnswerConstant.NEXT.equals(nextType)) {
            subject = this.getByPreviousId(examinationId, subject);
        } else {
            subject = this.getPreviousByCurrentId(examinationId, subject);
        }
        return SubjectUtil.subjectToDto(subject, true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = "subject", key = "#subjectDto.id")
	public int insertSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectDto, subject);
        subject.setAnswer(subjectDto.getAnswer().getAnswer());
        subject.setType(subjectDto.getType());
        insertOptions(subject);
        return this.insert(subject);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertOptions(Subject subject) {
        if(!SubjectTypeEnum.isChoices(subject.getType())){
            return ;
        }
        if (CollectionUtils.isNotEmpty(subject.getOptions())) {
            SubjectOption subjectOption = new SubjectOption();
            subjectOption.setSubjectChoicesId(subject.getId());
            subjectOptionService.deleteBySubjectChoicesId(subjectOption);
            // 初始化
            subject.getOptions().forEach(option -> {
                option.setCommonValue(subject.getCreator(), subject.getApplicationCode(),
                    subject.getTenantCode());
                option.setSubjectChoicesId(subject.getId());
            });
            // 批量插入
            subjectOptionService.insertBatch(subject.getOptions());
        }
    }

    /**
     * 更新，包括更新选项
     *
     * @param subjectDto subjectDto
     * @return int
     * @author gaokx
     * @date 2020/06/16 17:50
     */
    @Override
    @Transactional
	@CacheEvict(value = "subject", key = "#subjectDto.id")
	public int updateSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectDto, subject);
        subject.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
        // 参考答案
        subject.setAnswer(AnswerHandlerUtil.replaceComma(subjectDto.getAnswer().getAnswer()));
        return this.update(subject);
    }

    /**
     * 删除
     *
     * @param subjectDto subjectDto
     * @return int
     * @author gaokx
     * @date 2020/06/16 17:50
     */
    @Override
    @Transactional
	@CacheEvict(value = "subject", key = "#subjectDto.id")
	public int deleteSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectDto, subject);
        return this.delete(subject);
    }

    /**
     * 物理删除
     *
     * @param subjectDto subjectDto
     * @return int
     * @author gaokx
     * @date 2020/06/16 22:50
     */
    @Override
    @Transactional
	@CacheEvict(value = "subject", key = "#subjectDto.id")
	public int physicalDeleteSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectDto, subject);
        return this.physicalDelete(subject);
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return int
     * @author gaokx
     * @date 2020/06/16 17:50
     */
    @Override 
    @Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = "subject", allEntries = true)
	public int deleteAllSubject(Long[] ids) {
        return this.deleteAll(ids);
    }

    /**
     * 物理删除
     *
     * @param ids ids
     * @return int
     * @author gaokx
     * @date 2020/06/16 22:51
     */
    @Override 
    @Transactional(rollbackFor = Exception.class)
	  @CacheEvict(value = "subject", allEntries = true)
	public int physicalDeleteAllSubject(Long[] ids) {
        return this.physicalDeleteAll(ids);
    }

    /**
     * 查询列表
     *
     * @param subjectDto subjectDto
     * @return List
     * @author gaokx
     * @date 2020/06/16 18:16
     */
    @Override
    public List<SubjectDto> findSubjectList(SubjectDto subjectDto) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectDto, subject);
        return SubjectUtil.subjectToDto(this.findList(subject), true);
    }

    /**
     * 查询分页列表
     *
     * @param pageInfo   pageInfo
     * @param subjectDto subjectDto
     * @return PageInfo
     * @author gaokx
     * @date 2020/06/16 18:16
     */
    @Override
    public PageInfo<SubjectDto> findSubjectPage(PageInfo pageInfo, SubjectDto subjectDto) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectDto, subject);
        // 选择题类型：单选或多选
        /*if (subjectDto.getType() != null)
            subject.setChoicesType(subjectDto.getType());*/
        PageInfo<SubjectChoices> subjectChoicesPageInfo = this.findPage(pageInfo, subject);
        List<SubjectDto> subjectDtos = SubjectUtil.subjectChoicesToDto(subjectChoicesPageInfo.getList(), true);
        PageInfo<SubjectDto> subjectDtoPageInfo = new PageInfo<>();
        subjectDtoPageInfo.setList(subjectDtos);
        subjectDtoPageInfo.setTotal(subjectChoicesPageInfo.getTotal());
        subjectDtoPageInfo.setPageSize(subjectChoicesPageInfo.getPageSize());
        subjectDtoPageInfo.setPageNum(subjectChoicesPageInfo.getPageNum());
        return subjectDtoPageInfo;
    }

    /**
     * 根据ID批量查询
     *
     * @param ids ids
     * @return List
     * @author gaokx
     * @date 2020/06/16 18:16
     */
    @Override
    public List<SubjectDto> findSubjectListById(Long[] ids) {
        return SubjectUtil.subjectToDto(this.findListById(ids), true);
    }

    /**
     * 根据题目ID查询题目信息和选项
     *
     * @param subjectId subjectId
     * @return SubjectChoices
     * @author gaokx
     * @date 2020/10/07 21:03:43
     */
    private Subject getSubjectChoicesById(Long subjectId) {
        Subject subject = new Subject();
        subject.setId(subjectId);
        subject = this.get(subject);
        if(SubjectTypeEnum.isChoices(subject.getType())){
            SubjectOption subjectOption = new SubjectOption();
            subjectOption.setSubjectChoicesId(subject.getId());
            List<SubjectOption> options = subjectOptionService.getBySubjectChoicesId(subjectOption);
            subject.setOptions(options);
        }
        return subject;
    }
}
