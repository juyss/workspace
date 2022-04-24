package com.github.tangyi.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.common.core.utils.PageUtil;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.exam.api.dto.ExaminationDto;
import com.github.tangyi.exam.api.dto.ExaminationUserRelationDto;
import com.github.tangyi.exam.api.dto.SubjectDto;
import com.github.tangyi.exam.api.module.Course;
import com.github.tangyi.exam.api.module.Examination;
import com.github.tangyi.exam.api.module.ExaminationSubject;
import com.github.tangyi.exam.api.module.ExaminationUserRelation;
import com.github.tangyi.exam.mapper.ExaminationSubjectMapper;
import com.github.tangyi.exam.mapper.ExaminationUserRelationMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考试用户关联service
 *
 * @author tangyi
 * @date 2019/6/16 15:38
 */
@AllArgsConstructor
@Service
public class ExaminationUserRelationService extends CrudService<ExaminationUserRelationMapper, ExaminationUserRelation> {

    @Override
    public PageInfo<ExaminationUserRelation> findPage(PageInfo<ExaminationUserRelation> page, ExaminationUserRelation relation) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return super.findPage(page,relation);
    }

    /**
     * 分页返回用户信息
     * @param page
     * @param userRelationDto
     * @return
     */
    public PageInfo<ExaminationUserRelationDto> findUserInfoListPage(PageInfo<ExaminationUserRelationDto> page, ExaminationUserRelationDto userRelationDto) {
        userRelationDto.setTenantCode(SysUtil.getTenantCode());
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo<>(dao.findUserInfoList(userRelationDto));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean  batchInsert(List<ExaminationUserRelation> examinationUserRelations){
        for(ExaminationUserRelation userRelation : examinationUserRelations){
          super.insert(userRelation);
        }
        return true ;
    }

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     * @author gaokx
     * @date 2020/12/26 22:54
     */
    @Transactional(rollbackFor = Exception.class)
    public int physicalDeleteAll(Long[] ids) {
        return this.dao.physicalDeleteAll(ids);
    }

}
