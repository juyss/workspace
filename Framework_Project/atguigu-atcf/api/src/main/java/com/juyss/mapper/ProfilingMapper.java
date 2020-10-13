package com.juyss.mapper;

import com.juyss.bean.Profiling;
import com.juyss.bean.ProfilingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProfilingMapper {
    int countByExample(ProfilingExample example);

    int deleteByExample(ProfilingExample example);

    int insert(Profiling record);

    int insertSelective(Profiling record);

    List<Profiling> selectByExample(ProfilingExample example);

    int updateByExampleSelective(@Param("record") Profiling record, @Param("example") ProfilingExample example);

    int updateByExample(@Param("record") Profiling record, @Param("example") ProfilingExample example);
}