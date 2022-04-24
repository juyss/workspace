package com.github.tangyi.exam.mapper;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.exam.api.dto.ExaminationUserRelationDto;
import com.github.tangyi.exam.api.module.Course;
import com.github.tangyi.exam.api.module.ExaminationUserRelation;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 考试学生关联mapper
 *
 * @author gaokx
 * @date 2018/11/8 21:10
 */
@Mapper
public interface ExaminationUserRelationMapper extends CrudMapper<ExaminationUserRelation> {

    List<ExaminationUserRelationDto> findUserInfoList(ExaminationUserRelationDto dto);

    int physicalDeleteAll(@Param(CommonConstant.PARAM_IDS) Long[] ids);
}
