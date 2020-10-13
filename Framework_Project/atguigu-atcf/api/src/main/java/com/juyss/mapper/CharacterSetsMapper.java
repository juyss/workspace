package com.juyss.mapper;

import com.juyss.bean.CharacterSets;
import com.juyss.bean.CharacterSetsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CharacterSetsMapper {
    int countByExample(CharacterSetsExample example);

    int deleteByExample(CharacterSetsExample example);

    int insert(CharacterSets record);

    int insertSelective(CharacterSets record);

    List<CharacterSets> selectByExample(CharacterSetsExample example);

    int updateByExampleSelective(@Param("record") CharacterSets record, @Param("example") CharacterSetsExample example);

    int updateByExample(@Param("record") CharacterSets record, @Param("example") CharacterSetsExample example);
}