package com.juyss.mapper;

import com.juyss.bean.CollationCharacterSetApplicability;
import com.juyss.bean.CollationCharacterSetApplicabilityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollationCharacterSetApplicabilityMapper {
    int countByExample(CollationCharacterSetApplicabilityExample example);

    int deleteByExample(CollationCharacterSetApplicabilityExample example);

    int insert(CollationCharacterSetApplicability record);

    int insertSelective(CollationCharacterSetApplicability record);

    List<CollationCharacterSetApplicability> selectByExample(CollationCharacterSetApplicabilityExample example);

    int updateByExampleSelective(@Param("record") CollationCharacterSetApplicability record, @Param("example") CollationCharacterSetApplicabilityExample example);

    int updateByExample(@Param("record") CollationCharacterSetApplicability record, @Param("example") CollationCharacterSetApplicabilityExample example);
}