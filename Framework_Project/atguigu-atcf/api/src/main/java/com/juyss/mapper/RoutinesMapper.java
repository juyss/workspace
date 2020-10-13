package com.juyss.mapper;

import com.juyss.bean.Routines;
import com.juyss.bean.RoutinesExample;
import com.juyss.bean.RoutinesWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoutinesMapper {
    int countByExample(RoutinesExample example);

    int deleteByExample(RoutinesExample example);

    int insert(RoutinesWithBLOBs record);

    int insertSelective(RoutinesWithBLOBs record);

    List<RoutinesWithBLOBs> selectByExampleWithBLOBs(RoutinesExample example);

    List<Routines> selectByExample(RoutinesExample example);

    int updateByExampleSelective(@Param("record") RoutinesWithBLOBs record, @Param("example") RoutinesExample example);

    int updateByExampleWithBLOBs(@Param("record") RoutinesWithBLOBs record, @Param("example") RoutinesExample example);

    int updateByExample(@Param("record") Routines record, @Param("example") RoutinesExample example);
}