package com.juyss.mapper;

import com.juyss.bean.ViewRoutineUsage;
import com.juyss.bean.ViewRoutineUsageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewRoutineUsageMapper {
    int countByExample(ViewRoutineUsageExample example);

    int deleteByExample(ViewRoutineUsageExample example);

    int insert(ViewRoutineUsage record);

    int insertSelective(ViewRoutineUsage record);

    List<ViewRoutineUsage> selectByExample(ViewRoutineUsageExample example);

    int updateByExampleSelective(@Param("record") ViewRoutineUsage record, @Param("example") ViewRoutineUsageExample example);

    int updateByExample(@Param("record") ViewRoutineUsage record, @Param("example") ViewRoutineUsageExample example);
}