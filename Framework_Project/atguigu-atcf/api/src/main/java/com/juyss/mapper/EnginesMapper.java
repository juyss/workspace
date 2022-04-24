package com.juyss.mapper;

import com.juyss.bean.Engines;
import com.juyss.bean.EnginesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnginesMapper {
    int countByExample(EnginesExample example);

    int deleteByExample(EnginesExample example);

    int insert(Engines record);

    int insertSelective(Engines record);

    List<Engines> selectByExample(EnginesExample example);

    int updateByExampleSelective(@Param("record") Engines record, @Param("example") EnginesExample example);

    int updateByExample(@Param("record") Engines record, @Param("example") EnginesExample example);
}